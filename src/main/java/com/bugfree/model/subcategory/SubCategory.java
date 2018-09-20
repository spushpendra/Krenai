package com.bugfree.model.subcategory;

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
import com.bugfree.model.category.Category;
import com.bugfree.model.location.Location;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;

@Entity
@Table(name="sub_category")
public class SubCategory extends ImageInterface{

	@Id
	@GeneratedValue
	private int subCategoryId;
	
	@Column(unique=true)
	private String subCategoryName;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@Type(type="date")
	private Date dateAdded;
	
	@Type(type="date")
	private Date dateUpdated;
	
	@Type(type="date")
	private Date approvedDate;
	
	
	@Column(length=255)
	private String reason;
	
	public String getReason() {
		return reason;
	}
	private int requestCount;
	public void setReason(String reason) {
		this.reason = reason;
	}

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="current_status_id")
	private Status currentStatus;

	
	@PrePersist
	protected void onCreate() {
	    dateAdded = new Date();
	    
	  }

	@PreUpdate
	protected void onModify() {
		dateUpdated = new Date();	   
	}
	
	
	
	
	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	@OneToOne
	@JoinColumn(name="subcategory_description_id")
	private SubCategoryDescription subCategoryDescription;
	
	@ManyToOne
	@JoinColumn
	private Supplier supplier;
	
	private boolean availability;
	
	@ManyToOne
	@JoinColumn(name="approved_by_admin_id")
	private Admin approvedBy;
	
	
	public SubCategoryDescription getSubCategoryDescription() {
		return subCategoryDescription;
	}

	public void setSubCategoryDescription(
			SubCategoryDescription subCategoryDescription) {
		this.subCategoryDescription = subCategoryDescription;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	
	
	public int getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Category getCategory() {
		return category;
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

	public void setCategory(Category category) {
		this.category = category;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Admin getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Admin approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	
}
