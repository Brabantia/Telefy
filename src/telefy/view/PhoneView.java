package telefy.view;

import java.math.BigDecimal;

public class PhoneView extends TemplatedPageView {
	public static final boolean GRID_VIEW = true;
	public static final boolean SINGLE_VIEW = false;
	public static final String PRICE_TAG = "PRICE";
	public static final String TEXT_TAG = "TEXT";
	public static final String TITLE_TAG = "TITLE";
	public static final String TAGS_TAG = "TAGS";
	public static final String IMAGE_TAG = "IMAGE";
	public static final String LOGO_TAG = "LOGO";
	public static final String TAG_START = "<a href=\"";
	public static final String TAG_MIDDLE = "\">";
	public static final String TAG_END = "</a>";
	public static final String TITLE_START = "<h1 class=\"entry__title\"><a href=\"phone?id=";
	public static final String TITLE_MIDDLE = "\">";
	public static final String TITLE_END = "</a></h1>";
	public static final String IMAGE_START = "<img src=\"";
	public static final String IMAGE_MIDDLE = "\" alt=\"";
	public static final String IMAGE_END = "\">";
	public static final String LOGO_START = "<a href=\"phone?id=";
	public static final String LOGO_MIDDLE = "\" class=\"entry__thumb-link ";
	public static final String LOGO_END = "__logo\">";

	public static class Tag {
		private final String name, url;
		public Tag(String name, String url) {
			this.name = name;
			this.url = url;
		}
		/**
		 * @return the tag
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}
	}

	public PhoneView(String template, boolean gridView) {
		super(template);
		if (gridView == GRID_VIEW) {
			super.put(new HtmlPageView("SINGLE", ""));
		} else if (gridView == SINGLE_VIEW) {
			super.put(new HtmlPageView("GRID", ""));
		}
	}

	public void setPrice(BigDecimal price) {
		if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
			return;
		}
		if (price.doubleValue() < 0) {
			throw new RuntimeException("Price must be non-negative: " + price);
		}
		if (BigDecimal.ZERO.equals(price)) {
			super.put(new HtmlPageView(PRICE_TAG, "FREE"));
		} else {
			super.put(new HtmlPageView(PRICE_TAG, "$"+price.setScale(2)));
		}
	}

	public void setTitle(String title, int id) {
		super.put(new HtmlPageView(TITLE_TAG, TITLE_START+id+TITLE_MIDDLE+title+TITLE_END));
	}

	public void setText(String text) {
		super.put(new HtmlPageView(TEXT_TAG, text));
	}

	public void setTags(Tag... tags) {
		StringBuilder text = new StringBuilder();
		for (Tag tag : tags) {
			text.append(TAG_START).append(tag.getUrl()).append(TAG_MIDDLE).append(tag.getName()).append(TAG_END);
		}
		super.put(new HtmlPageView(TAGS_TAG, text.toString()));
	}

	public void setImage(String url, String altText) {
		if (url == null || url.isEmpty()) {
			return;
		}
		if (altText == null) {
			altText = "";
		}

		super.put(new HtmlPageView(IMAGE_TAG, IMAGE_START+url+IMAGE_MIDDLE+altText+IMAGE_END));
	}

	public void setLogo(String logo, int id) {
		if (logo == null || logo.isEmpty()) {
			return;
		}
		super.put(new HtmlPageView(LOGO_TAG, LOGO_START+id+LOGO_MIDDLE+logo.toLowerCase()+LOGO_END));
	}

	public String getTag() {
		return "CONTENT";
	}
}
