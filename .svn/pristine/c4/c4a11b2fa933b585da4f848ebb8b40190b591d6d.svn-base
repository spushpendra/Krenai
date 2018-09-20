package com.bugfree.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_description")
public class ProductDescription {

	@Id
	@GeneratedValue
	private int productDescriptionId;
	
	
	@Column(length=1200)
	private String description;
	
	private int languageId;
	
	@Column(length=255)
	private String metaDescription;
	
	@Column(length=255)
	private String metaKeywords;

	public int getProductDescriptionId() {
		return productDescriptionId;
	}

	public void setProductDescriptionId(int productDescriptionId) {
		this.productDescriptionId = productDescriptionId;
	}

//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	
}
