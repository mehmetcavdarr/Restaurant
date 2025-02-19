package com.example.Restaurant.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String description;
    Double price;
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @Transient
    private String categoryName;

    @PostLoad
    private void setCategoryNameFromCategory() {
        if (category != null) {
            this.categoryName = category.getName();
        }
    }

    public String getCategoryName() {
        return categoryName;
    }

    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }
}
