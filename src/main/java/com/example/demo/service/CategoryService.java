package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<Category> findAll();

    Category findById(String id);

   List<Category> findByName(String name);

    Set<Category> findByCategoryIdIn(Set<String> ids);

}
