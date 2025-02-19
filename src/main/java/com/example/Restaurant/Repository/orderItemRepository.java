package com.example.Restaurant.Repository;

import com.example.Restaurant.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderItemRepository extends JpaRepository<OrderItem,Long> {
}
