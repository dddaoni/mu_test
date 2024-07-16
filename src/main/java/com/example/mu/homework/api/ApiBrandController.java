package com.example.mu.homework.api;

import com.example.mu.common.contants.ConstantsResponse;
import com.example.mu.common.vo.ReturnVO;
import com.example.mu.homework.dto.BrandDTO;
import com.example.mu.homework.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API _ 브랜드
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/brand")
@RequiredArgsConstructor
public class ApiBrandController {

    private final BrandService brandService;

    /**
     * 구현 4 _ 추가 (브랜드)
     *
     * @Param BrandDTO.ReqAddBrand
     * @return ResponseEntity
     */
    @PostMapping("/add")
    public ResponseEntity<ReturnVO> addBrand(@RequestBody BrandDTO.ReqAddBrand param) {
        return ResponseEntity.ok().body(new ReturnVO(true, brandService.addBrand(param)));
    }

    /**
     * 구현 4 _ 수정 (브랜드)
     *
     * @Param targetId, BrandDTO.ReqUpdateBrand
     * @return ResponseEntity
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ReturnVO> updateBrand(@PathVariable("id") Long targetId, @RequestBody BrandDTO.ReqUpdateBrand param) {
        return ResponseEntity.ok().body(new ReturnVO(true, brandService.updateBrand(targetId, param)));
    }

    /**
     * 구현 4 _ 삭제 (브랜드)
     *
     * @Param targetId
     * @return ResponseEntity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ReturnVO> deleteBrand(@PathVariable("id") Long targetId) {
        brandService.deleteBrand(targetId);
        return ResponseEntity.ok().body(ConstantsResponse.RES_SUCCESS);
    }
}
