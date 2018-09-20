package com.bugfree.model.social.network;

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
@Table(name="user_follow", uniqueConstraints=@UniqueConstraint(columnNames={"user_id","supplier_id"}))
public class UserFollow {

	@Id
	@GeneratedValue
	private int userFollowId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@Type(type="date")
	private Date followedDate;
	@Type(type="date")
	private Date requestedDate;
	@Type(type="date")
	private Date updatedDate;
	private String statusValue;
	@PrePersist
	private void onCreate(){
		Date date =new Date();
		this.followedDate=date;
	}
	@PreUpdate
	private void onUpdate(){
		Date date =new Date ();
		this.updatedDate=date;
	}
	public int getUserFollowId() {
		return userFollowId;
	}
	public void setUserFollowId(int userFollowId) {
		this.userFollowId = userFollowId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Date getFollowedDate() {
		return followedDate;
	}
	public void setFollowedDate(Date followedDate) {
		this.followedDate = followedDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	public Date getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}
	
	
}
