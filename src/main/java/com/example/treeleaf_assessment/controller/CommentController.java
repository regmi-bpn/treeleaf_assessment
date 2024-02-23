package com.example.treeleaf_assessment.controller;

import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.entity.Comment;
import com.example.treeleaf_assessment.service.CommentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/add")
    public Message addComment(@Valid String comment, @Valid String blogId) {
        log.info("Add comment :: {}", comment);
        return commentService.addComment(comment, blogId);
    }

    @PutMapping("/update")
    public Message updateComment(@Valid String comment, @Valid String id) {
        log.info("Update comment :: {}", comment);
        return commentService.updateComment(comment, id);
    }

    @GetMapping("/get-all")
    public List<Comment> getAll() {
        log.info("get all comments");
        return commentService.getAll();
    }

    @DeleteMapping("/delete")
    public Message deleteComment(@Valid String id) {
        log.info("Delete comment :: {}", id);
        return commentService.deleteComment(id);
    }
}
