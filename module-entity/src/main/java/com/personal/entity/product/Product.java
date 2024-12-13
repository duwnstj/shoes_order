package com.personal.entity.product;

import com.personal.entity.store.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    private String material;

    @Column(nullable = false)
    private Long spacing;

    @Column(nullable = false)
    private Long basePrice;

    @Column(nullable = false)
    private Long customPrice;

    @Column(nullable = false)
    private boolean isSold;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id" , nullable = false)
    private Store store;
}
