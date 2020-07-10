package telefy.view;

public class OsMenuTagView extends HtmlPageView {
	public static final String TAG = "OS_MENU";

	public static final String MENU_ITEM_START_CURRENT = "<li class=\"current\"><a href=\"index.html?os=";
	public static final String MENU_ITEM_START = "<li><a href=\"index.html?os=";
	public static final String MENU_ITEM_MIDDLE = "\">";
	public static final String MENU_ITEM_END = "</a></li>";

	public OsMenuTagView(String current, String... operatingSystems) {
		super();

		for (String os : operatingSystems) {
			if (os.equals(current)) {
				super.add(MENU_ITEM_START_CURRENT);
			} else {
				super.add(MENU_ITEM_START);
			}

			super.add(os);
			super.add(MENU_ITEM_MIDDLE);
			super.add(os);
			super.add(MENU_ITEM_END);
		}
	}

	@Override
	public String getTag() {
		return OsMenuTagView.TAG;
	}
}