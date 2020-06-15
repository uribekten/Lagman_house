package com.devxschool.food_delivery.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cart {

    private UUID uuid;
    private List<CartItem> cartItems = new ArrayList<>();
    private BigDecimal totalPrice;
    private String username;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    private void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        updatePrice();
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void updatePrice(){
        Function<CartItem, BigDecimal> totalMapper = CartItem::getTotalPrice;
                BigDecimal totalPrice = cartItems
                .stream()
                .map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        setTotalPrice(totalPrice);
    }

    public void addCartItem(CartItem cartItem){
        if (this.cartItems.size() == 0)
            cartItems.add(cartItem);
        else {
            CartItem oldItem = cartItems
                    .stream()
                    .filter(ci -> ci.getFood().getId() == cartItem.getFood().getId())
                    .findAny().orElse(null);
            if (oldItem == null) {
                cartItems.add(cartItem);
            } else {
                cartItems.remove(oldItem);
                oldItem.setQuantity(oldItem.getQuantity() + 1);
                cartItems.add(oldItem);
            }
        }
    }

    public void removeCartItem(CartItem cartItem) {
        List<CartItem> filteredCartList = cartItems
                .stream()
                .filter(ci -> ci.getFood().getId() != cartItem.getFood().getId())
                .collect(Collectors.toList());

        this.setCartItems(filteredCartList);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
