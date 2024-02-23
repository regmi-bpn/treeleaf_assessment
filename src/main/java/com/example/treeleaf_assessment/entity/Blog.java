package com.example.treeleaf_assessment.entity;

import com.example.treeleaf_assessment.entity.user.BaseEntity;
import com.example.treeleaf_assessment.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Blog extends BaseEntity {

    private String title;

    @Column(length = 500000)
    private String content;

    private String image;

    @ManyToOne
    private User user;

    private Boolean isDeleted = false;
}
