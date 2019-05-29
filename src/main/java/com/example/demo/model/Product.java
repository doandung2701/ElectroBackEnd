package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="product")
public class Product implements Serializable{

	@Id
	@Column(name="product_id")
	private String productId;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@Column(name="product_name")
	private String productName;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<ProductDetail> productDetails;
	
	@Column(name="thumbnail")
	private String thumbnail;
	
	@Temporal(TemporalType.DATE)
	@Column(name="import_date")
	private Date importDate;

	@Column(name="viewcount")
	private Long viewCount;

	@Column(name = "full_spec")
	private String fullSpec;
	
	@ManyToMany(fetch =  FetchType.LAZY)
	@JoinTable(name = "productcategory", joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	@Column(name="avg_review_score")
	private Float avgReviewScore;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "seen_product", joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	private Set<User> seenUsers;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "favorite_product", joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	private Set<User> favoriteUsers;

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	@Basic(fetch = FetchType.LAZY)
	private Set<Review> reviews;

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private Set<Comment> comments;

	public Product(String productId, Brand brand, String productName, Set<ProductDetail> productDetails,
			String thumbnail, Date importDate, Set<Category> categories,String fullSpec) {
		super();
		this.productId = productId;
		this.brand = brand;
		this.productName = productName;
		this.productDetails = productDetails;
		this.thumbnail = thumbnail;
		this.importDate = importDate;
		this.categories = categories;
		this.fullSpec = fullSpec;
	}

	public Set<User> getSeenUsers() {
		return seenUsers;
	}

	public void setSeenUsers(Set<User> seenUsers) {
		this.seenUsers = seenUsers;
	}

	public Set<User> getFavoriteUsers() {
		return favoriteUsers;
	}

	public void setFavoriteUsers(Set<User> favoriteUsers) {
		this.favoriteUsers = favoriteUsers;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Float getAvgReviewScore() {
		return avgReviewScore;
	}

	public void setAvgReviewScore(Float avgReviewScore) {
		this.avgReviewScore = avgReviewScore;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

	public String getFullSpec() {
		return fullSpec;
	}

	public void setFullSpec(String fullSpec) {
		this.fullSpec = fullSpec;
	}

	/**
	 * @return the importDate
	 */
	public Date getImportDate() {
		return importDate;
	}


	/**
	 * @param importDate the importDate to set
	 */
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}


	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}


	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}


	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}


	/**
	 * @return the brand
	 */
	public Brand getBrand() {
		return brand;
	}


	/**
	 * @param brand the brand to set
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}


	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}


	/**
	 * @return the productDetails
	 */
	public Set<ProductDetail> getProductDetails() {
		return productDetails;
	}


	/**
	 * @param productDetails the productDetails to set
	 */
	public void setProductDetails(Set<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}


	/**
	 * @return the categories
	 */
	public Set<Category> getCategories() {
		return categories;
	}


	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Product(String productId, Brand brand, String productName, Set<Category> categories) {
		super();
		this.productId = productId;
		this.brand = brand;
		this.productName = productName;
		this.categories = categories;
	}


	public Product() {
		super();
	}
	
	
}
