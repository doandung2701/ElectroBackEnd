package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;

@Service
@Repository
@Transactional
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    @Transactional(readOnly = true)
    public Product findById(String id) {
        return productRepo.findById(id).get();
    }

    @Transactional(readOnly = true)
    public Page<Product> findAllWithPaging(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product findByImgId(Long id) {
        return productRepo.findByImgId(id);
    }

    @Override
    public Long countByCategory(Category category) {
        return productRepo.countByCategories(category);
    }

    @Override
    public List<Product> findByCategories(Category category, Pageable pageable) {
        return productRepo.findByCategories(category, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return productRepo.count();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public List<Product> findByBrandId(String id, Pageable pageable) {
        return productRepo.findByBrandId(id, pageable);
    }

    @Override
    public Long countByBrand(String brandId) {
        return productRepo.countByBrand_BrandId(brandId);
    }

    @Override
    public Set<Product> filterPrice(Double priceFrom, Double priceTo) {
        Set<Product> products;
        if (priceFrom == null || priceTo == null || priceFrom == 0 || priceTo == 0 || priceFrom >= priceTo) {
            products = new HashSet<>(productRepo.findAll());
            return products;
        }
        return productRepo.filterPrice(priceFrom, priceTo);
    }

    @Override
    public Set<Product> findDistinctByAvgReviewScoreGreaterThanEqual(Float score) {
        if (score == null || score == 0) {
            return new HashSet<>(productRepo.findAll());
        }
        return productRepo.findDistinctByAvgReviewScoreGreaterThanEqual(score);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Set<Product> findFavoriteProductsByUserId(Long id) {
        return productRepo.findFavoriteProductsByUserId(id);
    }

    @Override
    public List<Product> findSeenProductsByUserId(Long id, Pageable pageable) {
        return productRepo.findSeenProductsByUserId(id, pageable);
    }

    @Override
    public Product findSeenProductsByUserIdAndProductId(Long id, String prodId) {
        return productRepo.findSeenProductsByUserIdAndProductId(id, prodId);
    }

    @Override
    public Long countBySeenUsers(User user) {
        return productRepo.countBySeenUsers(user);
    }
}
