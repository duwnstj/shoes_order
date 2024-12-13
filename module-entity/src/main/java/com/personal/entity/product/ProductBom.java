package com.personal.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "product_bom")
public class ProductBom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_product_id" , nullable = false)
    private Product baseProduct;

    @Column(nullable = false)
    private Long baseQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_product_id" , nullable = false)
    private Product materialProduct;

    @Column(nullable = false)
    private Long materialQty;
}
