package telefy.view;

import java.util.ArrayList;

public class GridLayoutView extends HtmlPageView {
	public static final String START = "<div class=\"row masonry-wrap\"><div class=\"masonry\"><div class=\"grid-sizer\"></div>";
	public static final String END = "</div></div>";
	ArrayList<HtmlPageView> views = new ArrayList<>();
	private boolean built = false;
	
	public GridLayoutView() {
		super();
	}
	
	private void build() {
		if (this.built) {
			return;
		}
		this.built = true;
		
		super.add(START);
		for (HtmlPageView view : this.views) {
			super.add(view.toString());
		}
		super.add(END);
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

	@Override
	public String getTag() {
		return "CONTENT";
	}
	
	public void add(HtmlPageView view) {
		this.views.add(view);
	}
}
