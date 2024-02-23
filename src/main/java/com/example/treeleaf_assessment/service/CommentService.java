package com.example.treeleaf_assessment.service;

import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.entity.Comment;

import java.util.List;

public interface CommentService {

    Message addComment(String comment, String blogId);

    Message updateComment(String comment, String id);

    List<Comment> getAll();

    Message deleteComment(String id);
}
