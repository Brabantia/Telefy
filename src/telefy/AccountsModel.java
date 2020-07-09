package telefy;

public interface AccountsModel {
	public abstract Account[] getAllAccounts();
	public abstract Account getAccount(int id);
	public abstract Account getLogin(int id);
	public abstract Account getLogin(String email);
}