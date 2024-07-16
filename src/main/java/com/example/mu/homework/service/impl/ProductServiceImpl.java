package com.example.mu.homework.service.impl;

import com.example.mu.common.handler.exception.NotFoundException;
import com.example.mu.homework.domain.BrandEntity;
import com.example.mu.homework.domain.CategoryEntity;
import com.example.mu.homework.domain.ProductEntity;
import com.example.mu.homework.dto.BrandDTO;
import com.example.mu.homework.dto.ProductDTO;
import com.example.mu.homework.repository.BrandRepository;
import com.example.mu.homework.repository.CategoryRepository;
import com.example.mu.homework.repository.ProductRepository;
import com.example.mu.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * product Service impl
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    /**
     * 구현 1
     *
     * @return ProductDTO.ResLowestProductByCategory
     */
    @Override
    @Transactional(readOnly = true)
    public ProductDTO.ResLowestProductByCategory getLowestProductListByCategory() {
        List<ProductDTO.ResProductDto> resBrandProductList = productRepository.findLowestListGroupByCategory();
        int totalPrice = resBrandProductList.stream().mapToInt(ProductDTO.ResProductDto::getPrice).sum();
        return new ProductDTO.ResLowestProductByCategory(totalPrice, resBrandProductList);
    }

    /**
     * 구현 2
     *
     * @return ProductDTO.ResLowestProduct
     */
    @Override
    @Transactional(readOnly = true)
    public ProductDTO.ResLowestProduct getLowestProductListByBrand() {
        BrandDTO.ResLowestBrand resLowestBrand = brandRepository.findLowestSumBrand();
        List<ProductDTO.ResProductDto> dbResultList = productRepository.findDtoListByBrandId(resLowestBrand.getBrandId());
        List<ProductDTO.ProductJsonDto> resultList = dbResultList.stream()
                .map(result -> ProductDTO.ProductJsonDto.builder().category(result.getCategory()).price(result.getPrice()).build()).collect(Collectors.toList());

        return new ProductDTO.ResLowestProduct(new ProductDTO.ResLowestProductByBrand(resLowestBrand.getBrandName(), resultList, resLowestBrand.getTotalPrice()));
    }

    /**
     * 구현 3
     *
     * @Param ProductDTO.ReqCategory
     * @return ProductDTO.ResLowestHighestProduct
     */
    @Override
    @Transactional(readOnly = true)
    public ProductDTO.ResLowestHighestProduct getLowestHighestProduct(ProductDTO.ReqCategory param) {
        CategoryEntity categoryEntity = categoryRepository.findByName(param.getCategory()).orElseThrow(() -> new NotFoundException("등록되지 않은 카테고리입니다."));

        ProductDTO.ResProductDto lowest = productRepository.findLowestByCategory(categoryEntity.getId());
        ProductDTO.ResProductDto highest = productRepository.findHighestByCategory(categoryEntity.getId());

        return new ProductDTO.ResLowestHighestProduct(categoryEntity.getName(),
                ProductDTO.ProductJsonDto.builder().brand(lowest.getBrand()).price(lowest.getPrice()).build(),
                ProductDTO.ProductJsonDto.builder().brand(highest.getBrand()).price(highest.getPrice()).build() );
    }

    /**
     * 구현 4 _ 추가
     *
     * @Param ProductDTO.ReqAddProduct
     * @return ProductDTO.ResProduct
     */
    @Override
    @Transactional
    public ProductDTO.ResProduct addProduct(ProductDTO.ReqAddProduct param) {
        BrandEntity brand = brandRepository.findById(param.getBrandId()).orElseThrow(() -> new NotFoundException("등록되지 않은 브랜드입니다."));
        CategoryEntity category = categoryRepository.findByName(param.getCategory()).orElseThrow(() -> new NotFoundException("등록되지 않은 카테고리입니다."));
        ProductEntity product = ProductEntity.createProduct(brand, category, param.getName(), param.getPrice(), param.getStock());

        productRepository.save(product);
        return new ProductDTO.ResProduct(product);
    }

    /**
     * 구현 4 _ 수정
     *
     * @Param ProductDTO.ReqAddProduct
     * @return ProductDTO.ResProduct
     */
    @Override
    @Transactional
    public ProductDTO.ResProduct updateProduct(Long targetId, ProductDTO.ReqUpdateProduct param) {
        ProductEntity product = productRepository.findById(targetId).orElseThrow(() -> new NotFoundException("등록되지 않은 상품입니다."));
        CategoryEntity category = categoryRepository.findByName(param.getCategory()).orElseThrow(() -> new NotFoundException("등록되지 않은 카테고리입니다."));
        product.setCategory(category);
        product.setName(param.getName());
        product.setPrice(param.getPrice());
        product.setStock(param.getStock());

        productRepository.save(product);
        return new ProductDTO.ResProduct(product);
    }

    /**
     * 구현 4 _ 삭제
     *
     * @Param ProductDTO.ReqAddProduct
     */
    @Override
    @Transactional
    public void deleteProduct(Long targetId) {
        ProductEntity product = productRepository.findById(targetId).orElseThrow(() -> new NotFoundException("등록되지 않은 상품입니다."));
        productRepository.delete(product);
    }
}
