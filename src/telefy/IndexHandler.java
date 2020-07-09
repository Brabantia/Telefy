package telefy;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public class IndexHandler implements HttpHandler {
	private final ResourceModel resourceModel;
	private TemplateController templateController;

	public IndexHandler(TemplateController templateController, ResourceModel resourceModel) {
		this.templateController = templateController;
		this.resourceModel = resourceModel;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		HttpRequest req = new HttpRequest(exchange);
		HttpResponse resp = new HttpResponse(exchange);

		if (!req.getPath().equals("/") && !req.getPath().startsWith("/index.html")) {
			resp.respondNotFound();
		}

		TemplatedPageView indexView = this.templateController.getTemplatePage(req);

		resp.set(indexView).respondOk();
	}
}