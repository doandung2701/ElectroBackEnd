package com.example.demo.repo;

import com.example.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review,Long> {

    @Query("select r from Review r left outer join fetch r.product p where p.productId =:id" +
            " order by r.reviewTime desc")
    List<Review> findByProductId(@Param("id") String id);

}
