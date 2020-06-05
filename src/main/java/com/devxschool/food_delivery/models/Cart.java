package com.devxschool.food_delivery.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class Cart {

    private UUID uuid;
    private List<CartItem> cartItems = new ArrayList<>();
    private BigDecimal totalPrice;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void updatePrice(){
        Function<CartItem, BigDecimal> totalMapper = CartItem::getTotalPrice;
                BigDecimal totalPrice = cartItems
                .stream()
                .map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalPrice(totalPrice);
    }

}
