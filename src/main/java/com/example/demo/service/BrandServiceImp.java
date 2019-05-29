package com.example.demo.service;

import com.example.demo.model.Brand;
import com.example.demo.model.Category;
import com.example.demo.repo.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@Repository
public class BrandServiceImp implements BrandService{

    @Autowired
    private BrandRepo brandRepo;

    @Override
    public List<Brand> findAll() {
        return brandRepo.findAll();
    }

    @Override
    public Brand findById(String id) {
        return brandRepo.findById(id).get();
    }

    @Override
    public List<Brand> findByName(String name) {
        return brandRepo.findByBrandName(name);
    }

    @Override
    public List<Brand> findByProductsCategory(Set<Category> categories){
        return brandRepo.findByProductsCategory(categories);
    }

}
