package com.bugfree.repository.cart;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.cart.Cart;
import com.bugfree.model.cart.CartProducts;
import com.bugfree.model.product.Product;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;

public interface CartProductsRepository extends
		PagingAndSortingRepository<CartProducts, Integer> {

//	CartProducts findByCartAndSellerProductListingProductAndSupplierAndStatus(Cart cart,
//			Product product, Supplier supplier, Status status);

	List<CartProducts> findByCartAndStatus(Cart cart, Status status);

//	List<CartProducts> findByCartAndStatusAndSupplier(Cart cart, Status status,
//			Supplier supplier);

	CartProducts findByCartProductsIdAndUserEmail(int cartProductId, String emailId);

//	List<CartProducts> findBySupplierAndUserEmailAndStatus(Supplier supplier, String emailId, Status status);

//	List<CartProducts> findBySupplier(Supplier supplier);

	List<CartProducts> findByCartIn(List<Cart> cartList);

	List<CartProducts> findByCart(Cart cart);

	List<CartProducts> findByCartSupplier(Supplier supplier);

//	List<CartProducts> findByCartInAndSupplier(List<Cart> cartList, Supplier supplier);

//	List<CartProducts> findByOrderIdAndSupplier(String orderId, Supplier supplier);

//	List<CartProducts> findBySupplierAndStatus(Supplier supplier, Status status);

//	List<CartProducts> findBySupplierAndStatusIn(Supplier supplier, List<Status> statusList);

//	List<CartProducts> findByCartAndProductAndSupplierAndStatus(Cart cart,
//			Status status);

}
