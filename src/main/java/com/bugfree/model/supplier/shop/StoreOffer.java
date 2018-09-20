package com.bugfree.model.supplier.shop;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.bugfree.model.product.Product;
import com.bugfree.model.supplier.Supplier;

@Entity
@Table(name="store_offer")
public class StoreOffer {

	@Id
	@GeneratedValue
	private int storeOfferId;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	private String offer;
	
	private String offerDetails;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public int getStoreOfferId() {
		return storeOfferId;
	}
	
	private String status;
	
	@Type(type="date")
	private Date offerStartDate;
	
	@Type(type="date")
	private Date offerEndDate;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOfferStartDate() {
		return offerStartDate;
	}

	public void setOfferStartDate(Date offerStartDate) {
		this.offerStartDate = offerStartDate;
	}

	public Date getOfferEndDate() {
		return offerEndDate;
	}

	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}

	public void setStoreOfferId(int storeOfferId) {
		this.storeOfferId = storeOfferId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
