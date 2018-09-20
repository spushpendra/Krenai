package com.bugfree.model.cart;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.bugfree.model.product.Product;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.supplier.product.SellerProductListings;

@Entity
@Table(name="cart_products")
public class CartProducts {

	@Id
	@GeneratedValue
	private int cartProductsId;
	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
//	@ManyToOne 
//	@JoinColumn(name="product_id")
//	private Product product;
//	@ManyToOne 
//	@JoinColumn(name="supplier_id")
//	private Supplier supplier;
	@ManyToOne 
	@JoinColumn(name="seller_product_listing_id")
	private SellerProductListings sellerProductListing;
	
	private int quantity;
	//private float pricePerUnit;
	//private float mrp;
	//private float saving;
	//private String deliveryDate;
	//private String deliveryTime;
	/*
	 * active -> delivered -> removed 
	 */
	@ManyToOne 
	@JoinColumn(name="status_id")
	private Status status;
	@Type(type="date")
	private Date createdDate;
	@Type(type="date")
	private Date modifiedDate;
	//adding user email id just to give some security issue, on deleting
//	any cart item user can be verififed that the cart element belongs to him or not
	private String userEmail;
	private String orderId;
	
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@PrePersist
	private void onCreate(){
		Date date =new Date();
		this.createdDate=date;
	}
	@PreUpdate
	private void onUpdate(){
		Date date =new Date ();
		this.modifiedDate=date;
	}
	public int getCartProductsId() {
		return cartProductsId;
	}
	public void setCartProductsId(int cartProductsId) {
		this.cartProductsId = cartProductsId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public SellerProductListings getSellerProductListing() {
		return sellerProductListing;
	}
	public void setSellerProductListing(SellerProductListings sellerProductListing) {
		this.sellerProductListing = sellerProductListing;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
//	public float getSellingPrice() {
//		return sellingPrice;
//	}
//	public void setSellingPrice(float sellingPrice) {
//		this.sellingPrice = sellingPrice;
//	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getUserEmail() {
		return userEmail;
	}
	
	
}
