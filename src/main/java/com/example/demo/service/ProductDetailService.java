package com.example.demo.service;

import com.example.demo.model.ProductDetail;

import java.util.List;

public interface ProductDetailService {

    ProductDetail findById(Long id);

    ProductDetail save(ProductDetail productDetail);

    List<ProductDetail> findByProdDetailIdIn(List<Long> id);
}
