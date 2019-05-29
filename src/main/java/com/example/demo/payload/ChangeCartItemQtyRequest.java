package com.example.demo.payload;

import java.io.Serializable;

public class ChangeCartItemQtyRequest implements Serializable {

    private Long prodDetailId;

    private Long currentQty;

    private Long nextQty;


    public ChangeCartItemQtyRequest() {
    }

    public ChangeCartItemQtyRequest(Long prodDetailId, Long currentQty, Long nextQty) {
        this.prodDetailId = prodDetailId;
        this.currentQty = currentQty;
        this.nextQty = nextQty;
    }


    public Long getProdDetailId() {
        return prodDetailId;
    }

    public void setProdDetailId(Long prodDetailId) {
        this.prodDetailId = prodDetailId;
    }

    public Long getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(Long currentQty) {
        this.currentQty = currentQty;
    }

    public Long getNextQty() {
        return nextQty;
    }

    public void setNextQty(Long nextQty) {
        this.nextQty = nextQty;
    }
}
