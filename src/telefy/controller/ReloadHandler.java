package telefy.controller;

import telefy.model.FileResourceModel;
import telefy.view.HtmlPageView;
import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;
import telefy.StoreServer;
import telefy.model.AccountsModel;

public class ReloadHandler extends SafeHttpHandler {
	private final FileResourceModel resourceModel;
	private final AccountsModel accountsModel;

	public ReloadHandler(FileResourceModel rm, AccountsModel am) {
		this.resourceModel = rm;
		this.accountsModel = am;
	}

	@Override
	public void handle(HttpRequest req, HttpResponse resp) throws IOException {
		if (this.accountsModel.getLoginById(req.get("account")).isAdmin()) {
			this.resourceModel.loadResources();
			resp.respondRedirect("/");
			try {
				StoreServer.stop();
				StoreServer.start();
			} catch(Exception e) {e.printStackTrace();}
		} else {
			resp.set(new HtmlPageView("UNAUTHORIZED: Only logged in Administrators can reload the server.")).respondUnauthorized();
		}
	}
}