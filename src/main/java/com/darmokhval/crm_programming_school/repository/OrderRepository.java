package com.darmokhval.crm_programming_school.repository;

import com.darmokhval.crm_programming_school.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
