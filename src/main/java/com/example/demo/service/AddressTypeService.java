package com.example.demo.service;

import com.example.demo.model.AddressType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressTypeService {

    List<AddressType> findAll();

    AddressType findById(Long id);

    AddressType save(AddressType addressType);
}
