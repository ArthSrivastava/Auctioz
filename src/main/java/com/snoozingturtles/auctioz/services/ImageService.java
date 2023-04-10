package com.snoozingturtles.auctioz.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {
    String uploadImage(MultipartFile imageFile, String productId, String sellerId) throws IOException;
    InputStream downloadImage(String imageName) throws FileNotFoundException;
}
