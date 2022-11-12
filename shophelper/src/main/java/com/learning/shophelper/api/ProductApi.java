package com.learning.shophelper.api;

import com.learning.shophelper.api.model.ProductDto;
import com.learning.shophelper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct(@RequestParam boolean distinct){
        List<ProductDto> productDtos = productService.getAllProducts();
        if (distinct) {
            Map<String, ProductDto> distinctProducts = new HashMap<>();
            productDtos.forEach(productDto -> distinctProducts.put(productDto.getName(), productDto));
            return ResponseEntity.ok(distinctProducts.values().stream().toList());
        } else {
            return ResponseEntity.ok(productDtos);
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product){
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product, @PathVariable Long id){
        return ResponseEntity.ok(productService.updateProduct(product,id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }






}
