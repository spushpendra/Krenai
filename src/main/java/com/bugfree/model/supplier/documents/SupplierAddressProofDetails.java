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
@Table(name="supplier_address_proof_details")
public class SupplierAddressProofDetails {

	@Id
	@GeneratedValue
	private int SupplierAddressProofDetailsId;
	
	@Column(unique=true)
	private String addressProofDocumentId;
	@OneToOne
	@JoinColumn(name="supplier")
	private Supplier supplier;
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	private String expiryDate;
	private String documentPath;
	public int getSupplierAddressProofDetailsId() {
		return SupplierAddressProofDetailsId;
	}
	public void setSupplierAddressProofDetailsId(int supplierAddressProofDetailsId) {
		SupplierAddressProofDetailsId = supplierAddressProofDetailsId;
	}
	public String getAddressProofDocumentId() {
		return addressProofDocumentId;
	}
	public void setAddressProofDocumentId(String addressProofDocumentId) {
		this.addressProofDocumentId = addressProofDocumentId;
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
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	
	
}
