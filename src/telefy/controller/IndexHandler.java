package telefy.controller;

import telefy.model.ResourceModel;
import telefy.view.PhoneView;
import telefy.view.GridLayoutView;
import telefy.view.TemplatedPageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import telefy.HttpRequest;
import telefy.HttpResponse;
import telefy.entity.Product;
import telefy.model.FileResourceModel;
import telefy.model.ProductsModel;

public class IndexHandler extends SafeHttpHandler {

	public static final String PHONE_TEMPLATE = "templates/phone.html";

	private final ResourceModel resourceModel;
	private final TemplateController templateController;
	private final ProductsModel productsModel;

	public IndexHandler(TemplateController templateController, FileResourceModel resourceModel, ProductsModel productsModel) {
		this.templateController = templateController;
		this.resourceModel = resourceModel;
		this.productsModel = productsModel;
	}

	@Override
	public void handle(HttpRequest req, HttpResponse resp) throws IOException {
		if (!req.getPath().equals("/") && !req.getPath().startsWith("/index.html")) {
			resp.respondNotFound();
		}

		TemplatedPageView indexView = this.templateController.getTemplatePage(req);

		GridLayoutView layoutView = new GridLayoutView();
		indexView.put(layoutView);
		List<Product> products = new ArrayList<>();
		products = productsModel.getAllProducts();
		for (int i = 0; !products.isEmpty() && i <=20; i++) {
			PhoneView phone = new PhoneView(new String(resourceModel.get(PHONE_TEMPLATE).getData()));
			phone.setTitle(products.get(0).getModel());
			phone.setText(products.get(0).getDescription());
			phone.setPrice(products.get(0).getPrice());
			phone.setTags(new PhoneView.Tag(products.get(0).getManufacturer(), "index.html?man=" + products.get(0).getManufacturer()), new PhoneView.Tag(products.get(0).getOperating_system(), "index.html?os=" + products.get(0).getOperating_system()));
			phone.setImage(products.get(0).getPicture(), products.get(0).getModel() + " Phone");
			//phone.setImage("res/images/phones/phone8-400.jpg", products.get(0).getModel() + " Phone");
			layoutView.add(phone);
			products.remove(0);
		}

		resp.set(indexView).respondOk();
	}
}
