package com.bugfree.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.bugfree.common.UploadImage;
import com.bugfree.model.brand.Brand;
import com.bugfree.model.category.Category;
import com.bugfree.model.subcategory.SubCategory;
import com.bugfree.model.supplier.Supplier;
import com.bugfree.model.unit.MeasuredValue;
import com.bugfree.model.unit.Unit;


@Entity
@Table(name="product", uniqueConstraints = @UniqueConstraint(columnNames = {"product_name", "packaged_quantity", "brand_id"}))
public class Product extends UploadImage {

	@Id
	@GeneratedValue
	private int productId;
	
	@Column(name="product_name")
	private String productName;
	

	@OneToOne
	@JoinColumn(name="product_description")
	private ProductDescription productDescription;
	
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name="sub_category_id")
	private SubCategory subCategory;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
//	@ManyToOne
//	@JoinColumn(name="unit_id")
//	private Unit unit;
	
//	@OneToOne
//	@JoinColumn(name="measured_value")
//	private MeasuredValue measuredValue;
//	
	@OneToOne
	@JoinColumn(name="image_id")
	private ProductImage productImage;
	
	private String packagedUnit;
	@Column(name="packaged_quantity")
	private float packagedQuantity;
	
//	 @ManyToOne
//	 @JoinColumn(name="rating_id")
//	 private Rating rating;
	
//	 @ManyToOne
//	 @JoinColumn(name="tax_id")
//	 private Tax tax;
	 
	@Column(nullable = false) 
	private long weightInGrams;
	
//	@ManyToOne
//	@JoinColumn(name="cost_id")
//	private Cost cost;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
//	public SubCategory getSubCategory() {
//		return subCategory;
//	}
//
//	public void setSubCategory(SubCategory subCategory) {
//		this.subCategory = subCategory;
//	}
	
	
//	public MeasuredValue getMeasuredValue() {
//		return measuredValue;
//	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

//	public void setMeasuredValue(MeasuredValue measuredValue) {
//		this.measuredValue = measuredValue;
//	}

	public ProductDescription getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}

	public Category getCategory() {
		return category;
	}

	public ProductImage getProductImage() {
		return productImage;
	}

	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

//	public Unit getUnit() {
//		return unit;
//	}
//
//	public void setUnit(Unit unit) {
//		this.unit = unit;
//	}

//	public Tax getTax() {
//		return tax;
//	}
//
//	public void setTax(Tax tax) {
//		this.tax = tax;
//	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

//	 public Rating getRating() {
//	 	return rating;
//	 }

//	 public void setRating(Rating rating) {
//	 	this.rating = rating;
//	 }

	public long getWeightInGrams() {
		return weightInGrams;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public void setWeightInGrams(long weightInGrams) {
		this.weightInGrams = weightInGrams;
	}

	public String getPackagedUnit() {
		return packagedUnit;
	}

	public void setPackagedUnit(String packagedUnit) {
		this.packagedUnit = packagedUnit;
	}

	public float getPackagedQuantity() {
		return packagedQuantity;
	}

	public void setPackagedQuantity(float packagedQuantity) {
		this.packagedQuantity = packagedQuantity;
	}

	

//	public Cost getCost() {
//		return cost;
//	}
//
//	public void setCost(Cost cost) {
//		this.cost = cost;
//	}
//	
	
	
}
