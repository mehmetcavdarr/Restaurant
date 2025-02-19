package com.example.Restaurant.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // "order" kelimesi veritabanı için rezerve olabilir, bu nedenle tablo adını "orders" yapıyoruz
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

     String name;
     String description;
     Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
     Category category;
    
    


    /**
     * Order ile OrderItem arasındaki ilişki:
     * Bir sipariş, birden çok OrderItem içerebilir.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
     List<OrderItem> orderItems = new ArrayList<>();

    // -- GETTER ve SETTER METOTLARI --

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    // -- SETTERS --
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * orderItems listesini dışarıdan tamamen değiştirmek (örneğin toplu set) istiyorsanız.
     * Genellikle tek tek ekleme (add) yapmak da bir yöntemdir.
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * Siparişe yeni bir OrderItem eklerken kullanabileceğiniz yardımcı metot (isteğe bağlı).
     */
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
