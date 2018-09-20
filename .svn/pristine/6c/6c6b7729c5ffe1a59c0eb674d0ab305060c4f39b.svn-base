package com.bugfree.model.cost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bugfree.model.discount.Discount;

@Entity
@Table(name="cost")
public class Cost {

	@Id
	@GeneratedValue
	private int costId;
	
	private long costValue;
	
	private long mrp;
	
//	@ManyToOne
//	@JoinTable(name="tax_id")
//	private Tax tax;
	
	private int taxPercentageAdded;
	
	@ManyToOne
	@JoinTable(name="discount_id")
	private Discount discount;

	public int getCostId() {
		return costId;
	}

	public void setCostId(int costId) {
		this.costId = costId;
	}

	public long getCostValue() {
		return costValue;
	}

	public void setCostValue(long costValue) {
		this.costValue = costValue;
	}

	public long getMrp() {
		return mrp;
	}

	public void setMrp(long mrp) {
		this.mrp = mrp;
	}

	public int getTaxPercentageAdded() {
		return taxPercentageAdded;
	}

	public void setTaxPercentageAdded(int taxPercentageAdded) {
		this.taxPercentageAdded = taxPercentageAdded;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	
	
	
}
