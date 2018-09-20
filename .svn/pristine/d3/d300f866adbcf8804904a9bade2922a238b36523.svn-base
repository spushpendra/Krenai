package com.bugfree.model.supplierRating;

import java.util.Date;

import javax.persistence.Column;
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

import com.bugfree.model.user.User;

@Entity
@Table(name="supplier_rating_comment",
uniqueConstraints=@UniqueConstraint(columnNames={"supplier_rating_id", "user_name_id"}))
public class SupplierRatingComment {

	@Id
	@GeneratedValue
	private int supplierRatingCommentId;
	
	@ManyToOne
	@JoinColumn(name="supplier_rating_id")
	private SupplierRating supplierRating;
	
	@ManyToOne 
	@JoinColumn(name="user_name_id")
	private User user;
	
	@Column(length=255)
	private String comment;
	
	@Type(type="date")
	private Date createdDate;
	@Type(type="date")
	private Date updatedDate;
	
	@PrePersist
	private void onCreate(){
		createdDate=new Date();
	}
	@PreUpdate
	private void onUpdate(){
		updatedDate=new Date();
	}

	public int getSupplierRatingCommentId() {
		return supplierRatingCommentId;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public void setSupplierRatingCommentId(int supplierRatingCommentId) {
		this.supplierRatingCommentId = supplierRatingCommentId;
	}

	public SupplierRating getSupplierRating() {
		return supplierRating;
	}

	public void setSupplierRating(SupplierRating supplierRating) {
		this.supplierRating = supplierRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user)throws Exception {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
