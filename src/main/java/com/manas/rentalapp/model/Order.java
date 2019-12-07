package com.manas.rentalapp.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private UserProfile user;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	private double orderAmount;
	
	private double discount;
	
	private double amountPaid;
	
	private double amountDue;
	
	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	private String paymentMode;
	
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public void setOrderStatus(Map<OrderStatus, LocalDateTime> orderStatus) {
		this.orderStatus = orderStatus;
	}

	@ElementCollection
    @CollectionTable(name="orderStatus")
	private Map<OrderStatus,LocalDateTime> orderStatus = new HashMap<>();;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Map<OrderStatus, LocalDateTime> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(HashMap<OrderStatus, LocalDateTime> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public DiscountCoupon getDiscountCoupon() {
		return discountCoupon;
	}

	public void setDiscountCoupon(DiscountCoupon discountCoupon) {
		this.discountCoupon = discountCoupon;
	}

	@OneToOne
	private DiscountCoupon discountCoupon;
	
	
	
	

	
}
