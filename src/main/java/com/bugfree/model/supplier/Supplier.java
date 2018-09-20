package com.bugfree.model.supplier;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.bugfree.common.KrenaiCONSTANTS;
import com.bugfree.model.address.Address;
import com.bugfree.model.category.Category;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.shop.SupplierShop;

@Entity
@Table(name="supplier")
public class Supplier {

 @Id
 @GeneratedValue
 private int supplierId;
 
// private String firstName;
// private String middleName;
// private String lastName;
 private String fullName;
 private int minimumOrderValue;
 private int maximumDeliveryDistance;
 
 @OneToOne
 @JoinColumn(name="status_id")
 private Status status;
 
 @Type(type="date")
 private Date createdDate;
 
 private String storeType;
 
 private String requestedCall;
 
 @Type(type="timestamp")
 private Date requestedCallDate;
 
 @OneToOne
 @JoinColumn(name="category_id")
 private Category storeTypePrimary;
 
 private int otp;
 
 @Type(type="date")
 private Date otpSessionDate;
 
 
 @OneToOne
 @JoinColumn(name="address_id")
 private Address address;
 
 @OneToOne
 @JoinColumn(name="supplier_shop_id")
 private SupplierShop supplierShop;
 
 private String contactNo;
 
 private boolean serviceProvider;
 
 @Column(unique=true)
 private String uniqueStoreName;
 
 @Column(unique=true)
 private String emailId;
 @Column(length=1200)
 private String password;
 @Column(length=1200)
 private String salt;
 private String imagePath;
 private String deviceToken;
 
 public Address getAddress() {
  return address;
 }

 public void setAddress(Address address) {
  this.address = address;
 }

 public String getImagePath() {
  return imagePath;
 }

 public void setImagePath(String imagePath) {
  this.imagePath = imagePath;
 }
 
 
 @PrePersist
 protected void onCreate() {
     createdDate = KrenaiCONSTANTS.getCurrentDate();
     this.minimumOrderValue=150;
     this.maximumDeliveryDistance=4;
//     this.fullName = this.firstName+" "+this.lastName;
   }


 public int getSupplierId() {
  return supplierId;
 }

 public void setSupplierId(int supplierId) {
  this.supplierId = supplierId;
 }
 
 
 
 public int getOtp() {
  return otp;
 }

 public void setOtp(int otp) {
  this.otp = otp;
 }

 public Date getOtpSessionDate() {
  return otpSessionDate;
 }

 public void setOtpSessionDate(Date otpSessionDate) {
  this.otpSessionDate = otpSessionDate;
 }

 public SupplierShop getSupplierShop() {
  return supplierShop;
 }

 public void setSupplierShop(SupplierShop supplierShop) {
  this.supplierShop = supplierShop;
 }



 public String getFullName() {
  return this.fullName;
 }

 public void setFullName(String fullName) {
  this.fullName = fullName;
 }
 
 

 public String getContactNo() {
  return contactNo;
 }

 public void setContactNo(String contactNo) {
  this.contactNo = contactNo;
 }

 public String getEmailId() {
  return emailId;
 }

 public void setEmailId(String emailId) {
  this.emailId = emailId;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getSalt() {
  return salt;
 }

 public void setSalt(String salt) {
  this.salt = salt;
 }

 public Status getStatus() {
  return status;
 }

 public void setStatus(Status status) {
  this.status = status;
 }

 public Date getCreatedDate() {
  return createdDate;
 }

 public void setCreatedDate(Date createdDate) {
  this.createdDate = createdDate;
 }

 public String getStoreType() {
  return storeType;
 }

 public void setStoreType(String storeType) {
  this.storeType = storeType;
 }

 public boolean isServiceProvider() {
  return serviceProvider;
 }

 public void setServiceProvider(boolean serviceProvider) {
  this.serviceProvider = serviceProvider;
 }

 public String getUniqueStoreName() {
  return uniqueStoreName;
 }

 public void setUniqueStoreName(String uniqueStoreName) {
  this.uniqueStoreName = uniqueStoreName;
 }

 public int getMinimumOrderValue() {
  return minimumOrderValue;
 }

 public void setMinimumOrderValue(int minimumOrderValue) {
  this.minimumOrderValue = minimumOrderValue;
 }

 public String getDeviceToken() {
  return deviceToken;
 }

 public void setDeviceToken(String deviceToken) {
  this.deviceToken = deviceToken;
 }

 public Category getStoreTypePrimary() {
  return storeTypePrimary;
 }

 public void setStoreTypePrimary(Category storeTypePrimary) {
  this.storeTypePrimary = storeTypePrimary;
 }

 public int getMaximumDeliveryDistance() {
  return maximumDeliveryDistance;
 }

 public void setMaximumDeliveryDistance(int maximumDeliveryDistance) {
  this.maximumDeliveryDistance = maximumDeliveryDistance;
 }

public String getRequestedCall() {
	return requestedCall;
}

public void setRequestedCall(String requestedCall) {
	this.requestedCall = requestedCall;
}

public Date getRequestedCallDate() {
	return requestedCallDate;
}

public void setRequestedCallDate(Date requestedCallDate) {
	this.requestedCallDate = requestedCallDate;
}

 
 

 
}