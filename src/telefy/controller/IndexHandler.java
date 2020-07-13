package telefy.controller;

import telefy.model.ResourceModel;
import telefy.view.PhoneView;
import telefy.view.GridLayoutView;
import telefy.view.TemplatedPageView;
import java.io.IOException;
import java.util.List;
import telefy.HttpRequest;
import telefy.HttpResponse;
import telefy.entity.Product;
import telefy.model.FileResourceModel;
import telefy.model.ProductsModel;

public class IndexHandler extends SafeHttpHandler {

	public static final String PHONE_TEMPLATE = "templates/phone.html";
	public static final int PHONES_PER_PAGE = 20;

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

		List<Product> phoneList;
		if (req.contains("os")) {
			phoneList = this.productsModel.getProductsWithOs(req.get("os"));
		} else if (req.contains("man")) {
			phoneList = this.productsModel.getProductsFromManufacturer(req.get("man"));
		} else {
			phoneList = this.productsModel.getAllProducts();
		}

		int max = PHONES_PER_PAGE;
		for (Product phone : phoneList) {
			if (--max <= 0) {
				break;
			}
			PhoneView view = new PhoneView(new String(resourceModel.get(PHONE_TEMPLATE).getData()));

			String man = phone.getManufacturer();
			String os = phone.getOperating_system();
			view.setTags(new PhoneView.Tag(man, "index.html?man=" + man),
					new PhoneView.Tag(os, "index.html?os=" + os));
			view.setImage(phone.getPicture(), phone.getModel());
			view.setTitle(phone.getModel());
			view.setText(phone.getDescription());
			view.setPrice(phone.getPrice());

			layoutView.add(view);
		}

		resp.set(indexView).respondOk();
	}
}
