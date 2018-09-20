package com.bugfree.model.supplier.query;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import com.bugfree.model.supplier.Supplier;

@Entity
public class Query {
	@Id
	@GeneratedValue
	private int queryId;
	private String raiseQuery;
	@Type(type="date")
	private Date createdDate;
	@ManyToOne
	private Supplier supplier;
	
	@PrePersist
	private void onCreate(){
		Date date =new Date();
		this.createdDate=date;
	}

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public String getRaiseQuery() {
		return raiseQuery;
	}

	public void setRaiseQuery(String raiseQuery) {
		this.raiseQuery = raiseQuery;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
}
