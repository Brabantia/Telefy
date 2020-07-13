package telefy.controller;

import telefy.model.FileResourceModel;
import telefy.view.HtmlPageView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;

public class ReloadHandler extends SafeHttpHandler {
	private final FileResourceModel resourceModel;

	public ReloadHandler(FileResourceModel resourceModel) {
		this.resourceModel = resourceModel;
	}

	@Override
	public void handle(HttpRequest req, HttpResponse resp) throws IOException {
		this.resourceModel.loadResources();
		resp.set(new HtmlPageView("Resources Reloaded!")).respondOk();
	}
}