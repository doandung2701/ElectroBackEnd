package com.example.demo.service;

import com.example.demo.model.Review;

import java.util.List;

public interface ReviewService {

    List<Review> findByProductId(String id);

    Review save(Review review);

}
