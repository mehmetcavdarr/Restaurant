package com.example.Restaurant.Repository;

import com.example.Restaurant.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<Category,Long> {
}
