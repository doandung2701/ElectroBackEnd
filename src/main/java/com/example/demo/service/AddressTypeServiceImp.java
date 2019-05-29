package com.example.demo.service;

import com.example.demo.model.AddressType;
import com.example.demo.repo.AdressTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class AddressTypeServiceImp implements AddressTypeService {

    @Autowired
    private AdressTypeRepo adressTypeRepo;

    @Override
    @Transactional(readOnly = true)
    public List<AddressType> findAll() {
        return adressTypeRepo.findAll();
    }

    @Override
    public AddressType save(AddressType addressType) {
        return adressTypeRepo.save(addressType);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressType findById(Long id) {
        return adressTypeRepo.findById(id).get();
    }
}
