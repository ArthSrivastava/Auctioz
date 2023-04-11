package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/sellers/{sellerId}/products/{productId}/images")
    public ResponseEntity<ApiResponse> uploadImage(@RequestParam("image") MultipartFile image,
                                                   @PathVariable String sellerId,
                                                   @PathVariable String productId) throws IOException {
        String imageName = imageService.uploadImage(image, productId, sellerId);

        URI uri = UriComponentsBuilder.fromUriString("/api/v1/images/{imageFileName}").buildAndExpand(imageName)
                .toUri();
        return ResponseEntity.created(uri)
                .body(
                        ApiResponse.builder()
                                .message("Image uploaded successfully!")
                                .success(true)
                                .build()
                );
    }

    @GetMapping("/images/{imageName}")
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream inputStream = imageService.downloadImage(imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }

}
