package com.bugfree.repository.cart;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bugfree.model.cart.Cart;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;

public interface CartRepository extends PagingAndSortingRepository<Cart, Integer> {

	List<Cart> findByUserAndStatus(User user, Status status);

	List<Cart> findByCartIdIn(List<Integer> idList);

	Cart findByOrderId(String orderId);

	List<Cart> findByUser(User user);

	List<Cart> findBySupplier(Supplier supplier);

	List<Cart> findBySupplierAndStatus(Supplier supplier, Status status);

	Cart findByOrderIdAndSupplier(String orderId, Supplier supplier);

	List<Cart> findByUserAndSupplier(User user, Supplier supplier);

	List<Cart> findBySupplierAndStatusStatusValueIn(Supplier supplier, String[] strArray);

	List<Cart> findBySupplierAndStatusStatusValue(Supplier supplier, String orderDelivered);

	@Query(value="select  month(C.delivered_time), year(C.delivered_time), count(distinct C.order_id), sum(OPD.selling_price * OPD.quantity) from cart C  left join cart_products CP on CP.cart_id=C.cart_id left join ordered_product_detail OPD on OPD.cart_product_id=CP.cart_products_id where C.supplier_id=? and C.status_id=? group by  month(C.delivered_time) ,  year(C.delivered_time) ;", nativeQuery=true)
	List<Object> findOrderNosAndOrderAmountMonthlyWise(Supplier supplier, Status status);

	@Query(value="select C.cart_id, C.order_id, C.order_date, C.user_required_date, C.user_required_time, St.status_value, U.first_name, U.last_name, UAB.custom_address, UAB.google_address,  U.profile_image_url, U.contact_no , sum(OPD.selling_price * OPD.quantity) , A.latitude, A.longitude, C.payment_status, C.payment_mode  from cart C left join status St on St.status_id=C.status_id left join user U on U.user_id=C.user_id left join user_address_book UAB on UAB.user_address_book_id=C.user_address_book_id left join cart_products CP on CP.cart_id=C.cart_id left join ordered_product_detail OPD on OPD.cart_product_id=CP.cart_products_id left join supplier S on S.supplier_id = C.supplier_id  left join address A on A.address_id = S.address_id  where C.supplier_id=?1 and St.status_value in (?2) group by CP.cart_id, C.cart_id ;", nativeQuery=true)
	List<Object> findBySupplierAndStatusStatusValueIn(Supplier supplier, List<String> strArray);

	
}
