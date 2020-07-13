package telefy.model;

import telefy.entity.Account;

public interface AccountsModel {
	public Account[] getAllAccounts();
	public Account getAccount(int id);
	public Account getLogin(int id);
	public Account getLoginById(String id);
	public Account getLogin(String email);
}