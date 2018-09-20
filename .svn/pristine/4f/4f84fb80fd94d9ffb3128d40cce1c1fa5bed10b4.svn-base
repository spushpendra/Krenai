package com.bugfree.model.supplier;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="supplier_activity_monitor")
public class SupplierActivityMonitor {

	@Id
	@GeneratedValue
	private int supplierActivityMonitorId;
	
	@ManyToOne 
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@Type(type="date")
	private Date date;
	
	private String activityOpened;
	
	private String ipAddress;
	
	private String activityChangedOrRequested;
	private String oldValue;
	private String newValue;

	
	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getSupplierActivityMonitorId() {
		return supplierActivityMonitorId;
	}

	public void setSupplierActivityMonitorId(int supplierActivityMonitorId) {
		this.supplierActivityMonitorId = supplierActivityMonitorId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getActivityOpened() {
		return activityOpened;
	}

	public void setActivityOpened(String activityOpened) {
		this.activityOpened = activityOpened;
	}

	public String getActivityChangedOrRequested() {
		return activityChangedOrRequested;
	}

	public void setActivityChangedOrRequested(String activityChangedOrRequested) {
		this.activityChangedOrRequested = activityChangedOrRequested;
	}
	
	
}
