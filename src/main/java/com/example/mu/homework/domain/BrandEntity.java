package com.example.mu.homework.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 브랜드
 */
@Entity
@Getter
@Table(name = "brand")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(nullable = false, length = 50)
    @Setter
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductEntity> products = new ArrayList<>();

    /**
     * 브랜드 추가
     */
    public static BrandEntity createBrand(String name) {
        BrandEntity brand = new BrandEntity();
        brand.name = name;
        return brand;
    }
}
