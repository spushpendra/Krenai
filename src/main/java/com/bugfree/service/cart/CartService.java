package com.bugfree.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.cart.Cart;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;
import com.bugfree.repository.cart.CartRepository;

@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	public List<Cart> findByUserAndStatus(User user, Status status) {
		return cartRepository.findByUserAndStatus(user, status);
	}
	public Cart save(Cart cart) {
		return cartRepository.save(cart);
	}
	
	public Cart findByOrderId(String orderId) {
		return cartRepository.findByOrderId(orderId);
	}
	public List<Cart> findByUser(User user) {
		return cartRepository.findByUser(user);
	}
	public List<Cart> findByCartIdIn(List<Integer> tempList) {
		return cartRepository.findByCartIdIn(tempList);
	}
	public List<Cart> findBySupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return cartRepository. findBySupplier( supplier);
	}
	public List<Cart> findBySupplierAndStatus(Supplier supplier, Status status) {
		// TODO Auto-generated method stub
		return cartRepository.findBySupplierAndStatus( supplier, status);
	}
	public Cart findByOrderIdAndSupplier(String orderId, Supplier supplier) {
		// TODO Auto-generated method stub
		return cartRepository.findByOrderIdAndSupplier( orderId,  supplier);
	}
	public List<Cart> findByUserAndSupplier(User user, Supplier supplier) {
		// TODO Auto-generated method stub
		return cartRepository.findByUserAndSupplier( user,  supplier);
	}
	public List<Cart> findBySupplierAndStatusIn(Supplier supplier, String[] strArray) {
		// TODO Auto-generated method stub
		return cartRepository.findBySupplierAndStatusStatusValueIn(supplier,  strArray);
	}
	
	public List<Object> findBySupplierAndStatusIn(Supplier supplier, List<String> strArray) {
		// TODO Auto-generated method stub
		return cartRepository.findBySupplierAndStatusStatusValueIn(supplier,  strArray);
	}
	
	
	
}
