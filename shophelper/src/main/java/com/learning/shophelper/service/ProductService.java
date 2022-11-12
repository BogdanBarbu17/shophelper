package com.learning.shophelper.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.shophelper.api.model.ProductDto;
import com.learning.shophelper.data.access.model.ProductEntity;
import com.learning.shophelper.data.access.model.UserEntity;
import com.learning.shophelper.data.access.repository.ProductRepository;
import com.learning.shophelper.data.access.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;



    public ProductDto getProductById(Long id){
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        return toDto(productEntity);
    }


    public ProductDto createProduct(ProductDto product){
        ProductEntity newProduct = toEntity(product);
        UserEntity userEntity = userRepository.findById(product.getUserId()).orElseThrow();
        newProduct.setUserEntity(userEntity);
        newProduct = productRepository.save(newProduct);
        return toDto(newProduct);
    }


    public ProductDto updateProduct(ProductDto product,Long id){
        ProductEntity newProduct = toEntity(product);
        UserEntity userEntity = userRepository.findById(product.getUserId()).orElseThrow();
        newProduct.setUserEntity(userEntity);
        ProductEntity oldProduct = productRepository.findById(id).orElseThrow();
        newProduct.setId(oldProduct.getId());
        newProduct = productRepository.save(newProduct);
        return toDto(newProduct);
    }

    public List<ProductDto> getAllProducts(){
        List<ProductDto> productDtos = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();
        for (ProductEntity productEntity : productEntities){
            productDtos.add(toDto(productEntity));
        }
        return productDtos;
    }

    public List<ProductDto> getAllByName(String name) {
        List<ProductDto> productDtos = new ArrayList<>();
        List<ProductEntity> productEntities =productRepository.findAllByName(name);
        for (ProductEntity productEntity : productEntities){
            productDtos.add(toDto(productEntity));
        }
        return productDtos;
    }

    public void deleteProduct (Long id){
        productRepository.deleteById(id);
    }

    private ProductEntity toEntity(ProductDto productDto) {
        return objectMapper.convertValue(productDto, ProductEntity.class);
    }

    private ProductDto toDto(ProductEntity productEntity) {
        ProductDto productDto = objectMapper.convertValue(productEntity, ProductDto.class);
        productDto.setUserId(productEntity.getUserEntity().getId());
        productDto.setUserName(productEntity.getUserEntity().getName());
        return productDto;
    }

}
