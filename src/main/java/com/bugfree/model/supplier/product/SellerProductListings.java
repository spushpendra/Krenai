package com.bugfree.model.supplier.product;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.bugfree.model.cost.Tax;
import com.bugfree.model.product.Product;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;

@Entity
@Table(name="seller_product_listings", uniqueConstraints=@UniqueConstraint(columnNames={"product_id","supplier_id"}))
public class SellerProductListings {

	@Id
	@GeneratedValue
	private int sellerProductListingId;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	private String tradeType;
	
	private float mrp;
	
	private float sellingPrice;
	
	@ManyToOne 
	@JoinColumn(name="tax_id")
	private Tax tax;
	
	private int availableQuantity;
	private int minimumSellQuantity;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
	
	@Type(type="date")
	private Date availabilityDate;

	public int getSellerProductListingId() {
		return sellerProductListingId;
	}

	public void setSellerProductListingId(int sellerProductListingId) {
		this.sellerProductListingId = sellerProductListingId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public int getMinimumSellQuantity() {
		return minimumSellQuantity;
	}

	public void setMinimumSellQuantity(int minimumSellQuantity) {
		this.minimumSellQuantity = minimumSellQuantity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getAvailabilityDate() {
		return availabilityDate;
	}

	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}
	
	
}
