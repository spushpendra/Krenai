package com.bugfree.model.offer.promocode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PromoCodeBucket {

	@Id
	@GeneratedValue
	private int promoCodeId;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(unique=true)
	private String promoCode;
	
	private String status;
	
	private float promoValue;

	public int getPromoCodeId() {
		return promoCodeId;
	}

	public void setPromoCodeId(int promoCodeId) {
		this.promoCodeId = promoCodeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getPromoValue() {
		return promoValue;
	}

	public void setPromoValue(float promoValue) {
		this.promoValue = promoValue;
	}
	
	
	
}
