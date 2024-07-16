package com.example.mu.homework.service.impl;

import com.example.mu.common.handler.exception.NotFoundException;
import com.example.mu.homework.domain.BrandEntity;
import com.example.mu.homework.dto.BrandDTO;
import com.example.mu.homework.repository.BrandRepository;
import com.example.mu.homework.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * brand service impl
 */
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    /**
     * 구현 4 _ 추가
     *
     * @Param BrandDTO.ReqAddBrand
     * @return BrandDTO.ResBrand
     */
    @Override
    @Transactional
    public BrandDTO.ResBrand addBrand(BrandDTO.ReqAddBrand param) {
        BrandEntity brand = BrandEntity.createBrand(param.getName());
        brandRepository.save(brand);
        return new BrandDTO.ResBrand(brand);
    }

    /**
     * 구현 4 _ 수정
     *
     * @Param targetId, BrandDTO.ReqUpdateBrand
     * @return BrandDTO.ResBrand
     */
    @Override
    @Transactional
    public BrandDTO.ResBrand updateBrand(Long targetId, BrandDTO.ReqUpdateBrand param) {
        BrandEntity brand = brandRepository.findById(targetId).orElseThrow(() -> new NotFoundException("등록되지 않은 브랜드입니다."));
        brand.setName(param.getName());
        brandRepository.save(brand);
        return new BrandDTO.ResBrand(brand);
    }

    /**
     * 구현 4 _ 삭제
     *
     * @Param targetId
     */
    @Override
    @Transactional
    public void deleteBrand(Long targetId) {
        BrandEntity brand = brandRepository.findById(targetId).orElseThrow(() -> new NotFoundException("등록되지 않은 브랜드입니다."));
        brandRepository.delete(brand);
    }
}
