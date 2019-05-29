package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class ReviewServiceImp implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Review> findByProductId(String id) {
        return reviewRepo.findByProductId(id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepo.saveAndFlush(review);
    }
}
