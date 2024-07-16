package com.example.mu.homework.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.Setter;

/**
 * 상품
 */
@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private BrandEntity brand;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false, length = 20)
    private int price;

    @Setter
    @Column(nullable = false, length = 6)
    private int stock;

    /**
     * 상품 추가
     */
    public static ProductEntity createProduct(BrandEntity brand, CategoryEntity category, String name, int price, int stock) {
        ProductEntity product = new ProductEntity();
        product.brand = brand;
        product.category = category;
        product.name = name;
        product.price = price;
        product.stock = stock;
        return product;
    }
}
