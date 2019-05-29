package com.example.demo.payload;

import java.io.Serializable;
import java.util.Date;

public class AddReviewRequest implements Serializable {

    private String reviewHeader;

    private String reviewContent;

    private String productId;

    private double reviewScore;

    public AddReviewRequest(String reviewHeader, String reviewContent, String productId, double reviewScore) {
        this.reviewHeader = reviewHeader;
        this.reviewContent = reviewContent;
        this.productId = productId;
        this.reviewScore = reviewScore;
    }

    public AddReviewRequest() {
    }

    public String getReviewHeader() {
        return reviewHeader;
    }

    public void setReviewHeader(String reviewHeader) {
        this.reviewHeader = reviewHeader;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(double reviewScore) {
        this.reviewScore = reviewScore;
    }
}
