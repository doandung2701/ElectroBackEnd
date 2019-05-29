package com.example.demo.repo;

import com.example.demo.model.Oder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Oder,Long> {

    List<Oder> findDistinctByUserUserId(Long id);

    List<Oder> findDistinctByUserUserId(Long id, Pageable pageable);

    Long countDistinctByUserUserId(Long id);
}
