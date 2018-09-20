package com.bugfree.model.category;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.bugfree.common.ImageInterface;
import com.bugfree.model.admin.Admin;
import com.bugfree.model.location.Location;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;

@Entity
@Table(name="category")
public class Category extends ImageInterface{
	@Id
	@GeneratedValue
	private int categoryId;
	@Column(unique=true)
	private String categoryName;
	private int requestCount;
	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="current_status_id")
	private Status currentStatus;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@Type(type="date")
	private Date dateAdded;
	
	@Column(length=255)
	private String reason;
	

	private String webIcon;
	private String appIcon;
	
	private boolean service = false;
	
	
	public String getWebIcon() {
		return webIcon;
	}



	public void setWebIcon(String webIcon) {
		this.webIcon = webIcon;
	}



	public String getAppIcon() {
		return appIcon;
	}



	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
	}

	

	public int getRequestCount() {
		return requestCount;
	}



	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}



	public Status getCurrentStatus() {
		return currentStatus;
	}

	@Type(type="date")
	private Date dateUpdated;
	
	@Type(type="date")
	private Date approvedDate;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	private String imageUrl;
	
	private boolean availability;
	
	@OneToOne
	@JoinColumn(name="description_id")
	private CategoryDescription categoryDescription;
	
	@ManyToOne
	@JoinColumn(name="approved_by_admin_id")
	private Admin approvedBy;
	
	@PrePersist
	protected void onCreate() {
	    dateAdded = new Date();
	    
	  }

	@PreUpdate
	protected void onModify() {
		dateUpdated = new Date();	   
	  }
	
	public int getCategoryId() {
		return categoryId;
	}

	
	
	public Status getFinalStatus() {
		return currentStatus;
	}



	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}



	public CategoryDescription getCategoryDescription() {
		return categoryDescription;
	}

	

	public void setCategoryDescription(CategoryDescription categoryDescription) {
		this.categoryDescription = categoryDescription;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Admin getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Admin approvedBy) {
		this.approvedBy = approvedBy;
	}



	public boolean isService() {
		return service;
	}



	public void setService(boolean service) {
		this.service = service;
	}
	
	
}
