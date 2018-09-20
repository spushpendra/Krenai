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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;


@Entity
@Table(name="feed_likes",  uniqueConstraints=@UniqueConstraint(columnNames={"feed_id", "user_id"}))
public class FeedLikes {

	@Id
	@GeneratedValue
	@Column(name="feed_likes_id")
	private int feedLikesId;
	
	@ManyToOne
	@JoinColumn(name="feed_id")
	private Feeds feeds;
	
	@Column(name="user_id")
	private int userId;
	@Type(type="timestamp")
	private Timestamp likedAtTimestamp;
	@Type(type="timestamp")
	private Timestamp likeUpdatedAtTimestamp;
	private boolean likeDecision;
	
	@PrePersist
	private void onCreate(){
		this.likedAtTimestamp = new Timestamp(new Date().getTime());
		this.likeDecision = true;
	}
	@PreUpdate
	private void onUpdate(){
		this.likeUpdatedAtTimestamp = new Timestamp(new Date().getTime());
	}
	public int getFeedLikesId() {
		return feedLikesId;
	}
	
	public Feeds getFeeds() {
		return feeds;
	}
	public void setFeeds(Feeds feeds) {
		this.feeds = feeds;
	}

	public void setFeedLikesId(int feedLikesId) {
		this.feedLikesId = feedLikesId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getLikedAtTimestamp() {
		return likedAtTimestamp;
	}
	public void setLikedAtTimestamp(Timestamp likedAtTimestamp) {
		this.likedAtTimestamp = likedAtTimestamp;
	}
	public Timestamp getLikeUpdatedAtTimestamp() {
		return likeUpdatedAtTimestamp;
	}
	public void setLikeUpdatedAtTimestamp(Timestamp likeUpdatedAtTimestamp) {
		this.likeUpdatedAtTimestamp = likeUpdatedAtTimestamp;
	}
	public boolean isLikeDecision() {
		return likeDecision;
	}
	public void setLikeDecision(boolean likeDecision) {
		this.likeDecision = likeDecision;
	}
	
	
	
}
