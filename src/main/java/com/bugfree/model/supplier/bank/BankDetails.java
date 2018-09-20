package com.bugfree.model.supplier.bank;

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
@Table(name="supplier_bank_details")
public class BankDetails {

	@Id
	@GeneratedValue
	private int bankDetailsId;
	
	@OneToOne
	@JoinColumn(name="supplier")
	private Supplier supplier;

	private String bankName;
	private String branchName;
	private String acountHolderName;
	private long accountNo;
	private String ifscCode;
	private String tinORvatNo;
	private String addressProofDocument;
	private String addressProofDocumentNo;
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAcountHolderName() {
		return acountHolderName;
	}

	public void setAcountHolderName(String acountHolderName) {
		this.acountHolderName = acountHolderName;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getTinORvatNo() {
		return tinORvatNo;
	}

	public void setTinORvatNo(String tinORvatNo) {
		this.tinORvatNo = tinORvatNo;
	}

	public String getAddressProofDocument() {
		return addressProofDocument;
	}

	public void setAddressProofDocument(String addressProofDocument) {
		this.addressProofDocument = addressProofDocument;
	}

	public String getAddressProofDocumentNo() {
		return addressProofDocumentNo;
	}

	public void setAddressProofDocumentNo(String addressProofDocumentNo) {
		this.addressProofDocumentNo = addressProofDocumentNo;
	}

	public int getBankDetailsId() {
		return bankDetailsId;
	}

	public void setBankDetailsId(int bankDetailsId) {
		this.bankDetailsId = bankDetailsId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
	
}
