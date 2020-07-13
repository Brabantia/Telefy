package telefy.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import telefy.model.ResourceModel;
import telefy.entity.Resource;
import telefy.view.FileView;
import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;

public class FileHandler implements HttpHandler {
	private final ResourceModel resourceModel;

	public FileHandler(ResourceModel resourdeModel) {
		this.resourceModel = resourdeModel;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		HttpRequest req = new HttpRequest(exchange);
		HttpResponse resp = new HttpResponse(exchange);

		Resource resource = this.resourceModel.get(req.getPath());
		if (resource == null) {
			resp.respondNotFound();
		} else {
			resp.set(new FileView(resource)).respondOk();
		}
	}
}