package com.example.demo.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="products")
@Component
public class Product {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long productId;
    private String productName;
    private int price,stock;
    public Product() {
    }
    public Product(String productName, Long productId, int price, int stock) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.stock = stock;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
