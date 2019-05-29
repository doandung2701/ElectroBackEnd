package com.example.demo.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Product;

public interface ProductRepo extends JpaRepository<Product, String> {

	@Query("select p from Product p left outer join fetch p.productDetails pd left outer join"
			+ " fetch pd.productImg pi where pi.imgId=:id ")
	Product findByImgId(@Param(value="id") Long id);
	
	@Query("select p from Product p where lower(p.productName) like %:name%")
	List<Product> findByName(@Param("name") String name);

	@Query("select p from Product p left outer join fetch p.brand b where b.brandId=:id")
	List<Product> findByBrandId(@Param("id") String id, Pageable pageable);

	List<Product> findByCategories(Category category,Pageable pageable);

	Long countByCategories(Category category);

	Long countByBrand_BrandId(String id);

	@Query("select distinct p from Product p left outer join fetch p.productDetails pd where pd.price" +
			" between :priceFrom and :priceTo")
	Set<Product> filterPrice(@Param("priceFrom")double priceFrom, @Param("priceTo") double priceTo);

	Set<Product> findDistinctByAvgReviewScoreGreaterThanEqual(Float score);

	@Query("select distinct p from Product p left outer join fetch p.seenUsers su where su.userId=:id")
	List<Product> findSeenProductsByUserId(@Param("id") Long id,Pageable pageable);

	@Query("select p from Product p left outer join fetch p.favoriteUsers fu where fu.userId=:id")
	Set<Product> findFavoriteProductsByUserId(@Param("id") Long id);

	@Query("select p from Product p left outer join fetch p.seenUsers su where su.userId=:id and p.productId=:prodId")
	Product findSeenProductsByUserIdAndProductId(@Param("id") Long id,@Param("prodId") String prodId);

	Long countBySeenUsers(User user);
}
