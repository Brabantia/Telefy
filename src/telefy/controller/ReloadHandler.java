package telefy.controller;

import telefy.model.FileResourceModel;
import telefy.view.HtmlPageView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import telefy.HttpResponse;

public class ReloadHandler implements HttpHandler {
	private final FileResourceModel resourceModel;

	public ReloadHandler(FileResourceModel resourceModel) {
		this.resourceModel = resourceModel;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		this.resourceModel.loadResources();
		(new HttpResponse(exchange)).set(new HtmlPageView("Resources Reloaded!")).respondOk();
	}
}