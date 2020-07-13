package telefy.controller;

import java.util.List;
import telefy.HttpRequest;
import telefy.model.ResourceModel;
import telefy.entity.Resource;
import telefy.model.AccountsModel;
import telefy.model.FileResourceModel;
import telefy.model.ProductsModel;
import telefy.view.CurrentMenuView;
import telefy.view.ManufacturerMenuTagView;
import telefy.view.OsMenuTagView;
import telefy.view.LoginTagView;
import telefy.view.TemplatedPageView;

public class TemplateController {
	public static final String TEMPLATE_FILE = "templates/template.html";
	private final ResourceModel resourceModel;
	private final AccountsModel accountsModel;
	private final ProductsModel productsModel;

	public TemplateController(FileResourceModel rm, AccountsModel am, ProductsModel pm) {
		this.resourceModel = rm;
		this.accountsModel = am;
		this.productsModel = pm;
	}

	TemplatedPageView getTemplatePage(HttpRequest req) {
		Resource template = this.resourceModel.get(TEMPLATE_FILE);

		List<String> oses = this.productsModel.getAllOperatingSystems();
		List<String> manufacturers = this.productsModel.getAllManufacturers();

		TemplatedPageView view = new TemplatedPageView(new String(template.getData()));
		view.put(new LoginTagView(req.getPath(), this.accountsModel.getLoginById(req.get("account"))));
		view.put(new OsMenuTagView(req.get("os"), oses.toArray(new String[oses.size()])));
		view.put(new ManufacturerMenuTagView(req.get("man"), manufacturers.toArray(new String[manufacturers.size()])));
		view.put(new CurrentMenuView(req.getPath(), req.get("os"), req.get("man")));

		return view;
	}

}