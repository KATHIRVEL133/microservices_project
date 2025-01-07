package com.kathirvel.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kathirvel.product_service.dto.ProductRequest;
import com.kathirvel.product_service.dto.ProductResponse;
import com.kathirvel.product_service.model.Product;
import com.kathirvel.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  public void createProduct(ProductRequest productRequest)
  {
   Product product = Product.builder()
                     .name(productRequest.getName())
                     .description(productRequest.getDescription())
                     .price(productRequest.getPrice())
                     .build();
   productRepository.save(product);
   log.info("product {} saved successfully",product.getId());
  }

public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream().map(this::productToResponse).toList();
}  
public ProductResponse productToResponse(Product product)
{
 return ProductResponse.builder()
       .Id(product.getId())
       .name(product.getName())
       .description(product.getDescription())
       .price(product.getPrice())
       .build();
}
}
