package com.example.demo.service;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;

import java.util.List;
import java.util.Set;

public interface BrandService {

    List<Brand> findAll();

    Brand findById(String id);

    List<Brand> findByName(String name);

    List<Brand> findByProductsCategory( Set<Category> categories);


}
