package com.bugfree.model.supplierRating;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;

@Entity
@Table(name="supplier_rating", uniqueConstraints=@UniqueConstraint(columnNames={"supplier_id", "user_id"}))
public class SupplierRating {

	@Id
	@GeneratedValue
	private int supplierRatingId;
	
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private boolean oneStarAudience;
	private boolean twoStarAudience;
	private boolean threeStarAudience;
	private boolean fourStarAudience;
	private boolean fiveStarAudience;
	
	@Type(type="date")
	private Date addedOn;
	
	
	@Type(type="date")
	private Date lastCountUpdateDate;
	
	@PrePersist
	private void onCreate(){
		addedOn = new Date();
	}
	
	@PreUpdate
	private void onUpdate(){
		lastCountUpdateDate=new Date();
	}

	public int getSupplierRatingId() {
		return supplierRatingId;
	}

	public void setSupplierRatingId(int supplierRatingId) {
		this.supplierRatingId = supplierRatingId;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isOneStarAudience() {
		return oneStarAudience;
	}

	public void setOneStarAudience(boolean oneStarAudience) {
		this.oneStarAudience = oneStarAudience;
	}

	public boolean isTwoStarAudience() {
		return twoStarAudience;
	}

	public void setTwoStarAudience(boolean twoStarAudience) {
		this.twoStarAudience = twoStarAudience;
	}

	public boolean isThreeStarAudience() {
		return threeStarAudience;
	}

	public void setThreeStarAudience(boolean threeStarAudience) {
		this.threeStarAudience = threeStarAudience;
	}

	public boolean isFourStarAudience() {
		return fourStarAudience;
	}

	public void setFourStarAudience(boolean fourStarAudience) {
		this.fourStarAudience = fourStarAudience;
	}

	public boolean isFiveStarAudience() {
		return fiveStarAudience;
	}

	public void setFiveStarAudience(boolean fiveStarAudience) {
		this.fiveStarAudience = fiveStarAudience;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public Date getLastCountUpdateDate() {
		return lastCountUpdateDate;
	}

	public void setLastCountUpdateDate(Date lastCountUpdateDate) {
		this.lastCountUpdateDate = lastCountUpdateDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) throws Exception {
		this.supplier = supplier;
	}
	
}
