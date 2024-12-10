package com.personal.common.entity;

import com.personal.common.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class AuthUser {
    private Long userId;
    private String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long userId, String email, UserRole role) {
        this.userId = userId;
        this.email = email;
        this.authorities = List.of(new SimpleGrantedAuthority(role.name()));
    }
}

