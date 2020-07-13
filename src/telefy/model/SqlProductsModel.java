package telefy.model;

import java.sql.Statement;
import java.sql.Connection;
import telefy.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlProductsModel implements ProductsModel {
	private final Connection sqlServer;

	public SqlProductsModel(Connection sqlServer) {
		this.sqlServer = sqlServer;
	}

	@Override
	public Product getProduct(int id) {
		String query = "SELECT * FROM Product WHERE product_id = " + id + ";";
		
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				Statement stmt = this.sqlServer.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				if (!rs.next()) {
					return new Product();
				}

				Product product = new Product();
				product.setId(rs.getInt("product_id"));
				product.setModel(rs.getString("model"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setDescription(rs.getString("description"));
				product.setOperating_system(rs.getString("operating_system"));
				product.setPicture(rs.getString("picture"));
				product.setApplication(rs.getString("application"));
				product.setPrice(rs.getInt("price"));
				product.setStatus(rs.getString("product_status"));
				return product;
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
				Statement stmt = this.sqlServer.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				if (!rs.next()) {
					return new Product();
				}

				Product product = new Product();
				product.setId(rs.getInt("product_id"));
				product.setModel(rs.getString("model"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setDescription(rs.getString("description"));
				product.setOperating_system(rs.getString("operating_system"));
				product.setPicture(rs.getString("picture"));
				product.setApplication(rs.getString("application"));
				product.setPrice(rs.getInt("price"));
				product.setStatus(rs.getString("product_status"));
				return product;
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		String query = "SELECT * FROM Product ORDER BY price DESC;";
		List<Product> products = new ArrayList<>();
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				Statement stmt = this.sqlServer.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while((rs.next())) {					
					Product product = new Product();
					product.setId(rs.getInt("product_id"));
					product.setModel(rs.getString("model"));
					product.setManufacturer(rs.getString("manufacturer"));
					product.setDescription(rs.getString("description"));
					product.setOperating_system(rs.getString("operating_system"));
					product.setPicture(rs.getString("picture"));
					product.setApplication(rs.getString("application"));
					product.setPrice(rs.getInt("price"));
					product.setStatus(rs.getString("product_status"));
					products.add(product);
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
		List<String> os = new ArrayList<>();
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				Statement stmt = this.sqlServer.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					os.add(rs.getString("operating_system"));
				}
				System.out.println(java.util.Arrays.toString(os.toArray()));
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
			List<String> manufacturers = new ArrayList<>();
			try {
				Statement stmt = this.sqlServer.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
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
		List<Product> products = new ArrayList<>();
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				Statement stmt = this.sqlServer.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while((rs.next())) {					
					Product product = new Product();
					product.setId(rs.getInt("product_id"));
					product.setModel(rs.getString("model"));
					product.setManufacturer(rs.getString("manufacturer"));
					product.setDescription(rs.getString("description"));
					product.setOperating_system(rs.getString("operating_system"));
					product.setPicture(rs.getString("picture"));
					product.setApplication(rs.getString("application"));
					product.setPrice(rs.getInt("price"));
					product.setStatus(rs.getString("product_status"));
					products.add(product);
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
		List<Product> products = new ArrayList<>();
		for (int attempt = 1; attempt <= 3; ++attempt) {
			try {
				Statement stmt = this.sqlServer.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while((rs.next())) {					
					Product product = new Product();
					product.setId(rs.getInt("product_id"));
					product.setModel(rs.getString("model"));
					product.setManufacturer(rs.getString("manufacturer"));
					product.setDescription(rs.getString("description"));
					product.setOperating_system(rs.getString("operating_system"));
					product.setPicture(rs.getString("picture"));
					product.setApplication(rs.getString("application"));
					product.setPrice(rs.getInt("price"));
					product.setStatus(rs.getString("product_status"));
					products.add(product);
				}
				return products;
			} catch (SQLException ex) {
				Logger.getLogger(SqlProductsModel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

}
