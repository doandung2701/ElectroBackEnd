package com.example.demo.service;

import com.example.demo.model.Oder;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<Oder> findByUserId(Long id);

    List<Oder> findByUserId(Long id,Pageable pageable);

    List<Oder> findAllPaging(Pageable pageable);

    List<Oder> findAll();

    Oder save(Oder order);

    Long count();

    Long countByUserId(Long id);
}
