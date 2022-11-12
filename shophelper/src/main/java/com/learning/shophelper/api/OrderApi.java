package com.learning.shophelper.api;

import com.learning.shophelper.api.model.OrderDto;
import com.learning.shophelper.api.model.ProductDto;
import com.learning.shophelper.api.model.ResultDto;
import com.learning.shophelper.api.model.UserDto;
import com.learning.shophelper.service.DistanceCalculator;
import com.learning.shophelper.service.ProductService;
import com.learning.shophelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResultDto> createOrder(@RequestBody OrderDto order) {
        List<String> productNames = order.getProducts().stream().map(ProductDto::getName).toList();
        Map<String, ProductDto> productsByName = new HashMap<>();
        float min = Float.MAX_VALUE;
        UserDto closestSupplier = null;

        for (String product : productNames) {
            List<ProductDto> productDtos = productService.getAllByName(product);

            Comparator<ProductDto> comparatorByPrice = (p1, p2) -> (int) (p1.getPrice() - p2.getPrice());
            productDtos.sort(comparatorByPrice);

            order.getProducts().forEach(name -> productsByName.put(product, productDtos.get(0)));
        }

        for (ProductDto product : productsByName.values()) {
            UserDto userDto = userService.getUserById(product.getUserId());
            float distance = DistanceCalculator.distFrom(order.getLatitude(), order.getLongitude(), userDto.getLatitude(), userDto.getLongitude());

            if (distance < min){
                min = distance;
                closestSupplier = userDto;

            }
        }

        ResultDto resultDto = new ResultDto(new ArrayList<>(productsByName.values()), closestSupplier, min);
        return ResponseEntity.ok(resultDto);
    }


}
