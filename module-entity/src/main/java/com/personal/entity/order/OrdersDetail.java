package com.personal.entity.order;

import com.personal.entity.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
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

    @Builder
    public OrdersDetail(
            String productName,
            Long length,
            Long width,
            Long qty,
            boolean customYN,
            Long customPrice,
            Long basePrice,
            Long amt,
            Long tax,
            Orders orders,
            Product product
    ) {
        this.productName = productName;
        this.length = length;
        this.width = width;
        this.qty = qty;
        this.customYN = customYN;
        this.customPrice = customPrice;
        this.basePrice = basePrice;
        this.amt = amt;
        this.tax = tax;
        this.orders = orders;
        this.product = product;
    }
}
