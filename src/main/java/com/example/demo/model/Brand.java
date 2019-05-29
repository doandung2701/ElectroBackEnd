package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="brand")
public class Brand implements Serializable{

	@Id
	@Column(name="brand_id")
	private String brandId;
	
	@Column(name="brand_name")
	private String brandName;

	@OneToMany(mappedBy="brand",cascade=CascadeType.ALL,orphanRemoval=true)
	@Fetch(FetchMode.JOIN)
	@Basic(fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Product> products;
	
	/**
	 * @return the brandId
	 */
	public String getBrandId() {
		return brandId;
	}
	
	

	/**
	 * @return the products
	 */
	public Set<Product> getProducts() {
		return products;
	}



	/**
	 * @param products the products to set
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}



	/**
	 * @param brandId the brandId to set
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Brand(String brandId, String brandName) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
	}

	public Brand() {
		super();
	}
	
	
	
	
	
}
