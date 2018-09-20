package com.bugfree.model.social.network;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.bugfree.model.user.User;

@Entity
@Table(name="user_neighbour", uniqueConstraints=@UniqueConstraint(columnNames={"neighbour1_id","neighbour2_id"}))
public class UserNeighbour {


	@Id
	@GeneratedValue
	private int userNeighbourId;
	
	@ManyToOne
	@JoinColumn(name="neighbour1_id")
	private User neighbour1;
	
	@ManyToOne
	@JoinColumn(name="neighbour2_id")
	private User neighbour2;
	
	private Integer relationBreakUpInitiatedBy;
	
	@Type(type="date")
	private Date initiatedDate;
	
	@Type(type="date")
	private Date relationBreakInitiatedDate;
	
	@Type(type="date")
	private Date acceptedDate;
	
	@Type(type="date")
	private Date updatedDate;
	
	private String StatusValue;
	
	@PrePersist
	private void onCreate(){
		Date date =new Date();
		this.initiatedDate=date;
	}
	@PreUpdate
	private void onUpdate(){
		Date date =new Date ();
		this.updatedDate=date;
	}
	public int getUserNeighbourId() {
		return userNeighbourId;
	}
	public void setUserNeighbourId(int userNeighbourId) {
		this.userNeighbourId = userNeighbourId;
	}
	
	public User getNeighbour1() {
		return neighbour1;
	}
	public void setNeighbour1(User neighbour1) {
		this.neighbour1 = neighbour1;
	}
	public User getNeighbour2() {
		return neighbour2;
	}
	public void setNeighbour2(User neighbour2) {
		this.neighbour2 = neighbour2;
	}
	
	public int getRelationBreakUpInitiatedBy() {
		return relationBreakUpInitiatedBy;
	}
	public void setRelationBreakUpInitiatedBy(int relationBreakUpInitiatedBy) {
		this.relationBreakUpInitiatedBy = relationBreakUpInitiatedBy;
	}
	public Date getInitiatedDate() {
		return initiatedDate;
	}
	public void setInitiatedDate(Date initiatedDate) {
		this.initiatedDate = initiatedDate;
	}
	public Date getRelationBreakInitiatedDate() {
		return relationBreakInitiatedDate;
	}
	public void setRelationBreakInitiatedDate(Date relationBreakInitiatedDate) {
		this.relationBreakInitiatedDate = relationBreakInitiatedDate;
	}
	public Date getAcceptedDate() {
		return acceptedDate;
	}
	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStatusValue() {
		return StatusValue;
	}
	public void setStatusValue(String statusValue) {
		StatusValue = statusValue;
	}
	
	
}
