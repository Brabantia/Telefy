package telefy;

class ManufacturerMenuTagView extends HtmlPageView {
	public static final String TAG = "MANUFACTURER_MENU";

	public static final String MENU_ITEM_START_CURRENT = "<li class=\"current\"><a href=\"index.html?man=";
	public static final String MENU_ITEM_START = "<li><a href=\"index.html?man=";
	public static final String MENU_ITEM_MIDDLE = "\">";
	public static final String MENU_ITEM_END = "</a></li>";

	public ManufacturerMenuTagView(String current, String... manufacturers) {
		super();

		for (String manufactuerer : manufacturers) {
			if (manufactuerer.equals(current)) {
				super.add(MENU_ITEM_START_CURRENT);
			} else {
				super.add(MENU_ITEM_START);
			}

			super.add(manufactuerer);
			super.add(MENU_ITEM_MIDDLE);
			super.add(manufactuerer);
			super.add(MENU_ITEM_END);
		}
	}

	@Override
	public String getTag() {
		return ManufacturerMenuTagView.TAG;
	}
}