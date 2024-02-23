package com.example.treeleaf_assessment.service.impl;

import com.example.treeleaf_assessment.commons.AuthUtil;
import com.example.treeleaf_assessment.commons.exception.BadRequestException;
import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.entity.Blog;
import com.example.treeleaf_assessment.entity.Comment;
import com.example.treeleaf_assessment.repository.BlogRepository;
import com.example.treeleaf_assessment.repository.CommentRepository;
import com.example.treeleaf_assessment.service.CommentService;
import com.example.treeleaf_assessment.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserValidator userValidator;
    private final BlogRepository blogRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserValidator userValidator, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.userValidator = userValidator;
        this.blogRepository = blogRepository;
    }

    @Override
    public Message addComment(String comment, String blogId) {
        Comment comment1 = new Comment();
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isEmpty()) throw new BadRequestException("BL001", "Blog not found!!");
        comment1.setBlog(blogOptional.get());
        comment1.setComment(comment);
        comment1.setUser(userValidator.getUserById(AuthUtil.getLoggedInUserId()));
        commentRepository.save(comment1);
        return Message.builder()
                .message("Comment added to blog successfully!!")
                .build();
    }

    @Override
    public Message updateComment(String comment, String id) {
        Optional<Comment> commentById = commentRepository.findCommentById(id);
        if (commentById.isEmpty()) throw new BadRequestException("CMN001", "Comment not found!!");
        Comment comment1 = commentById.get();
        if (comment != null) {
            comment1.setComment(comment);
        }
        commentRepository.save(comment1);
        return Message.builder()
                .message("Comment updated successfully!!")
                .build();
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    @Override
    public Message deleteComment(String id) {
        Optional<Comment> commentById = commentRepository.findCommentById(id);
        if (commentById.isEmpty()) throw new BadRequestException("CMN001", "Comment not found!!");
        Comment comment = commentById.get();
        comment.setIsDeleted(true);
        commentRepository.save(comment);
        return Message.builder()
                .message("Comment deleted successfully!!")
                .build();
    }
}
