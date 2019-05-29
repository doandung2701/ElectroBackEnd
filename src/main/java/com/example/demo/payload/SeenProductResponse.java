package com.example.demo.payload;

import java.io.Serializable;

public class SeenProductResponse implements Serializable {

    private String productId;

    private String productName;

    private String thumbnail;

    public SeenProductResponse() {
    }

    public SeenProductResponse(String productId, String productName, String thumbnail) {
        this.productId = productId;
        this.productName = productName;
        this.thumbnail = thumbnail;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
