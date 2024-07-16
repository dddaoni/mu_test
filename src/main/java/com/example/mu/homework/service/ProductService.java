package com.example.mu.homework.service;

import com.example.mu.homework.dto.BrandDTO;
import com.example.mu.homework.dto.ProductDTO;

/**
 * product Service
 */
public interface ProductService {

    /**
     * 구현 1
     *
     * @return ProductDTO.ResLowestProductByCategory
     */
    ProductDTO.ResLowestProductByCategory getLowestProductListByCategory();

    /**
     * 구현 2
     *
     * @return ProductDTO.ResLowestProduct
     */
    ProductDTO.ResLowestProduct getLowestProductListByBrand();

    /**
     * 구현 3
     *
     * @Param ProductDTO.ReqCategory
     * @return ProductDTO.ResLowestHighestProduct
     */
    ProductDTO.ResLowestHighestProduct getLowestHighestProduct(ProductDTO.ReqCategory param);

    /**
     * 구현 4 _ 추가
     *
     * @Param ProductDTO.ReqAddProduct
     * @return ProductDTO.ResProduct
     */
    ProductDTO.ResProduct addProduct(ProductDTO.ReqAddProduct param);

    /**
     * 구현 4 _ 수정
     *
     * @Param ProductDTO.ReqAddProduct
     * @return ProductDTO.ResProduct
     */
    ProductDTO.ResProduct updateProduct(Long targetId, ProductDTO.ReqUpdateProduct param);

    /**
     * 구현 4 _ 삭제
     *
     * @Param ProductDTO.ReqAddProduct
     */
    void deleteProduct(Long targetId);
}
