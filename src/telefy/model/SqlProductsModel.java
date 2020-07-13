package telefy.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import telefy.entity.Product;

public class SqlProductsModel implements ProductsModel {

	private static final String[] COLUMNS = {"product_id", "model", "manufacturer", "description", "operating_system", "picture", "application", "price", "product_status"};
	private final Connection sqlServer;

	public SqlProductsModel(Connection sqlServer) {
		this.sqlServer = sqlServer;
	}

	private Product parseRow(ResultSet rs) throws SQLException {
		int[] indices = new int[COLUMNS.length];

		for (int a = 0; a < COLUMNS.length; ++a) {
			indices[a] = -1;
			try {
				indices[a] = rs.findColumn(COLUMNS[a]);
			} catch (SQLException ignored) {
			} // Ignore as the column was just missing in this query.
		}
		Product product = new Product();

		if (indices[0] >= 0) {
			product.setId(rs.getInt(indices[0]));
		}
		if (indices[1] >= 0) {
			product.setModel(rs.getString(indices[1]));
		}
		if (indices[2] >= 0) {
			product.setManufacturer(rs.getString(indices[2]));
		}
		if (indices[3] >= 0) {
			product.setDescription(rs.getString(indices[3]));
		}
		if (indices[4] >= 0) {
			product.setOperating_system(rs.getString(indices[4]));
		}
		if (indices[5] >= 0) {
			product.setPicture(rs.getString(indices[5]));
		}
		if (indices[6] >= 0) {
			product.setApplication(rs.getString(indices[6]));
		}
		if (indices[7] >= 0) {
			product.setPrice(rs.getInt(indices[7]));
		}
		if (indices[8] >= 0) {
			product.setStatus(rs.getString(indices[8]));
		}

		return product;
	}

	@Override
	public Product getProduct(int id) {
		String query = "SELECT * FROM Product WHERE product_id = " + id + ";";

		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				ResultSet rs = this.sqlServer.createStatement().executeQuery(query);
				return rs.next() ? parseRow(rs) : new Product();
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	@Override
	public Product getProduct(String model) {
		String query = "SELECT * FROM Product WHERE model = '" + model + "';";

		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				ResultSet rs = this.sqlServer.createStatement().executeQuery(query);
				return rs.next() ? parseRow(rs) : new Product();
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		String query = "SELECT * FROM Product ORDER BY price DESC;";
		ArrayList<Product> products = new ArrayList<>();
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				ResultSet rs = this.sqlServer.createStatement().executeQuery(query);
				while (rs.next()) {
					products.add(parseRow(rs));
				}
				return products;
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	@Override
	public List<String> getAllOperatingSystems() {
		String query = "SELECT DISTINCT operating_system FROM Product ORDER BY operating_system ASC;";
		ArrayList<String> os = new ArrayList<>();
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				ResultSet rs = this.sqlServer.createStatement().executeQuery(query);
				while (rs.next()) {
					os.add(rs.getString("operating_system"));
				}
				return os;
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	@Override
	public List<String> getAllManufacturers() {
		String query = "SELECT DISTINCT manufacturer FROM Product ORDER BY manufacturer ASC;";
		for (int attempt = 1; attempt <= 3; ++attempt) {
			ArrayList<String> manufacturers = new ArrayList<>();
			try {
				ResultSet rs = this.sqlServer.createStatement().executeQuery(query);
				while (rs.next()) {
					manufacturers.add(rs.getString("manufacturer"));
				}
				return manufacturers;
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	@Override
	public List<Product> getProductsWithOs(String operatingSystem) {
		String query = "SELECT * FROM PRODUCT WHERE operating_system = '" + operatingSystem + "';";
		ArrayList<Product> products = new ArrayList<>();
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				ResultSet rs = this.sqlServer.createStatement().executeQuery(query);
				while (rs.next()) {
					products.add(parseRow(rs));
				}
				return products;
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	@Override
	public List<Product> getProductsFromManufacturer(String manufacturer) {
		String query = "SELECT * FROM PRODUCT WHERE manufacturer = '" + manufacturer + "';";
		ArrayList<Product> products = new ArrayList<>();
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				ResultSet rs = this.sqlServer.createStatement().executeQuery(query);
				while (rs.next()) {
					products.add(parseRow(rs));
				}
				return products;
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

}
