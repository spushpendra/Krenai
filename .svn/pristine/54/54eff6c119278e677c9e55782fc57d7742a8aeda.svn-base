package com.bugfree.model.admin.util;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

import com.bugfree.model.status.Status;

@Entity
public class DuplicateEmailRequest {

	@Id
	@GeneratedValue
	private long duplicateEmailRequestId;
	
	private String emailConflict;
	private String existingUserContact;
	private String newUserContact;
	@ManyToOne
	@JoinColumn(name="status_id")
	private Status existingUserStatus;
	private String userType;
	@Type(type="timestamp")
	private Date createdTimestamp;
	
	@PrePersist
	private void onCreate(){
		this.createdTimestamp = new Date();
	}

	public long getDuplicateEmailRequestId() {
		return duplicateEmailRequestId;
	}

	public void setDuplicateEmailRequestId(long duplicateEmailRequestId) {
		this.duplicateEmailRequestId = duplicateEmailRequestId;
	}

	public String getEmailConflict() {
		return emailConflict;
	}

	public void setEmailConflict(String emailConflict) {
		this.emailConflict = emailConflict;
	}

	public String getExistingUserContact() {
		return existingUserContact;
	}

	public void setExistingUserContact(String existingUserContact) {
		this.existingUserContact = existingUserContact;
	}

	public String getNewUserContact() {
		return newUserContact;
	}

	public void setNewUserContact(String newUserContact) {
		this.newUserContact = newUserContact;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Status getExistingUserStatus() {
		return existingUserStatus;
	}

	public void setExistingUserStatus(Status status) {
		this.existingUserStatus = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
