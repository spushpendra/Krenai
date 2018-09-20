package com.bugfree.model.cost;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="tax")
public class Tax {

	@Id
	@GeneratedValue
	private int taxId;
	
	private String taxName;
	
	private int taxValue;
	
	@Type(type="date")
	private Date dateAdded;
	
	@Type(type="date")
	private Date modifiedDate;

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public int getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(int taxValue) {
		this.taxValue = taxValue;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
