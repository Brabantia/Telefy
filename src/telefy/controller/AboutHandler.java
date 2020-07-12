package telefy.controller;

import telefy.model.ResourceModel;
import telefy.model.AccountsModel;
import telefy.entity.Account;
import telefy.view.LoginErrorTagView;
import telefy.view.TemplatedPageView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;
import telefy.view.HtmlPageView;

public class AboutHandler implements HttpHandler {
	public final String ABOUT_TEMPLATE = "templates/about.html";

	private final ResourceModel resourceModel;
	private final TemplateController templateController;

	public AboutHandler(TemplateController templateController, ResourceModel resourceModel) {
		this.templateController = templateController;
		this.resourceModel = resourceModel;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		HttpRequest req = new HttpRequest(httpExchange);
		HttpResponse resp = new HttpResponse(httpExchange);

		TemplatedPageView template = this.templateController.getTemplatePage(req);
		resp.set(template);

		template.put(new HtmlPageView("CONTENT", new String(this.resourceModel.get(ABOUT_TEMPLATE).getData())));
		
		resp.respondOk();
	}
}
