package telefy.controller;

import telefy.model.ResourceModel;
import telefy.model.AccountsModel;
import telefy.entity.Account;
import telefy.view.LoginErrorTagView;
import telefy.view.TemplatedPageView;
import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;

public class LoginHandler extends SafeHttpHandler {
	public final String LOGIN_TEMPLATE = "templates/login.html";

	private final AccountsModel accountsModel;
	private final ResourceModel resourceModel;
	private final TemplateController templateController;

	public LoginHandler(TemplateController templateController, ResourceModel resourceModel, AccountsModel accounts) {
		this.templateController = templateController;
		this.resourceModel = resourceModel;
		this.accountsModel = accounts;
	}

	@Override
	public void handle(HttpRequest req, HttpResponse resp) throws IOException {
		TemplatedPageView template = this.templateController.getTemplatePage(req);
		resp.set(template);

		TemplatedPageView loginView = new TemplatedPageView("CONTENT", new String(this.resourceModel.get(LOGIN_TEMPLATE).getData()));
		template.put(loginView);

		if (req.contains("account") && req.contains("user_name")) {
			resp.respondRedirect("/");
		} else if (req.contains("email") && req.contains("password")) {
			Account account = accountsModel.getLogin(req.get("email"));
			if (account == null) {
				loginView.put(new LoginErrorTagView("Error: Database Querying Failed"));
				resp.respondError();
			} else if (account.getName() == null || account.getName().length() == 0) {
				loginView.put(new LoginErrorTagView("Error: User Does Not Exist"));
				resp.respondBadRequest();
			} else if (!req.get("password").equals(account.getPasswordHash())) {
				loginView.put(new LoginErrorTagView("Error: Incorrect Password"));
				resp.respondBadRequest();
			} else {
				resp.addCookie("account", Integer.toString(account.getId()))
						.addCookie("user_name", account.getName())
						.respondRedirect("/");
			}
		} else {
			resp.respondOk();
		}

		if (!resp.closed()) {
			resp.set(new LoginErrorTagView("Server Error")).respondError();
			throw new RuntimeException("No HTTP response was sent for: " + req);
		}
	}
}
