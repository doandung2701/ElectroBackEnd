package com.example.demo.service;

import com.example.demo.model.Oder;
import com.example.demo.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Service
@Transactional
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public List<Oder> findByUserId(Long id) {
        return orderRepo.findDistinctByUserUserId(id);
    }

    @Override
    public List<Oder> findAllPaging(Pageable pageable) {
        return orderRepo.findAll(pageable).getContent();
    }

    @Override
    public List<Oder> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public Oder save(Oder order) {
        return orderRepo.save(order);
    }

    @Override
    public Long count() {
        return orderRepo.count();
    }

    @Override
    public Long countByUserId(Long id) {
        return orderRepo.countDistinctByUserUserId(id);
    }

    @Override
    public List<Oder> findByUserId(Long id, Pageable pageable) {
        return orderRepo.findDistinctByUserUserId(id,pageable);
    }
}
