package com.example.treeleaf_assessment.entity;

import com.example.treeleaf_assessment.entity.user.BaseEntity;
import com.example.treeleaf_assessment.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Comment extends BaseEntity {

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Blog blog;

    private Boolean isDeleted = false;
}
