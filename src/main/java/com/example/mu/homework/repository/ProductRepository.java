package com.example.mu.homework.repository;

import com.example.mu.homework.domain.ProductEntity;
import com.example.mu.homework.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * product repository
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value =
            "SELECT c.name AS category, b.name AS brand, p.price " +
            "FROM ( " +
            "    SELECT " +
            "        product_id, " +
            "        category_id, " +
            "        brand_id, " +
            "        name, " +
            "        price, " +
            "        ROW_NUMBER() OVER (PARTITION BY category_id ORDER BY price ASC, product_id DESC) AS row_num " +
            "    FROM product " +
            ") p " +
            "JOIN brand b ON p.brand_id = b.brand_id " +
            "JOIN category c ON p.category_id = c.category_id " +
            "WHERE p.row_num = 1 "
    , nativeQuery = true)
    List<ProductDTO.ResProductDto> findLowestListGroupByCategory();

    @Query (value =
            "SELECT c.name AS category, p.price AS price " +
            "FROM product p " +
            "LEFT JOIN category c ON p.category_id = c.category_id " +
            "WHERE p.brand_id = :brandId "
    , nativeQuery = true)
    List<ProductDTO.ResProductDto> findDtoListByBrandId(@Param("brandId") Long brandId);

    @Query (value =
            "SELECT b.name AS brand , p.price " +
            "FROM product p " +
            "JOIN brand b ON p.brand_id = b.brand_id " +
            "WHERE category_id = :categoryId " +
            "ORDER BY price ASC " +
            "limit 1 "
    , nativeQuery = true)
    ProductDTO.ResProductDto findLowestByCategory(@Param("categoryId") int categoryId);

    @Query (value =
            "SELECT b.name AS brand, p.price " +
            "FROM product p " +
            "JOIN brand b ON p.brand_id = b.brand_id " +
            "WHERE category_id = :categoryId " +
            "ORDER BY price DESC " +
            "limit 1 "
    , nativeQuery = true)
    ProductDTO.ResProductDto findHighestByCategory(@Param("categoryId") int categoryId);
}
