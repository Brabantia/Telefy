package telefy.view;

import java.util.HashMap;

public class TemplatedPageView extends HtmlPageView {
	public static final String JAVA_TAG = "<!--java-->";
	public static final String HTML_COMMENT_START = "<!--";
	public static final String HTML_COMMENT_END = "-->";

	private final HashMap<String, HtmlPageView> taggedViews = new HashMap<>();
	private final String template;
	private final String tag;
	private boolean built = false;

	public TemplatedPageView(String template) {
		this(null, template);
	}

	public TemplatedPageView(String tag, String template) {
		super();

		this.tag = tag;
		this.template = template;
	}

	private void build() {
		if (built) {
			return;
		}
		built = true;

		String[] sections = this.template.split(JAVA_TAG);
		boolean javaSection = true;
		for (String section : sections) {
			if (!section.startsWith(HTML_COMMENT_START)) {
				if (!javaSection) {
					System.err.println("The template is invalid as it contained two non-java sections next to each other:");
					System.err.println(this.template);
					throw new RuntimeException("Failed to process HTML template due to processing error.");
				}
				// Just add the section as is since there are no special java tags in it.
				super.add(section);
				javaSection = false;
				continue;
			}
			javaSection = true;
			int index = section.indexOf(HTML_COMMENT_END);
			if (index < 0) {
				System.err.println("Template has a section that starts an HTML comment but does not end it:");
				System.err.println(section);
				throw new RuntimeException("HTML Template is invalid due to incomplete comment.");
			}
			String tag = section.substring(HTML_COMMENT_START.length(), index);
			HtmlPageView view = this.taggedViews.get(tag);
			// Skip tags with missing views, printing the existing section instead.
			if (view == null) {
				// Strip off the leading comment.
				super.add(section.substring(index + HTML_COMMENT_END.length()));
			} else {
				super.add(view.toString());
			}
		}
	}

	@Override
	public int length() {
		if (!this.built) {
			build();
		}
		return super.length();
	}

	@Override
	public byte[] getBytes() {
		if (!this.built) {
			build();
		}
		return super.getBytes();
	}

	@Override
	public String toString() {
		if (!this.built) {
			build();
		}
		return super.toString();
	}

	public boolean built() {
		return this.built;
	}

	public void put(HtmlPageView view) {
		if (this.built) {
			throw new RuntimeException("Cannot add views to a template that has already been compiled.");
		}
		this.taggedViews.put(view.getTag(), view);
	}

	@Override
	public String getTag() {
		return this.tag;
	}
}