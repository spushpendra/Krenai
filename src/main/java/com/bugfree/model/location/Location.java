package com.bugfree.model.location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name="location")
public class Location {

	@Id
	@GeneratedValue
	private int locationId;
	
	private Double locationCordinates;
	
	private String area;

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public Double getLocationCordinates() {
		return locationCordinates;
	}

	public void setLocationCordinates(Double locationCordinates) {
		this.locationCordinates = locationCordinates;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
}
