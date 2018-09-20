package com.bugfree.model.supplier.android;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;

@Entity
public class SupplierLoginSession {

	@Id
	private String sessionId;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@Type(type="timestamp")
	private Date loginTimestamp;
	
	@Type(type="timestamp")
	private Date logoutTimestamp;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getLoginTimestamp() {
		return loginTimestamp;
	}

	public void setLoginTimestamp(Date loginTimestamp) {
		this.loginTimestamp = loginTimestamp;
	}

	public Date getLogoutTimestamp() {
		return logoutTimestamp;
	}

	public void setLogoutTimestamp(Date logoutTimestamp) {
		this.logoutTimestamp = logoutTimestamp;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@PrePersist
	private void onCreate(){
		this.loginTimestamp = new Date();
	}
	
}
