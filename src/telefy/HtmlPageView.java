package telefy;

public class HtmlPageView implements View {
	private final StringBuilder html = new StringBuilder();

	public HtmlPageView() {
	}

	public HtmlPageView(String text) {
		this.html.append(text);
	}

	@Override
	public String getMime() {
		return "text/html";
	}

	@Override
	public int length() {
		return this.html.length();
	}

	@Override
	public byte[] getBytes() {
		return this.html.toString().getBytes();
	}

	public void add(String text) {
		html.append(text);
	}

	@Override
	public String toString() {
		return this.html.toString();
	}

	public String getTag() {
		return null;
	}
}