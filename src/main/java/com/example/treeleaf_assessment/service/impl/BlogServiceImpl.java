package com.example.treeleaf_assessment.service.impl;

import com.example.treeleaf_assessment.commons.AuthUtil;
import com.example.treeleaf_assessment.commons.exception.BadRequestException;
import com.example.treeleaf_assessment.dto.Message;
import com.example.treeleaf_assessment.entity.Blog;
import com.example.treeleaf_assessment.repository.BlogRepository;
import com.example.treeleaf_assessment.service.BlogService;
import com.example.treeleaf_assessment.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserValidator userValidator;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, UserValidator userValidator) {
        this.blogRepository = blogRepository;
        this.userValidator = userValidator;
    }

    @Override
    public ResponseEntity<String> addBlog(String title, String content, MultipartFile image) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            blog.setImage(imagePath);
        }
        blog.setUser(userValidator.getUserById(AuthUtil.getLoggedInUserId()));
        blogRepository.save(blog);
        return new ResponseEntity<>("Blog post created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateBlog(String title, String content, MultipartFile image, String blogId) {
        Optional<Blog> blogs = blogRepository.findById(blogId);
        if (blogs.isEmpty()) throw new BadRequestException("BLG001", "Blog not found!!");
        Blog blog = blogs.get();
        if (title != null) blog.setTitle(title);
        if (content != null) blog.setContent(content);
        if (image != null && !image.isEmpty()) {
            String imagePath = saveImage(image);
            blog.setImage(imagePath);
        }
        blogRepository.save(blog);
        return new ResponseEntity<>("Blog post updated successfully", HttpStatus.CREATED);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.getAll();
    }

    @Override
    public Message deleteBlog(String blogId) {
        Optional<Blog> blogs = blogRepository.findById(blogId);
        if (blogs.isEmpty()) throw new BadRequestException("BLG001", "Blog not found!!");
        Blog blog = blogs.get();
        blog.setIsDeleted(true);
        blogRepository.save(blog);
        return Message.builder()
                .message("Blog deleted successfully!!")
                .build();
    }

    private String saveImage(MultipartFile imageFile) {
        String uploadDirectory = "src/main/resources";

        try {
            Path path = Paths.get(uploadDirectory + "/" + imageFile.getOriginalFilename());

            Files.write(path, imageFile.getBytes());

            return path.toString();
        } catch (IOException exception) {
            System.out.println("The image error is " + exception.getMessage());
            throw new BadRequestException("IMG001", "Blog image issues!!");
        }
    }
}
