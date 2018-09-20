package com.bugfree.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugfree.model.cart.Cart;
import com.bugfree.model.cart.CartProducts;
import com.bugfree.model.product.Product;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;
import com.bugfree.repository.cart.CartProductsRepository;

@Service
@Transactional
public class CartProductsService {

	@Autowired
	private CartProductsRepository cartProductsRepository;
	
	/*public CartProducts findByCartAndProductAndSupplierAndStatus(Cart cart,
			Product product, Supplier supplier, Status status) {
		return cartProductsRepository.findByCartAndSellerProductListingProductAndSupplierAndStatus( cart,
				 product,  supplier,  status);
	}*/
	public CartProducts save(CartProducts cartProduct) {
		return cartProductsRepository.save(cartProduct);
	}
	public void delete(CartProducts cartProduct) {
		 cartProductsRepository.delete(cartProduct);
	}

	public List<CartProducts> findByCartAndStatus(Cart cart, Status status) {
		return cartProductsRepository. findByCartAndStatus(cart, status);
	}
//	public List<CartProducts> findByCartAndStatusAndSupplier(Cart cart,
//			Status status, Supplier supplier) {
//		return cartProductsRepository.findByCartAndStatusAndSupplier(cart,status,supplier) ;
//	}
	public CartProducts findByCartProductsIdAndUserEmail(int cartProductId, String emailId) {
		return cartProductsRepository.findByCartProductsIdAndUserEmail(cartProductId, emailId);
	}
//	public List<CartProducts> findBySupplierAndUserEmailAndStatus(Supplier supplier, String emailId, Status status) {
//		return cartProductsRepository.findBySupplierAndUserEmailAndStatus( supplier,  emailId,  status);
//	}
	public void saveList(List<CartProducts> tempListCartProducts) {
		 cartProductsRepository.save(tempListCartProducts);
	}
//	public List<CartProducts> findBySupplier(Supplier supplier) {
//		return cartProductsRepository.findBySupplier( supplier);
//	}
	/*public List<CartProducts> findByCartInAndSupplier(List<Cart> cartList, Supplier supplier) {
		return cartProductsRepository.findByCartInAndSupplier(cartList, supplier) ;
	}
	public List<CartProducts> findByOrderIdAndSupplier(String orderId, Supplier supplier) {
		return cartProductsRepository.findByOrderIdAndSupplier(orderId, supplier);
	}
	public List<CartProducts> findBySupplierAndStatus(Supplier supplier, Status status) {
		return cartProductsRepository.findBySupplierAndStatus( supplier,  status);
	}
	public List<CartProducts> findBySupplierAndStatusIn(Supplier supplier, List<Status> statusList) {
		return cartProductsRepository.findBySupplierAndStatusIn(supplier, statusList);
	}*/
	public List<CartProducts> findByCartIn(List<Cart> cartList) {
		// TODO Auto-generated method stub
		return cartProductsRepository.findByCartIn(cartList);
	}
	public List<CartProducts> findByCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartProductsRepository.findByCart(cart);
	}
	public List<CartProducts> findByCartSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return cartProductsRepository.findByCartSupplier( supplier);
	}
	

}
