package com.bugfree.model.payment.packages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="krenai_monthly_subscription_package")
public class MontlySubscriptionPackage {
	
	@Id
	@GeneratedValue
	private int montlySubscriptionPackageId;
	
	private float packageAmount;
	
	@Column(length=800)
	private String packageDescription;
	
	@Column(unique=true)
	private String packageName;
	
	private int totalSubscriptionDays;
	
	private int subscriptionMonths;
	
	public int getMontlySubscriptionPackageId() {
		return montlySubscriptionPackageId;
	}
	public void setMontlySubscriptionPackageId(int montlySubscriptionPackageId) {
		this.montlySubscriptionPackageId = montlySubscriptionPackageId;
	}
	
	public float getPackageAmount() {
		return packageAmount;
	}
	public void setPackageAmount(float packageAmount) {
		this.packageAmount = packageAmount;
	}
	public String getPackageDescription() {
		return packageDescription;
	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getTotalSubscriptionDays() {
		return totalSubscriptionDays;
	}
	public void setTotalSubscriptionDays(int totalSubscriptionDays) {
		this.totalSubscriptionDays = totalSubscriptionDays;
	}
	public int getSubscriptionMonths() {
		return subscriptionMonths;
	}
	public void setSubscriptionMonths(int subscriptionMonths) {
		this.subscriptionMonths = subscriptionMonths;
	}
	
	
}
