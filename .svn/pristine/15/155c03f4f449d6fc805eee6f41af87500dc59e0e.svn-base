package com.bugfree.model.supplierRating.store;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;

@Entity
public class StoreComment {

	 @Id
	 @GeneratedValue
	 private int storeCommentId;
	 
	 private int storeId;
	 

	 @ManyToOne
	 @JoinColumn(name="supplier_id")
	 private Supplier supplier;
	 
	 @ManyToOne
	 @JoinColumn(name="user_id")
	 private User user;
	 

	 @Column(length=1500)
	 private String comment;
	 
	 @Type(type="timestamp")
	 private Timestamp createdTimestamp;
	 
	 @Type(type="timestamp")
	 private Timestamp modifiedTimestamp;

	public int getStoreCommentId() {
		return storeCommentId;
	}

	public void setStoreCommentId(int storeCommentId) {
		this.storeCommentId = storeCommentId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Timestamp getModifiedTimestamp() {
		return modifiedTimestamp;
	}

	public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}
	 
	@PrePersist
	 protected void onCreate() {
	  createdTimestamp = new Timestamp(new Date().getTime());
	 }
	 
	 @PreUpdate
	 protected void onUpdate(){
	  modifiedTimestamp = new Timestamp(new Date().getTime());
	 }



}
