package com.example.Restaurant.Repository;

import com.example.Restaurant.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRepository extends JpaRepository<Order,Long> {
}
