package com.example.demo.service;

import com.example.demo.model.DeliveryAddress;
import com.example.demo.repo.DeliveryAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Repository
public class DeliveryAddressServiceImp implements DeliveryAddressService {

    @Autowired
    private DeliveryAddressRepo deliveryAddressRepo;

    @Override
    public void delete(DeliveryAddress address) {
        deliveryAddressRepo.delete(address);
    }

    @Override
    public DeliveryAddress findById(Long id) {
        return deliveryAddressRepo.findById(id).get();
    }

    @Override
    public DeliveryAddress save(DeliveryAddress deliveryAddress) {
        return deliveryAddressRepo.save(deliveryAddress);
    }

    @Override
    public List<DeliveryAddress> findAll() {
        return deliveryAddressRepo.findAll();
    }

    @Override
    public List<DeliveryAddress> findByUserId(Long id) {
        return deliveryAddressRepo.findDistinctByUserUserId(id);
    }
}
