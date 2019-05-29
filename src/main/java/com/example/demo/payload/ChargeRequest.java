package com.example.demo.payload;

import com.example.demo.model.DeliveryAddress;

import java.io.Serializable;
import java.util.List;

public class ChargeRequest implements Serializable {

    private Long deliveryAddress;

    private String paymentMethodId;

    private List<PurchaseCartRequest> purchaseCartRequests;

    public Long getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Long deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public List<PurchaseCartRequest> getPurchaseCartRequests() {
        return purchaseCartRequests;
    }

    public void setPurchaseCartRequests(List<PurchaseCartRequest> purchaseCartRequests) {
        this.purchaseCartRequests = purchaseCartRequests;
    }
}
