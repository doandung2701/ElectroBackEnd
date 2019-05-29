package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Service
@Transactional
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(String id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepo.findByCategoryName(name);
    }

    @Override
    public Set<Category> findByCategoryIdIn(Set<String> ids) {
        return categoryRepo.findByCategoryIdIn(ids);
    }
}
