package com.example.treeleaf_assessment.repository;

import com.example.treeleaf_assessment.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    @Query("SELECT blog FROM Blog blog WHERE blog.id=:id")
    Optional<Blog> findById(String id);

    @Query("SELECT blog FROM Blog blog WHERE blog.isDeleted=false")
    List<Blog> getAll();
}
