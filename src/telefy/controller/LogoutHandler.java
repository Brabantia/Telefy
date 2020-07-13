package telefy.controller;

import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;

public class LogoutHandler extends SafeHttpHandler {
	@Override
	public void handle(HttpRequest req, HttpResponse resp) throws IOException {
		resp.removeCookie("account").removeCookie("user_name").respondRedirect("/");
	}
}