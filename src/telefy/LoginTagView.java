package telefy;

public class LoginTagView extends HtmlPageView {
	public static final String TAG = "LOGIN";
	public static final String PATH = "/login";
	public static final String LOGGED_IN_START = "<li class=\"has-children";
	public static final String LOGGED_IN_CURRENT = " current";
	public static final String LOGGED_IN_MIDDLE = "\"><a href=\"account\">Account (";
	public static final String LOGGED_IN_END = ")</a><ul class=\"sub-menu\"><li><a href=\"account\">Account Settings</a></li><li><a href=\"logout\">Logout</a></li></ul>";
	public static final String LOGGED_OUT_START = "<li";
	public static final String LOGGED_OUT_CURRENT = " class=\"current\"";
	public static final String LOGGED_OUT_END = "><a href=\"login\" title=\"\">Login</a></li>";

	public LoginTagView(String path, String userFullName) {
		super();

		if (userFullName == null || userFullName.isEmpty()) {
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
			super.add(userFullName);
			super.add(LOGGED_IN_END);
		}
	}

	@Override
	public String getTag() {
		return LoginTagView.TAG;
	}
}