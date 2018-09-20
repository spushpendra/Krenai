package com.bugfree.model.cart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderedProductDetail {

	@Id
	@GeneratedValue
	private long orderedProductDetailId;
	
	@OneToOne
	@JoinColumn(name="cart_product_id")
	private CartProducts cartProduct;
	
	private int quantity;
	private float mrp;
	private float sellingPrice;
	private float amount;
	private float saving;
	public long getOrderedProductDetailId() {
		return orderedProductDetailId;
	}
	public void setOrderedProductDetailId(long orderedProductDetailId) {
		this.orderedProductDetailId = orderedProductDetailId;
	}
	public CartProducts getCartProduct() {
		return cartProduct;
	}
	public void setCartProduct(CartProducts cartProduct) {
		this.cartProduct = cartProduct;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getMrp() {
		return mrp;
	}
	public void setMrp(float mrp) {
		this.mrp = mrp;
	}
	public float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getSaving() {
		return saving;
	}
	public void setSaving(float saving) {
		this.saving = saving;
	}
	
	
}
