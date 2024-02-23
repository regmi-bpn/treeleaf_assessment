package com.example.treeleaf_assessment.controller;

import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.entity.Blog;
import com.example.treeleaf_assessment.service.BlogService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/createBlog")
    public ResponseEntity<String> addBlog(@RequestParam("title") String title,
                                          @RequestParam("content") String content,
                                          @RequestParam(value = "imageFile") MultipartFile imageFile) {

        return blogService.addBlog(title, content, imageFile);
    }

    @PostMapping("/updateBlog")
    public ResponseEntity<String> updateBlog(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "content", required = false) String content,
                                             @RequestParam(value = "imageFile", required = false) MultipartFile image, @RequestParam(value = "blogId") String blogId) {
        return blogService.updateBlog(title, content, image, blogId);
    }

    @GetMapping("/getAll")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @DeleteMapping("/deleteBlog")
    public Message deleteBlog(@Valid String blogId) {
        return blogService.deleteBlog(blogId);
    }
}
