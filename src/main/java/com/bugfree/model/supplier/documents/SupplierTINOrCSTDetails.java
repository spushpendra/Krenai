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
@Table(name="supplier_tin_or_cst_details")
public class SupplierTINOrCSTDetails {

	@Id
	@GeneratedValue
	private int SupplierTinOrCstDetailsId;
	
	@Column(unique=true)
	private String TinOrCstDocumentNo;
	
	@OneToOne
	@JoinColumn(name="supplier")
	private Supplier supplier;
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	private String expiryDate;
	private String documentPath;
	
	
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public int getSupplierTinOrCstDetailsId() {
		return SupplierTinOrCstDetailsId;
	}
	public void setSupplierTinOrCstDetailsId(int supplierTinOrCstDetailsId) {
		SupplierTinOrCstDetailsId = supplierTinOrCstDetailsId;
	}
	public String getTinOrCstDocumentNo() {
		return TinOrCstDocumentNo;
	}
	public void setTinOrCstDocumentNo(String tinOrCstDocumentNo) {
		TinOrCstDocumentNo = tinOrCstDocumentNo;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
