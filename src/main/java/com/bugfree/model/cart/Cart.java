package com.bugfree.model.cart;

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

import org.hibernate.annotations.Type;

import com.bugfree.model.offer.promocode.PromoGrabed;
import com.bugfree.model.status.Status;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.user.User;
import com.bugfree.model.user.UserAddressBook;
//import com.bugfree.model.cart.OnlinePaymentInfo;

@Entity
@Table(name="cart")
public class Cart {

 @Id
 @GeneratedValue
 private int cartId;
 @ManyToOne
 @JoinColumn(name="user_id")
 private User user;
 
 /*
  * active -> ordered -> processing -> outfordelivery -> delivered
  *                           -> cancelled   -> returned
  */
 
 @ManyToOne
 @JoinColumn(name="status_id")
 private Status status;
 private String orderId;
 @Temporal(TemporalType.TIMESTAMP)
 private Date orderDate;
 private String userRequiredTime;
 private String userRequiredDate;
 @Type(type="timestamp")
 private Date deliveredTime;
 private String deliveredByAgentBoy;
 private String remarks;
 @Column(length=1000)
 private String cancellationReason;
 private float totalOrderAmount;
 private boolean payViaWallet;
 private float usedWalletbalance;
 private float pointAtRate;
 private String userAddress;
 private String orderRating;
 
 
 public float getTotalOrderAmount() {
	return totalOrderAmount;
}
public void setTotalOrderAmount(float totalOrderAmount) {
	this.totalOrderAmount = totalOrderAmount;
}
public boolean isPayViaWallet() {
	return payViaWallet;
}
public void setPayViaWallet(boolean payViaWallet) {
	this.payViaWallet = payViaWallet;
}
public float getUsedWalletbalance() {
	return usedWalletbalance;
}
public void setUsedWalletbalance(float usedWalletbalance) {
	this.usedWalletbalance = usedWalletbalance;
}
public float getPointAtRate() {
	return pointAtRate;
}
public void setPointAtRate(float pointAtRate) {
	this.pointAtRate = pointAtRate;
}
 
 @ManyToOne
 @JoinColumn(name="promo_grab_id")
 private PromoGrabed promoGrabed;
 
 private float promoDiscount;
 
 private String paymentStatus;
 //Paid , Unpaid , Free
 
 private String paymentMode;
//Online, Cash, Prepaid, Paytm
 
 private float amountReceived;
 private String deliStatusUpdBy;
 private String extraAmountRemarks;
 @Column(length=500)
 private String deliveryAddress;
 private double deliveryLatitude;
 private double deliveryLongitude;
 private String googleAddress;
 private String payStatusUpdBy;
 private String orderBy;
 private boolean expressDelivery;
 
 
 @Temporal(TemporalType.TIMESTAMP)
 private Date payStatusUpdTime;
 
 public Date getPayStatusUpdTime() {
	return payStatusUpdTime;
}
public void setPayStatusUpdTime(Date payStatusUpdTime) {
	this.payStatusUpdTime = payStatusUpdTime;
}
public String getDeliveryAddress() {
	return deliveryAddress;
}
public void setDeliveryAddress(String deliveryAddress) {
	this.deliveryAddress = deliveryAddress;
}
public double getDeliveryLatitude() {
	return deliveryLatitude;
}
public void setDeliveryLatitude(double deliveryLatitude) {
	this.deliveryLatitude = deliveryLatitude;
}
public double getDeliveryLongitude() {
	return deliveryLongitude;
}
public void setDeliveryLongitude(double deliveryLongitude) {
	this.deliveryLongitude = deliveryLongitude;
}
public String getGoogleAddress() {
	return googleAddress;
}
public void setGoogleAddress(String googleAddress) {
	this.googleAddress = googleAddress;
}
private boolean invoiceCreated;
 

public String getPaymentStatus() {
	return paymentStatus;
}
public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
}
public String getPaymentMode() {
	return paymentMode;
}
public void setPaymentMode(String paymentMode) {
	this.paymentMode = paymentMode;
}
public float getPromoDiscount() {
 	return promoDiscount;
 }
 public void setPromoDiscount(float promoDiscount) {
 	this.promoDiscount = promoDiscount;
 }
 public PromoGrabed getPromoGrabed() {
	return promoGrabed;
}
 private boolean promoApplied;
 
public void setPromoGrabed(PromoGrabed promoGrabed) {
	this.promoGrabed = promoGrabed;
}
 @Type(type="timestamp")
 private Date statusInProcessDate;
 
 @Column(length=1000)
 private String statusInProcessMessage;
 
 @Type(type="timestamp")
 private Date statusOutForDeliveryDate;
 
 @Type(type="timestamp")
 private Date statusReturnedDate;
 
 @ManyToOne
 @JoinColumn(name="supplier_Id")
 private Supplier supplier;
 
 @ManyToOne
 @JoinColumn(name="user_address_book_id")
 private UserAddressBook userAddressBookId;
 
 
 public Date getOrderDate() {
	return orderDate;
}
public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}
public String getOrderId() {
  return orderId;
 }

public void setOrderId(String orderId) {
  this.orderId = orderId;
 }
 public int getCartId() {
  return cartId;
 }
 public void setCartId(int cartId) {
  this.cartId = cartId;
 }
 public User getUser() {
  return user;
 }
 public void setUser(User user) {
  this.user = user;
 }
 public Status getStatus() {
  return status;
 }
 public void setStatus(Status status) {
  this.status = status;
 }
 public Supplier getSupplier() {
  return supplier;
 }
 public void setSupplier(Supplier supplier) {
  this.supplier = supplier;
 }
 public String getUserRequiredTime() {
  return userRequiredTime;
 }
 public void setUserRequiredTime(String userRequiredTime) {
  this.userRequiredTime = userRequiredTime;
 }
 
 public Date getDeliveredTime() {
  return deliveredTime;
 }
 public void setDeliveredTime(Date deliveredTime) {
  this.deliveredTime = deliveredTime;
 }
 public String getDeliveredByAgentBoy() {
  return deliveredByAgentBoy;
 }
 public void setDeliveredByAgentBoy(String deliveredByAgentBoy) {
  this.deliveredByAgentBoy = deliveredByAgentBoy;
 }
 public String getRemarks() {
  return remarks;
 }
 public void setRemarks(String remarks) {
  this.remarks = remarks;
 }
 public UserAddressBook getUserAddressBookId() {
  return userAddressBookId;
 }
 public void setUserAddressBookId(UserAddressBook userAddressBookId) {
  this.userAddressBookId = userAddressBookId;
 }
 public String getUserRequiredDate() {
  return userRequiredDate;
 }
 public void setUserRequiredDate(String userRequiredDate) {
  this.userRequiredDate = userRequiredDate;
 }
 public String getCancellationReason() {
  return cancellationReason;
 }
 public void setCancellationReason(String cancellationReason) {
  this.cancellationReason = cancellationReason;
 }
 public Date getStatusInProcessDate() {
  return statusInProcessDate;
 }
 public void setStatusInProcessDate(Date statusInProcessDate) {
  this.statusInProcessDate = statusInProcessDate;
 }
 public Date getStatusOutForDeliveryDate() {
  return statusOutForDeliveryDate;
 }
 public void setStatusOutForDeliveryDate(Date statusOutForDeliveryDate) {
  this.statusOutForDeliveryDate = statusOutForDeliveryDate;
 }
 public Date getStatusReturnedDate() {
  return statusReturnedDate;
 }
 public void setStatusReturnedDate(Date statusReturnedDate) {
  this.statusReturnedDate = statusReturnedDate;
 }
public boolean isPromoApplied() {
	return promoApplied;
}
public void setPromoApplied(boolean promoApplied) {
	this.promoApplied = promoApplied;
}
public String getStatusInProcessMessage() {
	return statusInProcessMessage;
}
public void setStatusInProcessMessage(String statusInProcessMessage) {
	this.statusInProcessMessage = statusInProcessMessage;
}
public String getUserAddress() {
	return userAddress;
}
public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
}
public float getAmountReceived() {
	return amountReceived;
}
public void setAmountReceived(float amountReceived) {
	this.amountReceived = amountReceived;
}
public String getDeliStatusUpdBy() {
	return deliStatusUpdBy;
}
public void setDeliStatusUpdBy(String deliStatusUpdBy) {
	this.deliStatusUpdBy = deliStatusUpdBy;
}
public String getExtraAmountRemarks() {
	return extraAmountRemarks;
}
public void setExtraAmountRemarks(String extraAmountRemarks) {
	this.extraAmountRemarks = extraAmountRemarks;
}
public boolean isInvoiceCreated() {
	return invoiceCreated;
}
public void setInvoiceCreated(boolean invoiceCreated) {
	this.invoiceCreated = invoiceCreated;
}
public String getPayStatusUpdBy() {
	return payStatusUpdBy;
}
public void setPayStatusUpdBy(String payStatusUpdBy) {
	this.payStatusUpdBy = payStatusUpdBy;
}
public String getOrderBy() {
	return orderBy;
}
public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
}
public boolean isExpressDelivery() {
	return expressDelivery;
}
public void setExpressDelivery(boolean expressDelivery) {
	this.expressDelivery = expressDelivery;
}
public String getOrderRating() {
	return orderRating;
}
public void setOrderRating(String orderRating) {
	this.orderRating = orderRating;
}

 
 
}