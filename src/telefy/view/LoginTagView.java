package telefy.view;

import telefy.entity.Account;

public class LoginTagView extends HtmlPageView {
	public static final String TAG = "LOGIN";
	public static final String PATH = "/login";
	public static final String LOGGED_IN_START = "<li class=\"has-children";
	public static final String LOGGED_IN_CURRENT = " current";
	public static final String LOGGED_IN_MIDDLE = "\"><a href=\"account\">Account (";
	public static final String LOGGED_IN_END = ")</a>";
	public static final String SUBMENU_START = "<ul class=\"sub-menu\">";
	public static final String SUBMENU_END = "</ul>";
	public static final String LOGGED_OUT_START = "<li";
	public static final String LOGGED_OUT_CURRENT = " class=\"current\"";
	public static final String LOGGED_OUT_END = "><a href=\"login\" title=\"\">Login</a></li>";

	public LoginTagView(String path, Account user) {
		super();

		if (user == null || user.getName() == null || user.getName().isEmpty()) {
			super.add(LOGGED_OUT_START);
			if (path.startsWith(PATH)) {
				super.add(LOGGED_OUT_CURRENT);
			}
			super.add(LOGGED_OUT_END);
		} else {
			super.add(LOGGED_IN_START);
			if (path.startsWith(PATH)) {
				super.add(LOGGED_IN_CURRENT);
			}
			super.add(LOGGED_IN_MIDDLE);
			super.add(user.getName());
			super.add(LOGGED_IN_END);
			super.add(SUBMENU_START);
			super.add(makeMenuItem("account", "Account Settings"));
			if (user.isAdmin()) {
				super.add(makeMenuItem("reload", "Reload"));
			}
			super.add(makeMenuItem("logout", "Logout"));
			super.add(SUBMENU_END);
		}
	}

	@Override
	public String getTag() {
		return LoginTagView.TAG;
	}

	private static String makeMenuItem(String url, String label) {
		return "<li><a href=\"" + url + "\">" + label + "</a></li>";
	}
}