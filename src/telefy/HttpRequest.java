package telefy;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {
	public static final Boolean DO_DECODE = true;
	public static final Boolean DONT_DECODE = false;
	private final Map<String, String> argsMap = new HashMap<>();
	private final URI uri;

	public HttpRequest(HttpExchange httpExchange) throws IOException {
		this.uri = httpExchange.getRequestURI();
		extractParameters(this.uri.getQuery(), "&", DONT_DECODE);
		extractParameters(readInputStream(httpExchange.getRequestBody()), "&", DO_DECODE);
		List<String> cookieLines = httpExchange.getRequestHeaders().get("Cookie");
		if (cookieLines != null) {
			for (String cookieRow : cookieLines) {
				extractParameters(cookieRow, ";", DONT_DECODE);
			}
		}
	}

	private void extractParameters(String text, String delimiter, boolean decode) throws UnsupportedEncodingException {
		if (text == null || text.length() == 0) {
			return;
		}
		String[] parameters = text.split(delimiter);
		for (String param : parameters) {
			param = param.trim();
			int divider = param.indexOf("=");
			if (divider < 0) {
				String value = decode ? URLDecoder.decode(param, "UTF-8") : param;
				this.argsMap.put(value, null);
			} else {
				String arg = param.substring(0, divider);
				String value = param.substring(divider+1);
				if (decode) {
					arg = URLDecoder.decode(arg, "UTF-8");
					value = URLDecoder.decode(value, "UTF-8");
				}
				this.argsMap.put(arg, value);
			}
		}
	}

	public boolean contains(String key) {
		return argsMap.containsKey(key);
	}

	public String get(String key) {
		return argsMap.get(key);
	}

	public URI getUri() {
		return this.uri;
	}

	public String getPath() {
		return getUri().getPath();
	}

	@Override
	public String toString() {
		String text = "HttpRequest[\n\turi=\"" + this.uri + "\"\n\tArgs{";
		for (Map.Entry<String, String> entry : this.argsMap.entrySet()) {
			text += "\n\t\t\"" + entry.getKey()+"\" = \"" + entry.getValue() + "\"";
		}
		return text + "\n\t}\n]";
	}

	public static String readInputStream(InputStream in) throws IOException {
		StringBuilder sb = new StringBuilder();
		int i;
		while ((i = in.read()) != -1) {
			 sb.append((char) i);
		}
		return sb.toString();
	}
}