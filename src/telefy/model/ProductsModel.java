package telefy.model;

import java.util.List;
import telefy.entity.Product;

public interface ProductsModel {
	Product getProduct(int id);
	Product getProductById(String id);
	Product getProduct(String model);
	List<Product> getAllProducts();
	List<String> getAllOperatingSystems();
	List<String> getAllManufacturers();
	List<Product> getProductsWithOs(String operatingSystem);
	List<Product> getProductsFromManufacturer(String manufacturer);
}