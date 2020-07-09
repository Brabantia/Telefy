package telefy;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileHandler implements HttpHandler {
	private final ResourceModel resourceModel;

	public FileHandler(ResourceModel resourdeModel) {
		this.resourceModel = resourdeModel;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		HttpRequest req = new HttpRequest(httpExchange);
		HttpResponse resp = new HttpResponse(httpExchange);

		Resource resource = this.resourceModel.get(req.getPath());
		if (resource == null) {
			resp.respondNotFound();
		} else {
			resp.set(new FileView(resource)).respondOk();
		}
	}
}