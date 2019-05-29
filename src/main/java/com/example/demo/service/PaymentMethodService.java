package com.example.demo.service;

import com.example.demo.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {

    PaymentMethod save(PaymentMethod method);

    List<PaymentMethod> findAll();

    PaymentMethod findById(String id);

}
