package com.snoozingturtles.auctioz.services;

import com.snoozingturtles.auctioz.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    void updateCategory(String categoryId, CategoryDto categoryDto);
    CategoryDto getCategoryById(String categoryId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(String categoryId);
}
