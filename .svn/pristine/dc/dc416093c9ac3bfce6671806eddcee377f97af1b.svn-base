package com.bugfree.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_address_book")
public class UserAddressBook {

	@Id
	@GeneratedValue
	private int userAddressBookId;
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;
	private String addressType;
	private double latitude;
	private double longitude;
	private String googleAddress;
	private String description;
	private boolean isDefault;
	private String userEmailId;
	private String customAddress;
	
	
	public int getUserAddressBookId() {
		return userAddressBookId;
	}
	public void setUserAddressBookId(int userAddressBookId) {
		this.userAddressBookId = userAddressBookId;
	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGoogleAddress() {
		return googleAddress;
	}
	public void setGoogleAddress(String googleAddress) {
		this.googleAddress = googleAddress;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getCustomAddress() {
		return customAddress;
	}
	public void setCustomAddress(String customAddress) {
		this.customAddress = customAddress;
	}
	
}
