package com.personal.entity.ship;

import com.personal.common.entity.BaseEntity;
import com.personal.entity.order.Orders;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "shipping_status")
public class ShippingStatus extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ShipStatus shippingStatus;

    @Column(nullable = false)
    private LocalDate shipStartDate;

    @Column(nullable = false)
    private LocalDate shipCompleteDate;

    @Column(nullable = false)
    private String trackingNumber;

    @Column(nullable = false)
    private String carrier;

    @Column(nullable = false)
    private String trackingUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;
}
