package com.example.demo.repo;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CategoryRepo extends JpaRepository<Category,String> {

    List<Category> findByCategoryName(String name);

    Set<Category> findByCategoryIdIn(Set<String> ids);

}
