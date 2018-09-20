package com.bugfree.model.discount;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="discount")
public class Discount {

	@Id
	@GeneratedValue
	private int discountId;
	
	private int discountPercentage;
	
	@Type(type="date")
	private Date discountFron;
	
	@Type(type="date")
	private Date discountUpto;
	
	private int maxValidQuantity;
	
	public int getMaxValidQuantity() {
		return maxValidQuantity;
	}

	public void setMaxValidQuantity(int maxValidQuantity) {
		this.maxValidQuantity = maxValidQuantity;
	}

	public int getDiscountId() {
		return discountId;
	}
	
	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Date getDiscountFron() {
		return discountFron;
	}

	public void setDiscountFron(Date discountFron) {
		this.discountFron = discountFron;
	}

	public Date getDiscountUpto() {
		return discountUpto;
	}

	public void setDiscountUpto(Date discountUpto) {
		this.discountUpto = discountUpto;
	}
	
	
	
}
