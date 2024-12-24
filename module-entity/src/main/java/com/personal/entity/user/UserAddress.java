package com.personal.entity.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user_address")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean repYN = false;

    @Column(nullable = false)
    private String zip;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @Builder
    public UserAddress(boolean repYN, String zip, String address, String addressDetail, User user) {
        this.repYN = repYN;
        this.zip = zip;
        this.address = address;
        this.addressDetail = addressDetail;
        this.user = user;
    }

    public void updateAddress(String zip , String address , String addressDetail) {
        this.zip = zip;
        this.address = address;
        this.addressDetail = addressDetail;
    }

    public void updateRepYN(boolean repYN) {
        this.repYN = repYN;
    }
}
