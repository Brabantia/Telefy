package telefy.view;

public class PhoneView extends TemplatedPageView {
	public static final String PRICE_TAG = "PRICE";
	public static final String TEXT_TAG = "TEXT";
	public static final String TITLE_TAG = "TITLE";
	public static final String TAGS_TAG = "TAGS";
	public static final String IMAGE_TAG = "IMAGE";
	public static final String TAG_START = "<a href=\"";
	public static final String TAG_MIDDLE = "\">";
	public static final String TAG_END = "</a>";
	public static final String IMAGE_START = "<img src=\"";
	public static final String IMAGE_MIDDLE = "\" alt=\"";
	public static final String IMAGE_END = "\">";
	
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
	
	public PhoneView(String template) {
		super(template);
	}
	
	public void setPrice(int price) {
		if (price < 0) {
			throw new RuntimeException("Price must be non-negative: " + price);
		}
		if (price == 0) {
			super.put(new HtmlPageView(PRICE_TAG, "FREE"));
		} else {
			super.put(new HtmlPageView(PRICE_TAG, "$"+price));
		}	
	}
	
	public void setTitle(String title) {
		super.put(new HtmlPageView(TITLE_TAG, title));
	}
	
	public void setText(String text) {
		super.put(new HtmlPageView(TEXT_TAG, text));
	}
	
	public void setTags(Tag... tags) {
		StringBuilder text = new StringBuilder();
		for (Tag tag : tags) {
			text.append(TAG_START+tag.getUrl()+TAG_MIDDLE+tag.getName()+TAG_END);
		}
		super.put(new HtmlPageView(TAGS_TAG, text.toString()));
	}
	
	public void setImage(String url, String altText) {
		if (altText == null) {
			altText = "";
		}
		
		super.put(new HtmlPageView(IMAGE_TAG, IMAGE_START+url+IMAGE_MIDDLE+altText+IMAGE_END));
	}
}
