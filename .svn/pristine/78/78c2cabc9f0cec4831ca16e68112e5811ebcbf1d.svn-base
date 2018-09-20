package com.bugfree.model.supplier;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="supplier_login_monitor")
public class SupplierLoginMonitor {

	@Id
	@GeneratedValue
	private int supplierLoginMonitorId;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@Type(type="date")
	private Timestamp loginTimestamp;

	public int getSupplierLoginMonitorId() {
		return supplierLoginMonitorId;
	}

	public void setSupplierLoginMonitorId(int supplierLoginMonitorId) {
		this.supplierLoginMonitorId = supplierLoginMonitorId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Timestamp getLoginTimestamp() {
		return loginTimestamp;
	}

	public void setLoginTimestamp(Timestamp loginTimestamp) {
		this.loginTimestamp = loginTimestamp;
	}
	
	
}
