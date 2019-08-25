package com.manas.rentalapp.dto;

import java.util.List;

import com.manas.rentalapp.model.CartItem;

public class CartDto {

	private long id;
	
	private UserDto user;
	
	private List<CartItem> cartItem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
}
