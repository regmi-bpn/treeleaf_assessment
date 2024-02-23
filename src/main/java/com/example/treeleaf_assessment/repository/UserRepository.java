package com.example.treeleaf_assessment.repository;

import com.example.treeleaf_assessment.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT user FROM User user WHERE user.email=:email ")
    Optional<User> validateUserByEmail(String email);
}
