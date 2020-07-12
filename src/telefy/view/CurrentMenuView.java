package telefy.view;

public class CurrentMenuView extends HtmlPageView {
	public static final String ABOUT = "/about";
	public static final String CONTACT = "/contact";
	public static final String LOGIN = "/login";

	private final String tag;

	public CurrentMenuView(String path, String os, String manufacturer) {
		super();

		if (path.startsWith(ABOUT)) {
			this.tag = "MENU_CURRENT_ABOUT";
			super.add("<li class=\"current\">");
		} else if (path.startsWith(CONTACT)) {
			this.tag = "MENU_CURRENT_CONTACT";
			super.add("<li class=\"current\">");
		} else if (path.startsWith(LOGIN)) {
			this.tag = "MENU_CURRENT_LOGIN";
			super.add("<li class=\"current\">");
		} else if (os != null && !os.isEmpty()) {
			this.tag = "MENU_CURRENT_OS";
			super.add("<li class=\"current has-children\">");
		} else if (manufacturer != null && !manufacturer.isEmpty()) {
			this.tag = "MENU_CURRENT_MANUFACTURER";
			super.add("<li class=\"current has-children\">");
		} else {
			this.tag = "MENU_CURRENT_HOME";
			super.add("<li class=\"current\">");
		}
	}

	@Override
	public String getTag() {
		return this.tag;
	}
}