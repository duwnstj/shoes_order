package com.personal.entity.order;

import com.personal.common.entity.BaseEntity;
import com.personal.entity.store.Store;
import com.personal.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "order")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long orderNo;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private String recepi;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String request;

    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Long totalAmt;

    @Column(nullable = false)
    private Long totalTax;

    @Column(nullable = false)
    private String zip;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;


}
