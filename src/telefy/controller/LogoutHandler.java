package telefy.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import telefy.HttpResponse;

public class LogoutHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		HttpResponse resp = new HttpResponse(exchange);
		resp.removeCookie("account").removeCookie("user_name").respondRedirect("/");
	}
}