package telefy.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
	private int id;
	private String model;
	private String manufacturer;
	private String description;
	private String operating_system;
	private String picture;
	private String application;
	private BigDecimal price;
	private String status;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the operating_system
	 */
	public String getOs() {
		return operating_system;
	}

	/**
	 * @param operating_system the operating_system to set
	 */
	public void setOs(String operating_system) {
		this.operating_system = operating_system;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the application
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();

		text.append("Product[");
		text.append("\nid=").append(id);
		text.append("\nmodel=").append(model);
		text.append("\nmanufacturer=").append(manufacturer);
		text.append("\ndescription=").append(description);
		text.append("\noperating_system=").append(operating_system);
		text.append("\npicture=").append(picture);
		text.append("\napplication=").append(application);
		text.append("\nprice=").append(price);
		text.append("\nstatus=").append(status);
		text.append("\n]");

		return text.toString();
	}
}
