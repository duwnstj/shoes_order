package com.personal.entity.order;

import com.personal.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "orders_detail")
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long length;

    @Column(nullable = false)
    private Long width;

    @Column(nullable = false)
    private Long qty;

    @Column(nullable = false)
    private boolean customYN;

    @Column(nullable = false)
    private Long customPrice;

    @Column(nullable = false)
    private Long basePrice;

    @Column(nullable = false)
    private Long amt;

    @Column(nullable = false)
    private Long tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
