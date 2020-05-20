package com.devxschool.food_delivery.models;

public class Food {

    private Long id;
    private Float price;
    private String name;
    private String description;
    private FoodType foodType;

    public Food(){

    }
    public Food(Long id, String name, Float price, String description, FoodType foodType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.setDescription(description);
        this.foodType = foodType;
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

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
