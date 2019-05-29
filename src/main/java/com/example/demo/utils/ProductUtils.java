package com.example.demo.utils;

import com.example.demo.model.Product;
import com.example.demo.model.ProductDetail;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductUtils {

    public boolean isInStock(Product product){
        Set<ProductDetail> productDetails = product.getProductDetails();
        if (productDetails.size()==0||productDetails==null){
            return false;
        }
        Set<ProductDetail> productDetails1 = productDetails.stream().filter(productDetail -> {
            return productDetail.getStockQuantity()>0;
        }).collect(Collectors.toSet());
        if (productDetails1.size()==0||productDetails1==null){
            return false;
        }
        return true;
    }



}
