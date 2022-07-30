package com.glovoapp.backender.repositories;

import com.glovoapp.backender.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends JpaRepository<Order, Integer> { }

