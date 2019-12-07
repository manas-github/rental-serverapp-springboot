package com.manas.rentalapp.Dao;

public class OrderDao {

	private long couponCodeId;
	
	private String totalAmount;
	
	private String discount;
	
	private String amountPaid;
	
	private String paymentMode;
	
	private String amountDue;
	
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}

	public void setCouponCodeId(long couponCodeId) {
		this.couponCodeId = couponCodeId;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	private long cartId;

	public long getCouponCodeId() {
		return couponCodeId;
	}

	public void setCouponCode(long couponCode) {
		this.couponCodeId = couponCode;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	
	
	
}
