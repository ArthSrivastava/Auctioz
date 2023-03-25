package com.snoozingturtles.auctioz.controllers;

import com.snoozingturtles.auctioz.dto.CategoryDto;
import com.snoozingturtles.auctioz.payloads.ApiResponse;
import com.snoozingturtles.auctioz.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.createCategory(categoryDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{categoryId}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(
                ApiResponse.builder()
                        .message("Category created successfully!")
                        .success(true)
                        .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable String categoryId,
                                                  @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Category updated successfully!")
                        .success(true)
                        .build()
        );
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Category deleted successfully")
                        .success(true)
                        .build()
        );
    }

}
