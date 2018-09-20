package com.bugfree.repository.cart;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.cart.Cart;
import com.bugfree.model.cart.CartProducts;
import com.bugfree.model.cart.OrderedProductDetail;

public interface OrderedProductDetailRepository extends PagingAndSortingRepository<OrderedProductDetail, Long> {

	List<OrderedProductDetail> findByCartProductIn(List<CartProducts> cartProductList);

	@Query(value="select * from ordered_product_detail OPD where OPD.cart_product_id in (select CP.cart_products_id from cart_products CP where CP.cart_id in (?1))", nativeQuery=true)
	List<OrderedProductDetail> findByCartIn(List<Cart> cartTempList);

	@Query(value="select sum(OPD.selling_price * OPD.quantity) from ordered_product_detail OPD where OPD.cart_product_id in (select CP.cart_products_id from cart_products CP where CP.cart_id in (?1 ))", nativeQuery=true)
	Object findTotalOrderAmountByCartIn(List<Cart> cartTempList);

}
