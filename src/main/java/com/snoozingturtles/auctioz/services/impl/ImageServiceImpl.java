package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.ProductDto;
import com.snoozingturtles.auctioz.services.ImageService;
import com.snoozingturtles.auctioz.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${images.path}")
    private String path;

    private final ProductService productService;
    @Override
    public String uploadImage(MultipartFile imageFile,
                              String productId,
                              String sellerId) throws IOException {
        String imageName = imageFile.getOriginalFilename();
        String randomUuid = UUID.randomUUID().toString();
        assert imageName != null;
        String newImageFileName = randomUuid + imageName.substring(imageName.indexOf("."));

        //Update product
        ProductDto productById = productService.getProductById(productId, sellerId);
        productById.setImageName(newImageFileName);
        productService.updateProduct(productId, sellerId, productById);
        String fullPath = path + File.separator + newImageFileName;

        File file = new File(path);
        if(!file.exists()) {
            file.mkdir();
        }
        InputStream inputStream = imageFile.getInputStream();
        Files.copy(inputStream, Paths.get(fullPath));
        return newImageFileName;
    }

    @Override
    public InputStream downloadImage(String imageName) throws FileNotFoundException {
        String fullPath = path + File.separator + imageName;
        return new FileInputStream(fullPath);
    }
}
