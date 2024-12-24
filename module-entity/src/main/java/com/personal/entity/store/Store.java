package com.personal.entity.store;

import com.personal.common.entity.BaseEntity;
import com.personal.entity.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String zip;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressDetail;

    private String description;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Store(String name, String tel, String zip, String address, String addressDetail, String description, User user) {
        this.name = name;
        this.tel = tel;
        this.zip = zip;
        this.address = address;
        this.addressDetail = addressDetail;
        this.description = description;
        this.user = user;
    }
}
