package telefy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlAccountsModel implements AccountsModel {
	Connection sqlServer;

	public SqlAccountsModel(Connection sqlServer) {
		this.sqlServer = sqlServer;
	}

	@Override
	public Account[] getAllAccounts() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Account getAccount(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Account getLogin(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Account getLogin(String email) {
		String query = "SELECT account_id, email, name, password_hash FROM Account WHERE email = '" + email + "';";

		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				Statement stmt = this.sqlServer.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				if (!rs.next()) {
					return new Account();
				}

				Account account = new Account();
				account.setId(rs.getInt("account_id"));
				account.setName(rs.getString("name"));
				account.setEmail(rs.getString("email"));
				account.setPasswordHash(rs.getString("password_hash"));
				return account;
			} catch (SQLException ex) {
				Logger.getLogger(SqlAccountsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

}