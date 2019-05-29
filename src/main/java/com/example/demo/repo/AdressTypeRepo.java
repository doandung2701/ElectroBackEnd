package com.example.demo.repo;

import com.example.demo.model.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressTypeRepo extends JpaRepository<AddressType,Long> {
}
