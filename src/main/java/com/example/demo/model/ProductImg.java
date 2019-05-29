package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="productimg")
public class ProductImg implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="img_id")
	private Long imgId;
	
	@ManyToOne
	@JoinColumn(name="product_detail_id")
	@JsonIgnore
	private ProductDetail productDetail;
	
	@Column(name="img_url")
	private String imgUrl;

	/**
	 * @return the imgId
	 */
	public Long getImgId() {
		return imgId;
	}

	/**
	 * @param imgId the imgId to set
	 */
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	/**
	 * @return the productDetail
	 */
	public ProductDetail getProductDetail() {
		return productDetail;
	}

	/**
	 * @param productDetail the productDetail to set
	 */
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public ProductImg(ProductDetail productDetail, String imgUrl) {
		super();
		this.productDetail = productDetail;
		this.imgUrl = imgUrl;
	}

	public ProductImg() {
		super();
	}

	
}
