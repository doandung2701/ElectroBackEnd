package com.example.demo.payload;

import java.io.Serializable;

public class PurchaseCartRequest implements Serializable {

    private Long prodDetailId;

    private Integer quantity;

    public Long getProdDetailId() {
        return prodDetailId;
    }

    public void setProdDetailId(Long prodDetailId) {
        this.prodDetailId = prodDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
