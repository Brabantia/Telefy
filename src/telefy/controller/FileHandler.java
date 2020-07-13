package telefy.controller;

import telefy.model.ResourceModel;
import telefy.entity.Resource;
import telefy.view.FileView;
import java.io.IOException;
import telefy.HttpRequest;
import telefy.HttpResponse;

public class FileHandler extends SafeHttpHandler {
	private final ResourceModel resourceModel;

	public FileHandler(ResourceModel resourdeModel) {
		this.resourceModel = resourdeModel;
	}

	@Override
	public void handle(HttpRequest req, HttpResponse resp) throws IOException {
		Resource resource = this.resourceModel.get(req.getPath());
		if (resource == null) {
			resp.respondNotFound();
		} else {
			resp.set(new FileView(resource)).respondOk();
		}
	}
}