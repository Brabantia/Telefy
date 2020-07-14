package telefy.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SqlModel<T> {
	public static final int RETRIES = 3;

	public abstract T parseRow(ResultSet rs);

	public List<T> parse(ResultSet rs) {
		List<T> items = new ArrayList<>();

		try {
			while (rs.next()) {
				items.add(parseRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return items;
	}

	public List<T> select(Connection conn, String query) {
		for (int attempt = 0; attempt < RETRIES; ++attempt) {
			try {
				ResultSet rs = conn.createStatement().executeQuery(query);
				return parse(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}