package telefy.controller;

import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;
import static telefy.controller.IndexHandler.PHONE_TEMPLATE;
import telefy.entity.Product;
import telefy.model.FileResourceModel;
import telefy.model.ProductsModel;
import telefy.view.PhoneView;
import telefy.view.TemplatedPageView;

public class PhoneHandler extends SafeHttpHandler {

	private final TemplateController templateController;
	private final FileResourceModel resourceModel;
	private final ProductsModel productsModel;

	public PhoneHandler(TemplateController tc, FileResourceModel rm, ProductsModel pm) {
		this.templateController = tc;
		this.resourceModel = rm;
		this.productsModel = pm;
	}

	@Override
	public void handle(HttpRequest req, HttpResponse resp) throws IOException {
		if (!req.contains("id")) {
			resp.respondBadRequest();
			return;
		}

		Product phone = this.productsModel.getProductById(req.get("id"));
		if (phone == null) {
			resp.respondNotFound();
			return;
		}

		TemplatedPageView template = this.templateController.getTemplatePage(req);
		resp.set(template);


		PhoneView phoneView = new PhoneView(new String(resourceModel.get(PHONE_TEMPLATE).getData()), PhoneView.SINGLE_VIEW);
		template.put(phoneView);

		String man = phone.getManufacturer();
		String os = phone.getOs();
		phoneView.setTags(new PhoneView.Tag(man, "index.html?man=" + man),
				new PhoneView.Tag(os, "index.html?os=" + os));
		phoneView.setLogo(os, phone.getId());
		phoneView.setImage(phone.getPicture(), phone.getModel());
		phoneView.setTitle(phone.getModel(), phone.getId());
		phoneView.setText(phone.getDescription());
		phoneView.setPrice(phone.getPrice());

		resp.set(template).respondOk();
	}

}