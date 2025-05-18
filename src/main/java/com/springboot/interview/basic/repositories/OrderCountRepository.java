package com.springboot.interview.basic.repositories;

import com.springboot.interview.basic.entities.OrderCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderCountRepository extends JpaRepository<OrderCount,Long> {

    Optional<OrderCount> findByCustId(Long custId);
}
