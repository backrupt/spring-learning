package com.springboot.security.service.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.security.data.dto.ProductDto;
import com.springboot.security.data.entity.Product;
import com.springboot.security.data.repository.ProductRepository;
import com.springboot.security.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto getProduct(Long number) {
        LOGGER.info("[getProduct] product number : {}", number);
        Product product = productRepository.findById(number).orElseThrow(RuntimeException::new);

        LOGGER.info(
            "[getProduct] found Product :: productId : {}, productName : {}, productPrice : {}, productStock : {}",
            product.getNumber(), product.getName(), product.getPrice(), product.getStock());

        ProductDto productResponseDto = new ProductDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());
        return productResponseDto;
    }

    @Override
    public List<ProductDto> getProductList() {
        LOGGER.info("[getProductList] product list : {}","list");
        List<Product> productEntityList = productRepository.findAll();

        LOGGER.info(
                "[getProductList] found ProductList :: size : {}",
                productEntityList.size());
        List<ProductDto> productResponseDtoList=new ArrayList<>();
        
        for (Product productEntity:productEntityList) {
            ProductDto productResponseDto = new ProductDto();
            productResponseDto.setNumber(productEntity.getNumber());
            productResponseDto.setName(productEntity.getName());
            productResponseDto.setPrice(productEntity.getPrice());
            productResponseDto.setStock(productEntity.getStock());
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;

    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        LOGGER.info("[saveProduct] productName : {}", productDto.getName());
        Product productEntity = new Product();
        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setStock(productDto.getStock());
        //productEntity.setCreatedAt(LocalDateTime.now());
        productEntity.setUpdatedAt(LocalDateTime.now());

        Product savedProductEntity = productRepository.save(productEntity);
        LOGGER.info("[saveProduct] saved ProductId : {}", savedProductEntity.getNumber());

        ProductDto productResponseDto = new ProductDto();
        productResponseDto.setNumber(savedProductEntity.getNumber());
        productResponseDto.setName(savedProductEntity.getName());
        productResponseDto.setPrice(savedProductEntity.getPrice());
        productResponseDto.setStock(savedProductEntity.getStock());

        return productResponseDto;
    }

    @Override
    public ProductDto changeProduct(ProductDto productDto) {
        LOGGER.info("[changeProduct] request productId : {}", productDto.getNumber());
        Product foundProductEntity = productRepository.findById(productDto.getNumber())
            .orElseThrow(RuntimeException::new);
        LOGGER.info("[changeProduct] found Product's name : {}", foundProductEntity.getName());
        foundProductEntity.setName(productDto.getName());
        LOGGER.info("[changeProduct] change Product's name : {}", foundProductEntity.getName());
        Product changedProductEntity = productRepository.save(foundProductEntity);

        ProductDto productResponseDto = new ProductDto();
        productResponseDto.setNumber(changedProductEntity.getNumber());
        productResponseDto.setName(changedProductEntity.getName());
        productResponseDto.setPrice(changedProductEntity.getPrice());
        productResponseDto.setStock(changedProductEntity.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) {
        LOGGER.info("[deleteProduct] delete ProductId : {}", number);
        productRepository.deleteById(number);
    }
}
