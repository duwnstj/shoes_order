package com.personal.entity.user;

import com.personal.common.entity.BaseEntity;
import com.personal.common.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @Builder
    public User(String password, String email, String name, UserRole role) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
