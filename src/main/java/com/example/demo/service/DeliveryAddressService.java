package com.example.demo.service;

import com.example.demo.model.DeliveryAddress;

import java.util.List;

public interface DeliveryAddressService {

    DeliveryAddress save(DeliveryAddress deliveryAddress);

    List<DeliveryAddress> findAll();

    List<DeliveryAddress> findByUserId(Long id);

    DeliveryAddress findById(Long id);

    void delete(DeliveryAddress address);
}
