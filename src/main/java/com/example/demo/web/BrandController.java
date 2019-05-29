package com.example.demo.web;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.service.BrandService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/brands")
@CrossOrigin("*")
public class BrandController {

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/find/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/find/by-category")
    public ResponseEntity<?> findByCategories(@Nullable @RequestParam("catId") Set<String> catIds) {
        List<Brand> brands;
        if (catIds == null||catIds.size()==0) {
            brands = brandService.findAll();
        } else {
            Set<Category> categories = categoryService.findByCategoryIdIn(catIds);
            brands = brandService.findByProductsCategory(categories);
        }
        return ResponseEntity.ok(brands);
    }
}
