package com.example.demo.service;

import com.example.demo.model.ProductDetail;
import com.example.demo.repo.ProductDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class ProductDetailServiceImp implements ProductDetailService {

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Override
    public List<ProductDetail> findByProdDetailIdIn(List<Long> id) {
        return productDetailRepo.findDistinctByProdDetailIdIsIn(id);
    }

    @Override
    public ProductDetail findById(Long id) {
        return productDetailRepo.findById(id).get();
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) {
        return productDetailRepo.save(productDetail);
    }
}
