package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="productdetail")
public class ProductDetail implements Serializable {

	@Id
	@Column(name="product_detail_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long prodDetailId;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@Fetch(FetchMode.JOIN)
	@Basic(fetch=FetchType.LAZY)
	@JsonIgnore
	private Product product;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="color")
	private String color;
	
	@Column(name="descript")
	private String description;
	
	@Column(name="stock_quantity")
	private Long stockQuantity;
	
	@Column(name="discount")
	private Double discount;
	
	@Column(name="discount_ex_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date discountExDate;
	
	@OneToMany(mappedBy="productDetail",cascade=CascadeType.ALL,orphanRemoval=true)
	@Fetch(FetchMode.JOIN)
	@Basic(fetch=FetchType.LAZY)
	private Set<ProductImg> productImg;

	@OneToMany(mappedBy = "productDetail",fetch = FetchType.LAZY,
			orphanRemoval = true,cascade =  CascadeType.ALL)
	@JsonIgnore
	private Set<OrderDetail> orderDetails;

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * @return the prodDetailId
	 */
	public Long getProdDetailId() {
		return prodDetailId;
	}

	/**
	 * @param prodDetailId the prodDetailId to set
	 */
	public void setProdDetailId(Long prodDetailId) {
		this.prodDetailId = prodDetailId;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the stockQuantity
	 */
	public Long getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * @param stockQuantity the stockQuantity to set
	 */
	public void setStockQuantity(Long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * @return the discountExDate
	 */
	public Date getDiscountExDate() {
		return discountExDate;
	}

	/**
	 * @param discountExDate the discountExDate to set
	 */
	public void setDiscountExDate(Date discountExDate) {
		this.discountExDate = discountExDate;
	}

	/**
	 * @return the productImg
	 */
	public Set<ProductImg> getProductImg() {
		return productImg;
	}

	/**
	 * @param productImg the productImg to set
	 */
	public void setProductImg(Set<ProductImg> productImg) {
		this.productImg = productImg;
	}

	public ProductDetail(Product product, Double price, String color, String description, Long stockQuantity,
			Double discount, Date discountExDate, Set<ProductImg> productImg) {
		super();
		this.product = product;
		this.price = price;
		this.color = color;
		this.description = description;
		this.stockQuantity = stockQuantity;
		this.discount = discount;
		this.discountExDate = discountExDate;
		this.productImg = productImg;
	}

	public ProductDetail() {
		super();
	}
	
	
}
