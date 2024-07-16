package com.example.mu.homework.repository;

import com.example.mu.homework.domain.BrandEntity;
import com.example.mu.homework.domain.ProductEntity;
import com.example.mu.homework.dto.BrandDTO;
import com.example.mu.homework.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * brand repository
 */
@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    @Query (value =
            "SELECT p.brand_id AS brandId, b.name AS brandName, SUM(p.price) AS totalPrice " +
            "FROM product p " +
            "LEFT JOIN brand b ON p.brand_id = b.brand_id " +
            "GROUP BY p.brand_id " +
            "ORDER BY SUM(p.price) " +
            "LIMIT 1 "
    , nativeQuery = true)
    BrandDTO.ResLowestBrand findLowestSumBrand();
}
