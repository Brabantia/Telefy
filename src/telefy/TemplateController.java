package telefy;

class TemplateController {
	public static final String TEMPLATE_FILE = "templates/template.html";
	private final ResourceModel resourceModel;

	public TemplateController(ResourceModel resourceModel) {
		this.resourceModel = resourceModel;
	}

	TemplatedPageView getTemplatePage(HttpRequest req) {
		Resource template = this.resourceModel.get(TEMPLATE_FILE);
		TemplatedPageView view = new TemplatedPageView(new String(template.data));

		view.put(new LoginTagView(req.getPath(), req.get("user_name")));
		view.put(new OsMenuTagView(req.get("os"), "Android", "iOS", "Windows", "Blackberry"));
		view.put(new ManufacturerMenuTagView(req.get("man"), "Apple", "Blackberry", "Google", "LG", "Samsung"));
		view.put(new CurrentMenuView(req.getPath(), req.get("os"), req.get("man")));

		return view;
	}

}