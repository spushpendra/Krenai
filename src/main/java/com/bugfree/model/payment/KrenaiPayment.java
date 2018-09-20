package com.bugfree.model.payment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bugfree.model.payment.packages.MontlySubscriptionPackage;
import com.bugfree.model.supplier.Supplier;

//import com.bugfree.model.payment.packages.MontlySubscriptionPackage;
//import com.bugfree.model.supplier.Supplier;

@Entity
@Table(name="krenai_payment")
public class KrenaiPayment {

	@Id
	@GeneratedValue
	private long krenaiPaymentId;
	
	@ManyToOne
	@JoinColumn(name="montly_subscription_package_id")
	private MontlySubscriptionPackage montlySubscriptionPackage;
	
	private String paymentRecievedBy;
	private String krenaiInvoice;
	
	@Temporal(TemporalType.DATE)
	private Date serviceStartDate;
	
	@Temporal(TemporalType.DATE)
	private Date serviceEndDate;
	
	private float paidAmount;
	
	private float discountApplied;
	
	private String discountBy;
	
	@Column(length=1000)
	private String discountMessage;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	
	private String paymentMode;
	
	private String transactionId;
	
	private String chequeNo;
	
	private String status;


	public long getKrenaiPaymentId() {
		return krenaiPaymentId;
	}


	public void setKrenaiPaymentId(long krenaiPaymentId) {
		this.krenaiPaymentId = krenaiPaymentId;
	}


	public MontlySubscriptionPackage getMontlySubscriptionPackage() {
		return montlySubscriptionPackage;
	}


	public void setMontlySubscriptionPackage(MontlySubscriptionPackage montlySubscriptionPackage) {
		this.montlySubscriptionPackage = montlySubscriptionPackage;
	}


	public String getPaymentRecievedBy() {
		return paymentRecievedBy;
	}


	public void setPaymentRecievedBy(String paymentRecievedBy) {
		this.paymentRecievedBy = paymentRecievedBy;
	}


	public Date getServiceStartDate() {
		return serviceStartDate;
	}


	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}


	public Date getServiceEndDate() {
		return serviceEndDate;
	}


	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}


	public float getPaidAmount() {
		return paidAmount;
	}


	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getChequeNo() {
		return chequeNo;
	}


	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}


	public float getDiscountApplied() {
		return discountApplied;
	}


	public void setDiscountApplied(float discountApplied) {
		this.discountApplied = discountApplied;
	}


	public String getDiscountBy() {
		return discountBy;
	}


	public void setDiscountBy(String discountBy) {
		this.discountBy = discountBy;
	}


	public String getDiscountMessage() {
		return discountMessage;
	}


	public void setDiscountMessage(String discountMessage) {
		this.discountMessage = discountMessage;
	}


	public String getKrenaiInvoice() {
		return krenaiInvoice;
	}


	public void setKrenaiInvoice(String krenaiInvoice) {
		this.krenaiInvoice = krenaiInvoice;
	}

	
	

	
}
