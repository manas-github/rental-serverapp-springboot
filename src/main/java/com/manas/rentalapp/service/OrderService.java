package com.manas.rentalapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manas.rentalapp.Dao.OrderDao;
import com.manas.rentalapp.Dao.UserDao;
import com.manas.rentalapp.dto.CartDto;
import com.manas.rentalapp.model.Cart;
import com.manas.rentalapp.model.DiscountCoupon;
import com.manas.rentalapp.model.Order;
import com.manas.rentalapp.model.OrderItem;
import com.manas.rentalapp.model.OrderStatus;
import com.manas.rentalapp.model.UserProfile;
import com.manas.rentalapp.repository.CartRepository;
import com.manas.rentalapp.repository.DiscountCouponRepository;
import com.manas.rentalapp.repository.OrderRepository;
import com.manas.rentalapp.repository.ProductRepository;
import com.manas.rentalapp.repository.UserProfileRepository;

@Service
public class OrderService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	private DiscountCouponRepository discountCouponRepository;
	
	@Autowired
	private CartService cartService;
			
	@Transactional
	public String createOrder(UserDao userDao, OrderDao orderDao) {
		UserProfile user = userProfileRepository.findByEmail(userDao.getEmail());
		
		Optional<Cart> cartOptional = cartRepository.findByUserProfileId(user.getId());
		if(cartOptional.isPresent()) {
			Cart cart = cartOptional.get();
			if(cart.getCartItem().size()>0) {
				Order order = new Order();
				order.setUser(user);
				order.getOrderStatus().put(OrderStatus.PLACED, LocalDateTime.now());
				List<OrderItem> orderItems = new ArrayList<OrderItem>();
				cart.getCartItem().forEach(cartItem->{
					OrderItem orderItem = new OrderItem();
					orderItem.setProduct(cartItem.getProduct());
					orderItem.setQuantity(cartItem.getQuantity());
					orderItem.setPrice(cartItem.getProduct().getPrice().get(Integer.toString(cartItem.getDuration())));
					orderItem.setDuration(cartItem.getDuration());
					orderItems.add(orderItem);
				});
				order.setOrderItems(orderItems);
				order.setOrderAmount(Double.parseDouble(orderDao.getTotalAmount()));
				order.setDiscount(Double.parseDouble(orderDao.getDiscount()));
				order.setAmountPaid(Double.parseDouble(orderDao.getAmountPaid()));
				
				long orderId = orderRepository.save(order).getId();
				cartService.clearCart(userDao);
				return String.valueOf(orderId);
			}
		}
		return null;

		
	}
	
	@Transactional
	public List<Order> getAllOrder (UserDao userDao){
		UserProfile user = userProfileRepository.findByEmail(userDao.getEmail());
		if(user==null) {
			return null;
		}
		List<Order> orders = orderRepository.findAllByUserId(user.getId());
		if(orders!=null) {
			return orders;
		}
		return null;

	}

}

