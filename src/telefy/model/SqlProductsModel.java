package telefy.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import telefy.entity.Product;

public class SqlProductsModel extends SqlModel<Product> implements ProductsModel {
	private static final SqlColumn<Integer> ID = new SqlColumn<>("product_id", 0);
	private static final SqlColumn<String> MODEL = new SqlColumn<>("model", "");
	private static final SqlColumn<String> MANUFACTURER = new SqlColumn<>("manufacturer", "");
	private static final SqlColumn<String> DESCRIPTION = new SqlColumn<>("description", "");
	private static final SqlColumn<String> OS = new SqlColumn<>("operating_system", "");
	private static final SqlColumn<String> PICTURE = new SqlColumn<>("picture", "");
	private static final SqlColumn<String> APP = new SqlColumn<>("application", "");
	private static final SqlColumn<BigDecimal> PRICE = new SqlColumn<>("price", new BigDecimal(0));
	private static final SqlColumn<String> STATUS = new SqlColumn<>("product_status", "");
	private final Connection sqlServer;

	public SqlProductsModel(Connection sqlServer) {
		this.sqlServer = sqlServer;
	}

	@Override
	public Product parseRow(ResultSet rs) {
		Product product = new Product();

		if (APP.isPresentIn(rs)) {
			product.setApplication(APP.getValue(rs));
		}
		if (ID.isPresentIn(rs)) {
			product.setId(ID.getValue(rs));
		}
		if (MODEL.isPresentIn(rs)) {
			product.setModel(MODEL.getValue(rs));
		}
		if (MANUFACTURER.isPresentIn(rs)) {
			product.setManufacturer(MANUFACTURER.getValue(rs));
		}
		if (DESCRIPTION.isPresentIn(rs)) {
			product.setDescription(DESCRIPTION.getValue(rs));
		}
		if (OS.isPresentIn(rs)) {
			product.setOs(OS.getValue(rs));
		}
		if (PICTURE.isPresentIn(rs)) {
			product.setPicture(PICTURE.getValue(rs));
		}
		if (APP.isPresentIn(rs)) {
			product.setApplication(APP.getValue(rs));
		}
		if (PRICE.isPresentIn(rs)) {
			product.setPrice(PRICE.getValue(rs));
		}
		if (STATUS.isPresentIn(rs)) {
			product.setStatus(STATUS.getValue(rs));
		}

		return product;
	}

	@Override
	public Product getProduct(int id) {
		String query = "SELECT * FROM Product WHERE product_id = " + id + ";";
		List<Product> products = super.select(this.sqlServer, query);
		if (products == null || products.size() <= 0) {
			return null;
		}
		return products.get(0);
	}

	@Override
	public Product getProduct(String model) {
		String query = "SELECT * FROM Product WHERE model = '" + model + "';";
		List<Product> products = super.select(this.sqlServer, query);
		if (products == null || products.size() <= 0) {
			return null;
		}
		return products.get(0);
	}

	@Override
	public List<Product> getAllProducts() {
		String query = "SELECT * FROM Product ORDER BY price DESC;";
		return super.select(this.sqlServer, query);
	}

	@Override
	public List<String> getAllOperatingSystems() {
		String query = "SELECT DISTINCT operating_system FROM Product ORDER BY operating_system ASC;";
		List<Product> products = super.select(this.sqlServer, query);
		ArrayList<String> os = new ArrayList<>();
		for (Product p : products) {
			os.add(p.getOs());
		}
		return os;
	}

	@Override
	public List<String> getAllManufacturers() {
		String query = "SELECT DISTINCT manufacturer FROM Product ORDER BY manufacturer ASC;";
		List<Product> products = super.select(this.sqlServer, query);
		ArrayList<String> manufacturers = new ArrayList<>();
		for (Product p : products) {
			manufacturers.add(p.getManufacturer());
		}
		return manufacturers;
	}

	@Override
	public List<Product> getProductsWithOs(String operatingSystem) {
		String query = "SELECT * FROM PRODUCT WHERE operating_system = '" + operatingSystem + "' ORDER BY price DESC;";
		return super.select(this.sqlServer, query);
	}

	@Override
	public List<Product> getProductsFromManufacturer(String manufacturer) {
		String query = "SELECT * FROM PRODUCT WHERE manufacturer = '" + manufacturer + "' ORDER BY price DESC;";
		return super.select(this.sqlServer, query);
	}

	@Override
	public Product getProductById(String id) {
		try {
			return getProduct(Integer.parseInt(id));
		} catch (NumberFormatException ignored) {
			return new Product();
		}
	}
}
