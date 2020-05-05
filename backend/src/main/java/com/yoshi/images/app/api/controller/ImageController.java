package com.yoshi.images.app.api.controller;

import com.yoshi.images.domain.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
@AllArgsConstructor
@Slf4j
public class ImageController {
    private ImageRepository imageRepository;

    @PostMapping("")
    public ResponseEntity<String> upload(@RequestBody ImageParam postImage) {
        String objectKey = imageRepository.writeImage(postImage.getImage());
        log.info("S3ObjectKey={}", objectKey);
        return ResponseEntity.ok("ok");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class ImageParam {
        String image;
    }
}
