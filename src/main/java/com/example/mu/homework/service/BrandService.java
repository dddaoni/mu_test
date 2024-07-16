package com.example.mu.homework.service;

import com.example.mu.homework.dto.BrandDTO;
import com.example.mu.homework.dto.ProductDTO;

/**
 * brand service
 */
public interface BrandService {

    /**
     * 구현 4 _ 추가
     *
     * @Param BrandDTO.ReqAddBrand
     * @return BrandDTO.ResBrand
     */
    BrandDTO.ResBrand addBrand(BrandDTO.ReqAddBrand param);

    /**
     * 구현 4 _ 수정
     *
     * @Param targetId, BrandDTO.ReqUpdateBrand
     * @return BrandDTO.ResBrand
     */
    BrandDTO.ResBrand updateBrand(Long targetId, BrandDTO.ReqUpdateBrand param);

    /**
     * 구현 4 _ 삭제
     *
     * @Param targetId
     */
    void deleteBrand(Long targetId);
}
