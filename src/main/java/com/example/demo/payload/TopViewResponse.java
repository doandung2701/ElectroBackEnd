package com.example.demo.payload;

import java.io.Serializable;

public class TopViewResponse implements Serializable {

    private String productId;

    private String thumbnail;

    private String productName;

    public String getProductId() {
        return productId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public TopViewResponse(String productId, String thumbnail, String productName) {
        this.productId = productId;
        this.thumbnail = thumbnail;
        this.productName = productName;
    }
}
