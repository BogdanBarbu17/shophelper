package com.learning.shophelper.api.model;

import java.util.List;

public class OrderDto {

    private List<ProductDto> products;
    private float latitude;
    private float longitude;

   public List<ProductDto> getProducts() {
      return products;
   }

   public void setProducts(List<ProductDto> products) {
      this.products = products;
   }

   public float getLatitude() {
      return latitude;
   }

   public void setLatitude(float latitude) {
      this.latitude = latitude;
   }

   public float getLongitude() {
      return longitude;
   }

   public void setLongitude(float longitude) {
      this.longitude = longitude;
   }
}
