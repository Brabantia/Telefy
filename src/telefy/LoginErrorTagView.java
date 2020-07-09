package telefy;

public class LoginErrorTagView extends HtmlPageView {
	public static final String TAG = "ERROR";
	public static final String PRE = "<p style=\"text-align:center; width:100%\">";
	public static final String POST = "</p>";

	public LoginErrorTagView(String message) {
		super();

		super.add(PRE);
		super.add(message);
		super.add(POST);
	}

	@Override
	public String getTag() {
		return LoginErrorTagView.TAG;
	}
}