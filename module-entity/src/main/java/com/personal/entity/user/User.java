package com.personal.entity.user;

import com.personal.common.entity.BaseEntity;
import com.personal.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
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
