package com.example.mu.homework.api;

import com.example.mu.common.contants.ConstantsResponse;
import com.example.mu.common.vo.ReturnVO;
import com.example.mu.homework.dto.ProductDTO;
import com.example.mu.homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API _ 상품 관련
 */
@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ApiProductController {

    private final ProductService productService;

    /**
     * 구현 1
     *
     * @return ProductDTO.ResLowestProductByCategory
     */
    @GetMapping("/getLowestCategoryList")
    public ProductDTO.ResLowestProductByCategory getLowestCategoryList() {
        return productService.getLowestProductListByCategory();
    }

    /**
     * 구현 2
     *
     * @return ProductDTO.ResLowestProduct
     */
    @GetMapping("/getLowestBrandList")
    public ProductDTO.ResLowestProduct getLowestBrandList() {
        return productService.getLowestProductListByBrand();
    }

    /**
     * 구현 3
     *
     * @Param ProductDTO.ReqCategory
     * @return ProductDTO.ResLowestHighestProduct
     */
    @PostMapping("/getLowestHighest")
    public ProductDTO.ResLowestHighestProduct getLowestHighestProduct(@RequestBody ProductDTO.ReqCategory param) {
        return productService.getLowestHighestProduct(param);
    }

    /**
     * 구현 4 _ 추가 (상품)
     *
     * @Param ProductDTO.ReqAddProduct
     * @return ResponseEntity
     */
    @PostMapping("/add")
    public ResponseEntity<ReturnVO> addProduct(@RequestBody ProductDTO.ReqAddProduct param) {
        return ResponseEntity.ok().body(new ReturnVO(true, productService.addProduct(param)));
    }

    /**
     * 구현 4 _ 수정 (상품)
     *
     * @Param targetId, ProductDTO.ReqAddProduct
     * @return ResponseEntity
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ReturnVO> updateProduct(@PathVariable("id") Long targetId, @RequestBody ProductDTO.ReqUpdateProduct param) {
        return ResponseEntity.ok().body(new ReturnVO(true, productService.updateProduct(targetId, param)));
    }

    /**
     * 구현 4 _ 삭제 (상품)
     *
     * @Param ProductDTO.ReqAddProduct
     * @return ResponseEntity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ReturnVO> deleteProduct(@PathVariable("id") Long targetId) {
        productService.deleteProduct(targetId);
        return ResponseEntity.ok().body(ConstantsResponse.RES_SUCCESS);
    }
}
