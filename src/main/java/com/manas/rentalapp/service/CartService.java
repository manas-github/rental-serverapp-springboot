package com.manas.rentalapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.Dao.CartDao;
import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.dto.CartDto;
import com.manas.rentalapp.dto.UserDto;
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
	
	boolean isUpdatedQuantityZero= false;

	int counter =0;
	
	@Transactional
	public CartDto getCart(UserDao userDao) {
		UserProfile user = userProfileRepository.findByEmail(userDao.getEmail());

		Optional<Cart> cart = cartRepository.findByUserProfileId(user.getId());
		if(cart.isPresent()) {
			CartDto cartDto = new CartDto();
			cartDto.setId(cart.get().getId());
			
			UserDto userDto = new UserDto();
			userDto.setId(cart.get().getUserProfile().getId());
			userDto.setFirstName(cart.get().getUserProfile().getFirstName());
			userDto.setLastName(cart.get().getUserProfile().getLastName());
			userDto.setMobile(cart.get().getUserProfile().getMobile());
			userDto.setEmail(cart.get().getUserProfile().getEmail());
			cartDto.setUser(userDto);
			cartDto.setCartItem(cart.get().getCartItem());
			return cartDto;
		}
		else
			return new CartDto();
	}
	
	@Transactional
	public boolean addProductToCart(CartDao cartDao) {
		UserProfile user = userProfileRepository.findByEmail(cartDao.getEmail());
		if(user==null) {
			return false;
		}
		Optional<Cart> existingCart = cartRepository.findByUserProfileId(user.getId());
		Product product = productRepository.findById(cartDao.getProductId());
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
			cartItem.setDuration(cartDao.getDuration());
			
			List<CartItem> cartItemList = new ArrayList<>();
			
			cartItemList.add(cartItem);
			cart.setCartItem(cartItemList);
			
			cartRepository.save(cart);
		}
		else {
			List<CartItem> cartItemList = existingCart.get().getCartItem();
			boolean alreadyExistInCart = false;
			for(CartItem cartItem : cartItemList) {
				if(cartItem.getProduct().getId()==cartDao.getProductId() && cartDao.getDuration()==cartItem.getDuration()) {
					cartItem.setQuantity(cartItem.getQuantity()+1);
					alreadyExistInCart = true;
				}
			}
			if(!alreadyExistInCart) {
				
				CartItem cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setProductAddedOn(new Date());
				cartItem.setQuantity(1);
				cartItem.setDuration(cartDao.getDuration());

				cartItemList.add(cartItem);	
			}
			cartRepository.save(existingCart.get());
		}
		
		return true;	
	}

	@Transactional
	public boolean removeProductFromCart(CartDao cartDao) {
		UserProfile user = userProfileRepository.findByEmail(cartDao.getEmail());
		if(user==null) {
			return false;
		}
		Optional<Cart> existingCart = cartRepository.findByUserProfileId(user.getId());
		Product product = productRepository.findById(cartDao.getProductId());
		if(product==null) {
			return false;
		}
		
		if(existingCart.isPresent()) {
			List<CartItem> cartItemList = existingCart.get().getCartItem();
			int cartItemCount=cartItemList.size();

			cartItemList.removeIf(cartItem ->
				(cartItem.getProduct().getId() == cartDao.getProductId() && cartItem.getDuration()==cartDao.getDuration())
			);
			if(cartItemList.size()==cartItemCount) {
				return false;
			}
			else if(cartItemCount - cartItemList.size()==1) {
				existingCart.get().setCartItem(cartItemList);
				cartRepository.save(existingCart.get());
				return true;
			}
			else {
				System.out.println("Error in removing item from cart");
				return false;
			}
		}
		return false;
	}

	@Transactional
	public boolean decreaseQuantity(CartDao cartDao) {
		Product product = productRepository.findById(cartDao.getProductId());
		UserProfile user = userProfileRepository.findByEmail(cartDao.getEmail());
		if(user==null) {
			return false;
		}
		Optional<Cart> existingCart = cartRepository.findByUserProfileId(user.getId());
		if(product==null) {
			return false;
		}
		
		if(existingCart.isPresent()) {
			counter=0;
			isUpdatedQuantityZero = false;
			List<CartItem> cartItemList = existingCart.get().getCartItem();
			cartItemList.stream().forEach(cartItem ->{
				if(cartItem.getProduct().getId()==cartDao.getProductId() && cartDao.getDuration() == cartItem.getDuration()) {
					cartItem.setQuantity(cartItem.getQuantity()-1);
					counter++;
					if(cartItem.getQuantity()==0) {
						isUpdatedQuantityZero=true;
					}
				}
			});
			if(counter==1) {
				if(isUpdatedQuantityZero) {
					return removeProductFromCart(cartDao);
				}
				else {
					existingCart.get().setCartItem(cartItemList);
					cartRepository.save(existingCart.get());	
					return true;
				}
			}
			else {
				return false;
			}
		}
		else
			return false;
	}
	
	@Transactional
	public boolean clearCart(UserDao userDao) {
		UserProfile user = userProfileRepository.findByEmail(userDao.getEmail());
		if(user==null) {
			return false;
		}
		Optional<Cart> existingCart = cartRepository.findByUserProfileId(user.getId());
		if(existingCart.isPresent()) {
			existingCart.get().getCartItem().clear();
		}
		if(existingCart.get().getCartItem().isEmpty()) {
			cartRepository.save(existingCart.get());
			return true;
		}
		else
			return false;
	}
}

