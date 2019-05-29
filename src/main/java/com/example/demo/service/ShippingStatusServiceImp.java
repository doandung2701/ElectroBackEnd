package com.example.demo.service;

import com.example.demo.model.ShippingStatus;
import com.example.demo.repo.ShippingStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Service
@Transactional
public class ShippingStatusServiceImp implements ShippingStatusService {

    @Autowired
    private ShippingStatusRepo shippingStatusRepo;

    @Override
    @Transactional(readOnly = true)
    public List<ShippingStatus> findAll() {
        return shippingStatusRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ShippingStatus findById(Long id) {
        return shippingStatusRepo.findById(id).get();
    }
}
