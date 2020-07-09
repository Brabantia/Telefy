package telefy;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public class LoginHandler implements HttpHandler {
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
	public void handle(HttpExchange httpExchange) throws IOException {
		try {
			handleResponse(httpExchange);
		} catch (Exception e) {
			System.err.println("Failed to process request.");
			e.printStackTrace();
			throw e;
		}
	}

	private void handleResponse(HttpExchange httpExchange) throws IOException {
		HttpRequest req = new HttpRequest(httpExchange);
		HttpResponse resp = new HttpResponse(httpExchange);

		TemplatedPageView template = this.templateController.getTemplatePage(req);
		resp.set(template);

		TemplatedPageView loginView = new TemplatedPageView("CONTENT", new String(this.resourceModel.get(LOGIN_TEMPLATE).data));
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
