package com.example.treeleaf_assessment.repository;

import com.example.treeleaf_assessment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    @Query("SELECT comment FROM Comment comment WHERE comment.id=:id")
    Optional<Comment> findCommentById(String id);

    @Query("SELECT comment FROM Comment comment WHERE comment.isDeleted=false")
    List<Comment> getAll();
}
