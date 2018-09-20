package com.bugfree.model.offer.promocode;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.bugfree.model.user.User;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"promo_code_bucket_id","user_id"}))
public class PromoGrabed {

	@Id
	@GeneratedValue
	private long promoGrabId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="promo_code_bucket_id")
	private PromoCodeBucket promoCodeBucket;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Temporal(TemporalType.DATE)
	private Date grabedDate;

	public long getPromoGrabId() {
		return promoGrabId;
	}

	public void setPromoGrabId(long promoGrabId) {
		this.promoGrabId = promoGrabId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getGrabedDate() {
		return grabedDate;
	}

	public void setGrabedDate(Date grabedDate) {
		this.grabedDate = grabedDate;
	}

	public PromoCodeBucket getPromoCodeBucket() {
		return promoCodeBucket;
	}

	public void setPromoCodeBucket(PromoCodeBucket promoCodeBucket) {
		this.promoCodeBucket = promoCodeBucket;
	}
	
	
}
