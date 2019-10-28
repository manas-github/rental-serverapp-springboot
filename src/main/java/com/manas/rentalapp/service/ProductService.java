package com.manas.rentalapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manas.rentalapp.model.ProductCategory;
import com.manas.rentalapp.Dao.ProductDao;
import com.manas.rentalapp.model.Product;
import com.manas.rentalapp.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public List<Product> getAllActiveProducts(){
		
		List<Product> products = productRepository.findAll()
			.stream()
			.filter(product -> product.isActive())
			.collect(Collectors.toList());
		return products;
	}
	

	@Transactional
	public List<Product> getAllActiveFurnitures(){
		
		List<Product> products = productRepository.findAll()
			.stream()
			.filter(product -> product.isActive() && product.getProductCategory()==ProductCategory.FURNITURE)
			.collect(Collectors.toList());
		return products;
	}
	
	@Transactional
	public List<Product> getAllActiveAppliances(){
		
		List<Product> products = productRepository.findAll()
			.stream()
			.filter(product -> product.isActive() && product.getProductCategory()==ProductCategory.APPLIANCE)
			.collect(Collectors.toList());
		return products;
	}
	
	@Transactional
	public Product getProductById(long productId) {
		
		return productRepository.findById(productId);
	}

	
	@Transactional
	public boolean addProduct(ProductDao productDao) {
		
		Product product = new Product();
		product.setTitle(productDao.getTitle());
		product.setProductCategory(productDao.getProductCategory());
		product.setPrice(productDao.getPrice());
		product.setImageUrl(productDao.getImageUrl());
		product.setAvailableQuantity(productDao.getAvailableQuantity());
		product.setActive(productDao.isActive());
		product.setDescription(productDao.getDescription());
		
		if(productRepository.save(product)!=null) {
			return true;
		}
		return false;
				
	}
	
	@Transactional
	public List<Product> getProductBySearchingTitle(String searchKey) {
		List<Product> products = productRepository.findAll()
				.stream()
				.filter(product -> product.isActive() && (product.getTitle().toLowerCase().contains(searchKey.toLowerCase()) || product.getDescription().toLowerCase().contains(searchKey.toLowerCase())))
				.collect(Collectors.toList());
			return products;
	}
	
	@Transactional
	public List<String> getProductTitleByHotSearch(String searchKey){
		List<String> productsTtitle = new ArrayList<>();
		productRepository
				.findAll()
				.stream()
				.forEach(product ->{
					if(product.getTitle().toLowerCase().contains(searchKey.toLowerCase()))
						productsTtitle.add(product.getTitle());
				});
		return productsTtitle;
	}
}
