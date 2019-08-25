package com.manas.rentalapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.Dao.AddProductToCartDao;
import com.manas.rentalapp.model.Cart;
import com.manas.rentalapp.model.CartItem;
import com.manas.rentalapp.model.Product;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.CartRepository;
import com.manas.rentalapp.repository.ProductRepository;
import com.manas.rentalapp.repository.UserProfileRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public boolean addProductToCart(AddProductToCartDao addProductToCartDao) {
		
		UserProfile user = userProfileRepository.findByEmail(addProductToCartDao.getEmail());
		if(user==null) {
			return false;
		}
		Optional<Cart> existingCart = cartRepository.findByUserProfileId(user.getId());
		Product product = productRepository.findById(addProductToCartDao.getProductId());
		if(product==null) {
			return false;
		}
		if(!existingCart.isPresent()) {
			Cart cart = new Cart();
			cart.setUserProfile(user);
					
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setProductAddedOn(new Date());
			cartItem.setQuantity(1);
			
			List<CartItem> cartItemList = new ArrayList<>();
			
			cartItemList.add(cartItem);
			cart.setCartItem(cartItemList);
			
			cartRepository.save(cart);
		}
		else {
			List<CartItem> cartItemList = existingCart.get().getCartItem();
			boolean alreadyExistInCart = false;
			for(CartItem cartItem : cartItemList) {
				if(cartItem.getProduct().getId()==addProductToCartDao.getProductId()) {
					cartItem.setQuantity(cartItem.getQuantity()+1);
					alreadyExistInCart = true;
				}
			}
			if(!alreadyExistInCart) {
				
				CartItem cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setProductAddedOn(new Date());
				cartItem.setQuantity(1);

				cartItemList.add(cartItem);	
			}
			cartRepository.save(existingCart.get());
		}
		
		return true;	
	}
}

