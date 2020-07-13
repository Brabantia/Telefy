package telefy.model;

import java.util.List;
import telefy.entity.Product;

public interface ProductsModel {
	public Product getProduct(int id);
	public Product getProduct(String model);
	public List<Product> getAllProducts();
	public List<String> getAllOperatingSystems();
	public List<String> getAllManufacturers();
	public List<Product> getProductsWithOs(String operatingSystem);
	public List<Product> getProductsFromManufacturer(String manufacturer);
}