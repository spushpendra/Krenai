package com.bugfree.model.social.feed;

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
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.bugfree.model.user.User;

@Entity
@Table(name="feed_comments")
public class FeedComments {
	
	@Id
	@GeneratedValue
	private int feedCommentsId;
	
	@ManyToOne
	@JoinColumn(name="feed_id")
	private Feeds feeds;
	@Column(length=1200,nullable=false)
	private String commentMessage;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User commentedByUser;
	@Type(type="timestamp")
	private Timestamp commentedAtTimestamp;
	@Type(type="timestamp")
	private Timestamp commentedUpdatedAtTimestamp;
	
	@PrePersist
	private void onCreate(){
		this.commentedAtTimestamp = new Timestamp(new Date().getTime());
		
	}
	@PreUpdate
	private void onUpdate(){
		this.commentedUpdatedAtTimestamp = new Timestamp(new Date().getTime());
	}
	public int getFeedCommentsId() {
		return feedCommentsId;
	}
	public void setFeedCommentsId(int feedCommentsId) {
		this.feedCommentsId = feedCommentsId;
	}
	
	
	public Feeds getFeeds() {
		return feeds;
	}
	public void setFeeds(Feeds feeds) {
		this.feeds = feeds;
	}
	public String getCommentMessage() {
		return commentMessage;
	}
	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}
	
	public User getCommentedByUser() {
		return commentedByUser;
	}
	public void setCommentedByUser(User commentedByUser) {
		this.commentedByUser = commentedByUser;
	}
	public Timestamp getCommentedAtTimestamp() {
		return commentedAtTimestamp;
	}
	public void setCommentedAtTimestamp(Timestamp commentedAtTimestamp) {
		this.commentedAtTimestamp = commentedAtTimestamp;
	}
	public Timestamp getCommentedUpdatedAtTimestamp() {
		return commentedUpdatedAtTimestamp;
	}
	public void setCommentedUpdatedAtTimestamp(Timestamp commentedUpdatedAtTimestamp) {
		this.commentedUpdatedAtTimestamp = commentedUpdatedAtTimestamp;
	}
	
}
