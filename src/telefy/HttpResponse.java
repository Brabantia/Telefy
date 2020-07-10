package telefy;

import telefy.view.View;
import telefy.view.HtmlPageView;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class HttpResponse {
		public static final int COOKIE_EXPIRATION = 15;  // In minutes.
		public static final SimpleDateFormat UTC_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
		static {
			UTC_FORMATTER.setTimeZone(TimeZone.getTimeZone("UTC"));
		}

		private final List<String> cookies = new ArrayList<>();
		private final HttpExchange httpExchange;
		private boolean closed = false;
		private View view = new HtmlPageView();

		public HttpResponse(HttpExchange httpExchange) {
			this.httpExchange = httpExchange;
		}

		public void respondOk() throws IOException {
			close(HttpURLConnection.HTTP_OK);
		}
		public void respondError() throws IOException {
			close(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		public void respondBadRequest() throws IOException {
			close(HttpURLConnection.HTTP_BAD_REQUEST);
		}
		public void respondNotFound() throws IOException {
			close(HttpURLConnection.HTTP_NOT_FOUND);
		}
		public void respondRedirect(String path) throws IOException {
			this.httpExchange.getResponseHeaders().set("Location", path);
			close(HttpURLConnection.HTTP_MOVED_TEMP);
		}

		public HttpResponse addCookie(String key, String value, int expireMinutes) {
			cookies.add(key + "=" + value + "; expires=" + minuesInFuture(expireMinutes) + "; path=/;");
			return this;
		}

		public HttpResponse removeCookie(String key) {
			return addCookie(key, "0", -1);
		}

		public HttpResponse addCookie(String key, String value) {
			return addCookie(key, value, COOKIE_EXPIRATION);
		}

		public HttpResponse set(View view) {
			this.view = view;
			return this;
		}

		public boolean closed() {
			return this.closed;
		}

		private void close(int responseCode) throws IOException {
			if (this.closed) {
				throw new RuntimeException("HttpResponse has already responded closed the connection but was called to close again.");
			}
			this.closed = true;
			if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_MOVED_TEMP) {
				System.err.println("Sending " + responseCode + " error response to request: " + new HttpRequest(httpExchange));
			}

			Headers headers = this.httpExchange.getResponseHeaders();
			headers.put("Set-Cookie", this.cookies);
			headers.set("Content-Type", this.view.getMime());
			this.httpExchange.sendResponseHeaders(responseCode, this.view.length());

			OutputStream outputStream = this.httpExchange.getResponseBody();
			outputStream.write(this.view.getBytes());
			outputStream.flush();
			outputStream.close();
		}

		public static String minuesInFuture(int minutes) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, minutes);
			return UTC_FORMATTER.format(cal.getTime());
		}
}