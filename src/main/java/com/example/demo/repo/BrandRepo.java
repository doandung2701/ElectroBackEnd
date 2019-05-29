package com.example.demo.repo;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BrandRepo extends JpaRepository<Brand,String> {

    List<Brand> findByBrandName(String name);

    @Query("select distinct b from Brand b left outer join fetch b.products p left outer join fetch " +
            " p.categories c where c in (:categories)")
    List<Brand> findByProductsCategory(@Param("categories") Set<Category> categories);

}
