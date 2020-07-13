package telefy.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;
import telefy.view.HtmlPageView;

public abstract class SafeHttpHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		HttpRequest req = new HttpRequest(exchange);
		HttpResponse resp = new HttpResponse(exchange);
		
		System.out.println("Received Request: " + req.getUri());
				
		try {
			handle(req, resp);
		} catch (Exception e) {
			System.err.println("Failed to process request.");
			e.printStackTrace();
			
			resp.set(new HtmlPageView("Server Error: " + e.getMessage()));
			resp.respondError();
		}
	}
	
	public abstract void handle(HttpRequest req, HttpResponse resp) throws IOException;
}
