package com.manas.rentalapp.Dao;
import java.util.Map;

import com.manas.rentalapp.model.ProductCategory;

public class ProductDao {

	private String title;
	
	private String description;
	
	private String imageUrl;
	
	private int availableQuantity;
	
	private Map<String,String> price;
	
	private ProductCategory productCategory;
	
	private boolean isActive;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}



	public Map<String, String> getPrice() {
		return price;
	}

	public void setPrice(Map<String, String> price) {
		this.price = price;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
