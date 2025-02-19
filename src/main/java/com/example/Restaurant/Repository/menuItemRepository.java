package com.example.Restaurant.Repository;

import com.example.Restaurant.Entity.MenuItem;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface menuItemRepository extends JpaRepository<MenuItem, Long> {
	
    @EntityGraph(attributePaths = "category") // Kategoriyi de yükler

	List<MenuItem> findAll();
    
}
