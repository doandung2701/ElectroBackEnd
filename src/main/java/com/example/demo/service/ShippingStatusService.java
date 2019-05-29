package com.example.demo.service;

import com.example.demo.model.ShippingStatus;

import java.util.List;

public interface ShippingStatusService {

    List<ShippingStatus> findAll();

    ShippingStatus findById(Long id);

}
