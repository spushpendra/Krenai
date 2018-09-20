package com.bugfree.model.supplier.documents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;

@Entity
@Table(name="supplier_pancard_details")
public class SupplierPancardDetails {

	@Id
	@GeneratedValue
	private int pancardDetailsId;
	
	@OneToOne
	@JoinColumn(name="supplier")
	private Supplier supplier;
	
	@Column(unique=true)
	private String pancardNo;
	
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	
	private String expiryDate;
	private String documentPath;
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getPancardDetailsId() {
		return pancardDetailsId;
	}
	public void setPancardDetailsId(int pancardDetailsId) {
		this.pancardDetailsId = pancardDetailsId;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getPancardNo() {
		return pancardNo;
	}
	public void setPancardNo(String pancardNo) {
		this.pancardNo = pancardNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	
	
}
