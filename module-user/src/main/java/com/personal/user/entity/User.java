package com.personal.user.entity;

import com.personal.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    private String name;
    private String email;
}
