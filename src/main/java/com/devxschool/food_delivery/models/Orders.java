package com.devxschool.food_delivery.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
public class Orders {

    public enum OrderStatus {
        CONFIRMED,
        ONTHEWAY,
        DELIVERED,
        CANCELED,
        PICKUP
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private CustomUser customUser;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;

    @Enumerated
    private OrderStatus orderStatus;

    LocalDateTime orderPlacedAt;

    LocalDateTime orderUpdatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderPlacedAt() {
        return orderPlacedAt;
    }

    public void setOrderPlacedAt(LocalDateTime orderPlacedAt) {
        this.orderPlacedAt = orderPlacedAt;
    }

    public LocalDateTime getOrderUpdatedAt() {
        return orderUpdatedAt;
    }

    public void setOrderUpdatedAt(LocalDateTime orderUpdatedAt) {
        this.orderUpdatedAt = orderUpdatedAt;
    }
}
