package com.bugfree.model.social.feed;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;

@Entity
@Table(name="feeds")
public class Feeds {

	@Id
	@GeneratedValue
	private int feedsId;
	
	@ManyToOne
	@JoinColumn(name="published_by_user_id")
	private User publishedByUser;
	private boolean isFeedPublishedBySupplier;
	
	@ManyToOne
	@JoinColumn(name="published_by_supplier_id")
	private Supplier publishedBySupplier;
	
	private Timestamp feedPublishedTimestamp;
	private Timestamp feedUpdatedTimestamp;
	private String allowedFor;
	private String imageCaptionUrl;
	private String imageRedirectUrl;
	@Column(length=2000)
	private String feedMessage;
	@ManyToOne
	@JoinColumn(name="shared_ref_of_feed")
	private Feeds sharedRefOfFeed;
	
	private int shareCount;
	private String feedType;
	private String sharedFeedMessage;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="feeds")
	private List<FeedLikes> feedLikesList = new ArrayList<FeedLikes>();
	
	private int likeCount;
	
	@ManyToOne
	@JoinColumn(name="shared_by_user_id")
	private User sharedByUser;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( mappedBy="feeds" ,cascade = CascadeType.ALL)
	private List<FeedComments> feedsCommentList = new ArrayList<FeedComments>();
	
	
	@PrePersist
	private void onCreate(){
		this.feedPublishedTimestamp = new Timestamp(new Date().getTime());
		this.feedUpdatedTimestamp = new Timestamp(new Date().getTime());
		
	}
	@PreUpdate
	private void onUpdate(){
		this.feedUpdatedTimestamp = new Timestamp(new Date().getTime());
	}
	public int getFeedsId() {
		return feedsId;
	}
	public void setFeedsId(int feedsId) {
		this.feedsId = feedsId;
	}
	
	public Timestamp getFeedPublishedTimestamp() {
		return feedPublishedTimestamp;
	}
	public void setFeedPublishedTimestamp(Timestamp feedPublishedTimestamp) {
		this.feedPublishedTimestamp = feedPublishedTimestamp;
	}
	public Timestamp getFeedUpdatedTimestamp() {
		return feedUpdatedTimestamp;
	}
	public void setFeedUpdatedTimestamp(Timestamp feedUpdatedTimestamp) {
		this.feedUpdatedTimestamp = feedUpdatedTimestamp;
	}
	public String getAllowedFor() {
		return allowedFor;
	}
	public void setAllowedFor(String allowedFor) {
		this.allowedFor = allowedFor;
	}
	public String getImageCaptionUrl() {
		return imageCaptionUrl;
	}
	public void setImageCaptionUrl(String imageCaptionUrl) {
		this.imageCaptionUrl = imageCaptionUrl;
	}
	public String getFeedMessage() {
		return feedMessage;
	}
	public void setFeedMessage(String feedMessage) {
		this.feedMessage = feedMessage;
	}
	
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public User getPublishedByUser() {
		return publishedByUser;
	}
	public void setPublishedByUser(User publishedByUser) {
		this.publishedByUser = publishedByUser;
	}
	
	public boolean isFeedPublishedBySupplier() {
		return isFeedPublishedBySupplier;
	}
	public void setPublishedBySupplier(boolean isPublishedBySupplier) {
		this.isFeedPublishedBySupplier = isPublishedBySupplier;
	}
	public Supplier getPublishedBySupplier() {
		return publishedBySupplier;
	}
	public void setPublishedBySupplier(Supplier publishedBySupplier) {
		this.publishedBySupplier = publishedBySupplier;
	}
	public Feeds getSharedRefOfFeed() {
		return sharedRefOfFeed;
	}
	public void setSharedRefOfFeed(Feeds sharedRefOfFeed) {
		this.sharedRefOfFeed = sharedRefOfFeed;
	}
	public User getSharedByUser() {
		return sharedByUser;
	}
	public void setSharedByUser(User sharedByUser) {
		this.sharedByUser = sharedByUser;
	}
	public List<FeedLikes> getFeedLikesList() {
		return feedLikesList;
	}
	public void setFeedLikesList(List<FeedLikes> feedLikesList) {
		this.feedLikesList = feedLikesList;
	}
	public List<FeedComments> getFeedsCommentList() {
		return this.feedsCommentList;
	}
	public void setFeedsCommentList(List<FeedComments> feedsCommentList) {
		this.feedsCommentList = feedsCommentList;
	}
	public String getFeedType() {
		return feedType;
	}
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}
	public String getSharedFeedMessage() {
		return sharedFeedMessage;
	}
	public void setSharedFeedMessage(String sharedFeedMessage) {
		this.sharedFeedMessage = sharedFeedMessage;
	}
	public String getImageRedirectUrl() {
		return imageRedirectUrl;
	}
	public void setImageRedirectUrl(String imageRedirectUrl) {
		this.imageRedirectUrl = imageRedirectUrl;
	}
	
	
	
}
