package com.example.treeleaf_assessment.service;

import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.entity.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {

    ResponseEntity<String> addBlog(String title, String content, MultipartFile image);

    ResponseEntity<String> updateBlog(String title, String content, MultipartFile image, String blogId);

    List<Blog> getAllBlogs();

    Message deleteBlog(String blogId);


}
