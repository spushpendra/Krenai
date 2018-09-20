package com.bugfree.model.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_image")
public class ProductImage {

	@Id
	@GeneratedValue
	private int productImageId;
	
	@OneToOne 
	@JoinColumn(name="product_id")
	private Product product;
	
	private String imagePath1;
	private String imagePath2;
	private String imagePath3;
	private String imagePath4;
	private String imagePath5;
	private String imagePath7;
	private String imagePath6;
	private String imagePath8;
	public int getProductImageId() {
		return productImageId;
	}
	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getImagePath1() {
		return imagePath1;
	}
	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}
	public String getImagePath2() {
		return imagePath2;
	}
	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}
	public String getImagePath3() {
		return imagePath3;
	}
	public void setImagePath3(String imagePath3) {
		this.imagePath3 = imagePath3;
	}
	public String getImagePath4() {
		return imagePath4;
	}
	public void setImagePath4(String imagePath4) {
		this.imagePath4 = imagePath4;
	}
	public String getImagePath5() {
		return imagePath5;
	}
	public void setImagePath5(String imagePath5) {
		this.imagePath5 = imagePath5;
	}
	public String getImagePath7() {
		return imagePath7;
	}
	public void setImagePath7(String imagePath7) {
		this.imagePath7 = imagePath7;
	}
	public String getImagePath6() {
		return imagePath6;
	}
	public void setImagePath6(String imagePath6) {
		this.imagePath6 = imagePath6;
	}
	public String getImagePath8() {
		return imagePath8;
	}
	public void setImagePath8(String imagePath8) {
		this.imagePath8 = imagePath8;
	}
	
	
	
}
