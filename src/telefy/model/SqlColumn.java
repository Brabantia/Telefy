package telefy.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlColumn<T> {
	private final String field;
	private final T sample;

	public SqlColumn(String field, T sample) {
		this.field = field;
		this.sample = sample;
	}

	public T getValue(ResultSet rs) {
		try {
			if(this.sample instanceof Boolean) {
				return (T)(Boolean.valueOf(rs.getBoolean(this.field)));
			} else if(this.sample instanceof Integer) {
				return (T)(Integer.valueOf(rs.getInt(this.field)));
			} else if(this.sample instanceof String) {
				return (T)(rs.getString(this.field));
			} else if(this.sample instanceof BigDecimal) {
				return (T)(rs.getBigDecimal(this.field));
			} else {
				return (T)(rs.getObject(this.field));
			}
		} catch (SQLException ignored) {}
		return null;
	}

	public boolean isPresentIn(ResultSet rs) {
		try {
			if(this.sample instanceof Boolean) {
				rs.getBoolean(this.field);
				return true;
			} else if(this.sample instanceof Integer) {
				rs.getInt(this.field);
				return true;
			} else if(this.sample instanceof String) {
				return rs.getString(this.field) != null;
			} else if(this.sample instanceof BigDecimal) {
				return rs.getBigDecimal(this.field) != null;
			}
		} catch (SQLException ignored) {}
		return false;
	}
}