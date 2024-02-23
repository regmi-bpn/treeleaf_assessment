package com.example.treeleaf_assessment.dto.blog;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BlogRequest {

    private String title;

    private String content;
}
