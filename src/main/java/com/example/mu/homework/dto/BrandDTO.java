package com.example.mu.homework.dto;

import com.example.mu.common.custom.CustomNumberSerializer;
import com.example.mu.homework.domain.BrandEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 브랜드 DTO
 */
public class BrandDTO {

    /**
     * response _ 구현 2
     */
    public interface ResLowestBrand {
        Long getBrandId();
        String getBrandName();
        @JsonSerialize(using = CustomNumberSerializer.class)
        int getTotalPrice();
    }

    /**
     * request add
     */
    @Data
    public static class ReqAddBrand {
        private String name;
    }

    /**
     * request update
     */
    @Data
    public static class ReqUpdateBrand {
        private String name;
    }

    /**
     * response
     */
    @Data
    public static class ResBrand {
        private Long id;
        private String name;

        // convert to dto
        public ResBrand(BrandEntity brandEntity) {
            this.id = brandEntity.getId();
            this.name = brandEntity.getName();
        }
    }

}
