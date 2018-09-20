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

import org.hibernate.annotations.Type;

import com.bugfree.model.user.User;

@Entity
public class StoreSubComment {

	@Id
	 @GeneratedValue
	 private int storeSubCommentId;
	 
	 @Column(length=1500)
	 private String subComment;
	 
	 @ManyToOne
	 @JoinColumn(name="store_comment_id")
	 private StoreComment storeComment;
	 
	 @ManyToOne
	 @JoinColumn(name="user_id")
	 private User user;
	 
	 @Type(type="timestamp")
	 private Timestamp replyTimestamp;
	 
	 @PrePersist
	 protected void onCreate() {
	  this.replyTimestamp = new Timestamp(new Date().getTime());
	  System.out.println("************replytimestamp***********"+replyTimestamp);
	 }

	public int getStoreSubCommentId() {
		return storeSubCommentId;
	}

	public void setStoreSubCommentId(int storeSubCommentId) {
		this.storeSubCommentId = storeSubCommentId;
	}

	public String getSubComment() {
		return subComment;
	}

	public void setSubComment(String subComment) {
		this.subComment = subComment;
	}

	public StoreComment getStoreComment() {
		return storeComment;
	}

	public void setStoreComment(StoreComment storeComment) {
		this.storeComment = storeComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getReplyTimestamp() {
		return replyTimestamp;
	}

	public void setReplyTimestamp(Timestamp replyTimestamp) {
		this.replyTimestamp = replyTimestamp;
	}
	 
	 
}
