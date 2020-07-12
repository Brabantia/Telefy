package telefy.controller;

import telefy.view.PhoneView;
import telefy.view.GridLayoutView;
import telefy.model.ResourceModel;
import telefy.view.TemplatedPageView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;
import telefy.view.HtmlPageView;

public class IndexHandler implements HttpHandler {
	public static final String PHONE_TEMPLATE = "templates/phone.html";
	
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
		
		GridLayoutView layoutView = new GridLayoutView();
		indexView.put(layoutView);
		
		for (int a = 0; a < 25; ++a) {
			PhoneView phone = new PhoneView(new String(resourceModel.get(PHONE_TEMPLATE).getData()));
			phone.setTitle("Pixel 4");
			phone.setText("The best phone in teh world!");
			phone.setPrice(100+10*a);
			phone.setTags(new PhoneView.Tag("Google", "index.html?man=Google"), new PhoneView.Tag("Android", "index.html?os=Android"));
			phone.setImage("res/images/phones/phone8-400.jpg", "Pixel 4 Phone");
			layoutView.add(phone);
		}

		resp.set(indexView).respondOk();
	}
}