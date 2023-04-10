package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.ProductDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.models.Product;
import com.snoozingturtles.auctioz.repositories.ProductRepo;
import com.snoozingturtles.auctioz.services.ProductService;
import com.snoozingturtles.auctioz.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


//TODO: check if user exists
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    @Override
    public ProductDto createProduct(String sellerId, ProductDto productDto) {
        productDto.setSellerId(sellerId);
        Product product = productRepo.save(modelMapper.map(productDto, Product.class));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void updateProduct(String productId, String sellerId, ProductDto productDto) {
        ProductDto productById = getProductById(productId, sellerId);
        productById.setName(productDto.getName());
        productById.setDescription(productDto.getDescription());
        productById.setCategoryId(productDto.getCategoryId());
        productById.setSoldOut(productDto.isSoldOut());
        productById.setImageName(productDto.getImageName());
        productRepo.save(modelMapper.map(productById, Product.class));
    }

    @Override
    public ProductDto getProductById(String productId, String sellerId) {
        Product product = productRepo.findByIdAndSellerId(productId, sellerId)
                .orElseThrow(() -> new EntityNotFoundException("No such product found!"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(String productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("No such product found!"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProductsBySellerId(String sellerId) {
        return productRepo.findAllBySellerId(sellerId)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public List<ProductDto> getAllProducts() {
         return productRepo
                .findAll()
                 .stream()
                 .map(product -> modelMapper.map(product, ProductDto.class))
                 .toList();
    }

    @Override
    public List<ProductDto> getAllProductsByCategoryId(String categoryId) {
        return productRepo
                .findAllByCategoryId(categoryId)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public void deleteProduct(String productId, String sellerId) {
        Product product = modelMapper.map(getProductById(productId, sellerId), Product.class);
        productRepo.delete(product);
    }
}
