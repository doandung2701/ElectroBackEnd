package com.example.demo.repo;

import com.example.demo.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepo extends JpaRepository<DeliveryAddress,Long> {

    List<DeliveryAddress> findDistinctByUserUserId(Long id);

}
