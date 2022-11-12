package com.learning.shophelper.api.model;

import java.util.List;

public class ResultDto {

    private List<ProductDto> cheapestProducts;
    private UserDto closestSupplier;

    private float distance;

    public ResultDto(List<ProductDto> cheapestProducts, UserDto closestSupplier, float distance) {
        this.cheapestProducts = cheapestProducts;
        this.closestSupplier = closestSupplier;
        this.distance = distance;
    }

    public List<ProductDto> getCheapestProducts() {
        return cheapestProducts;
    }

    public void setCheapestProducts(List<ProductDto> cheapestProducts) {
        this.cheapestProducts = cheapestProducts;
    }

    public UserDto getClosestSupplier() {
        return closestSupplier;
    }

    public void setClosestSupplier(UserDto closestSupplier) {
        this.closestSupplier = closestSupplier;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
