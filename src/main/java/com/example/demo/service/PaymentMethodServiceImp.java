package com.example.demo.service;

import com.example.demo.model.PaymentMethod;
import com.example.demo.repo.PaymentMethodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Repository
public class PaymentMethodServiceImp implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Override
    public PaymentMethod save(PaymentMethod method) {
        return paymentMethodRepo.save(method);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return paymentMethodRepo.findAll();
    }

    @Override
    public PaymentMethod findById(String id) {
        return paymentMethodRepo.findById(id).get();
    }
}
