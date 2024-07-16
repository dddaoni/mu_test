package com.example.mu.homework.dto;

import com.example.mu.common.custom.CustomNumberSerializer;
import com.example.mu.homework.domain.ProductEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

/**
 * product DTO
 */
public class ProductDTO {

    /**
     * response _ db
     */
    public interface ResProductDto {
        String getCategory();
        String getBrand();
        @JsonSerialize(using = CustomNumberSerializer.class)
        int getPrice();
    }

    /**
     * response _ 공통 product json dto
     */
    @Getter
    @Builder
    @AllArgsConstructor
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProductJsonDto {
        @JsonProperty("카테고리")
        private String category;
        @JsonProperty("브랜드")
        private String brand;
        @JsonProperty("가격")
        @JsonSerialize(using = CustomNumberSerializer.class)
        private int price;
    }

    /**
     * response _ 공통 product json dto
     */
    @Data
    @ToString
    public static class testResDto {
        @JsonProperty("카테고리")
        private String category;
        @JsonProperty("가격")
        @JsonSerialize(using = CustomNumberSerializer.class)
        private int price;
    }

    /**
     * response _ 구현 1
     */
    @Data
    @AllArgsConstructor
    public static class ResLowestProductByCategory {
        @JsonSerialize(using = CustomNumberSerializer.class)
        private int totalPrice;
        private List<ResProductDto> brandProductList;
    }


    /**
     * response _ 구현 2
     */
    @Getter
    @AllArgsConstructor
    public static class ResLowestProduct {
        @JsonProperty("최저가")
        private ResLowestProductByBrand resLowestProductByBrand;
    }

    /**
     * response _ 구현 2
     */
    @Getter
    @AllArgsConstructor
    public static class ResLowestProductByBrand {
        @JsonProperty("브랜드")
        private String brand;
        @JsonProperty("카테고리")
        private List<ProductJsonDto> categoryProductList;
        @JsonProperty("총액")
        private int totalPrice;
    }

    /**
     * request _ 구현 3
     */
    @Data
    public static class ReqCategory {
        private String category;
    }

    /**
     * response _ 구현 3
     */
    @Data
    @Getter
    @AllArgsConstructor
    public static class ResLowestHighestProduct {
        @JsonProperty("카테고리")
        private String category;
        @JsonProperty("최저가")
        private ProductJsonDto lowestProduct;
        @JsonProperty("최고가")
        private ProductJsonDto highestProduct;
    }

    /**
     * request add
     */
    @Data
    public static class ReqAddProduct {
        private Long brandId;
        private String category;
        private String name;
        private int price;
        private int stock;
    }

    /**
     * request update
     */
    @Data
    public static class ReqUpdateProduct {
        private String category;
        private String name;
        private int price;
        private int stock;
    }

    /**
     * response
     */
    @Data
    @NoArgsConstructor
    public static class ResProduct {
        private Long id;
        private String category;
        private Long brandId;
        private String name;
        private int price;
        private int stock;

        // convert to dto
        public ResProduct(ProductEntity productEntity) {
            this.id = productEntity.getId();
            this.category = productEntity.getCategory().getName();
            this.brandId = productEntity.getBrand().getId();
            this.name = productEntity.getName();
            this.price = productEntity.getPrice();
            this.stock = productEntity.getStock();
        }
    }

}
