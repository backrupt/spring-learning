package com.springboot.security.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.data.dto.ProductDto;
import com.springboot.security.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/product")
@Tag(name = "제품",description = "제품API")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private  ProductService productService;
   
    @GetMapping("/{productNo}")
    @Operation(summary = "제품상세보기",description = "권한없음")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value="productNo") Long number) {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[getProduct] request Data :: productId : {}", number);
        ProductDto productResponseDto = productService.getProduct(number);

        LOGGER.info(
            "[getProduct] response Data :: productId : {}, productName : {}, productPrice : {}, productStock : {}",
            productResponseDto.getNumber(), productResponseDto.getName(),
            productResponseDto.getPrice(), productResponseDto.getStock());
        LOGGER.info("[getProduct] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
   
    @GetMapping("/list")
    @Operation(summary = "제품리스트",description = "권한없음")
    public ResponseEntity<List<ProductDto>> getProductList() {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[getProductList] request Data : {}", "X");
        List<ProductDto> productResponseDtoList = productService.getProductList();

        LOGGER.info(
            "[getProductList] response Data :: size : {}",productResponseDtoList.size());
        LOGGER.info("[getProductList] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtoList);
    }
    @Operation(summary = "제품생성",description = "admin 권한사용자")
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        long currentTime = System.currentTimeMillis();
        ProductDto productResponseDto = productService.saveProduct(productDto);

        LOGGER.info("[createProduct] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    @Operation(summary = "제품수정",description = "admin 권한사용자")
    @SecurityRequirement(name = "BearerAuth")
    @PutMapping()
    public ResponseEntity<ProductDto> changeProductName(
        @RequestBody ProductDto changeProductDto) throws Exception {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[changeProductName] request Data :: productNumber : {}, productName : {}",
            changeProductDto.getNumber(), changeProductDto.getName());

        ProductDto productResponseDto = 
        		productService.changeProduct(changeProductDto);

        LOGGER.info(
            "[changeProductName] response Data :: productNumber : {}, productName : {}, productPrice : {}, productStock : {}",
            productResponseDto.getNumber(), productResponseDto.getName(),
            productResponseDto.getPrice(), productResponseDto.getStock());
        LOGGER.info("[changeProductName] response Time : {}ms",
            System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    @Operation(summary = "제품삭제",description = "admin 권한사용자")
    @SecurityRequirement(name = "BearerAuth")
    @DeleteMapping("/{productNo}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "productNo") Long number) throws Exception {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[deleteProduct] request Data :: productNumber : {}", number);
        productService.deleteProduct(number);
        LOGGER.info("[deleteProduct] response Time : {}ms",
            System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
