package com.bugfree.model.unit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bugfree.model.product.Product;

@Entity
@Table(name="measured_value")
public class MeasuredValue {

	@Id
	@GeneratedValue
	private int measuredValueId;
	
	private String measuredValue;
	
	
	public int getMeasuredValueId() {
		return measuredValueId;
	}

	public void setMeasuredValueId(int measuredValueId) {
		this.measuredValueId = measuredValueId;
	}

	public String getMeasuredValue() {
		return measuredValue;
	}

	public void setMeasuredValue(String measuredValue) {
		this.measuredValue = measuredValue;
	}

//	public Product getProduct() {
//		return product;
//	}

//	public void setProduct(Product product) {
//		this.product = product;
//	}
	
	
}
