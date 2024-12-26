package com.personal.entity.stock;


import com.personal.common.entity.BaseEntity;
import com.personal.entity.product.Product;
import com.personal.entity.product.ProductType;
import com.personal.entity.store.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "stock")
public class Stock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private Long qty;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false, unique = true)
    private String lot;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public void increase(Long baseQty) {

        this.qty += baseQty;
    }

    public void decrease(Long decreaseQty) {

        this.qty -= decreaseQty;
    }
}
