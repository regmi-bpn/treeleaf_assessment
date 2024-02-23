package com.example.treeleaf_assessment.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {


    private String name;

    private String email;

    private String password;

    @ManyToOne
    private Group group;

    private boolean isActive;

}
