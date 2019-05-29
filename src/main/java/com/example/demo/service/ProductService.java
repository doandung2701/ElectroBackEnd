package com.example.demo.service;


import java.util.List;
import java.util.Set;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Product;

public interface ProductService {
	
	public Product findById(String id);
	
	public Page<Product> findAllWithPaging(Pageable pageable);
	
	public Product findByImgId(Long id);
	
	public long count();
	
	public List<Product> findByName(String name);

	public List<Product> findByBrandId(String id,Pageable pageable);

	public Long countByBrand(String brandId);

	public Long countByCategory(Category category);

	public List<Product> findByCategories(Category category,Pageable pageable);

	Set<Product> filterPrice(Double priceFrom, Double priceTo);

	Set<Product> findDistinctByAvgReviewScoreGreaterThanEqual(Float score);

	Product save(Product product);

	Set<Product> findFavoriteProductsByUserId(Long id);

	List<Product> findSeenProductsByUserId(Long id,Pageable pageable);

	Product findSeenProductsByUserIdAndProductId(Long id, String prodId);

	Long countBySeenUsers(User user);


}
