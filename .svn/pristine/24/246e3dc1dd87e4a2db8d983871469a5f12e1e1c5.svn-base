package com.bugfree.model.supplierRating.store;

import java.sql.Timestamp;
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

import com.bugfree.model.user.User;

@Entity
@Table(name="store_comment_like", uniqueConstraints=@UniqueConstraint(columnNames={"store_comment_id","user_id"}))
public class StoreCommentLike {

	 @Id
	 @GeneratedValue
	 private long storeCommentLikeId;

	 @ManyToOne
	 @JoinColumn(name="user_id")
	 private User user;

	 @Type(type="timestamp")
	 private Timestamp likeTime;

	 @ManyToOne
	 @JoinColumn(name="store_comment_id")
	 private StoreComment storeComment;
	 
	 @PrePersist
	 private void onCreate(){
	  this.likeTime = new Timestamp(new Date().getTime());
	 }
	 
	 @PreUpdate
	 private void onUpdate(){
	  this.likeTime = new Timestamp(new Date().getTime());
	 }
	public long getStoreCommentLikeId() {
		return storeCommentLikeId;
	}
	public void setStoreCommentLikeId(long storeCommentLikeId) {
		this.storeCommentLikeId = storeCommentLikeId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getLikeTime() {
		return likeTime;
	}
	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}

	public StoreComment getStoreComment() {
		return storeComment;
	}

	public void setStoreComment(StoreComment storeComment) {
		this.storeComment = storeComment;
	}
	


	 
}
