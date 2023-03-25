package com.snoozingturtles.auctioz.services.impl;

import com.snoozingturtles.auctioz.dto.CategoryDto;
import com.snoozingturtles.auctioz.exceptions.EntityNotFoundException;
import com.snoozingturtles.auctioz.models.Category;
import com.snoozingturtles.auctioz.repositories.CategoryRepo;
import com.snoozingturtles.auctioz.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return modelMapper.map(categoryRepo.save(category), CategoryDto.class);
    }
    @Override
    public void updateCategory(String categoryId, CategoryDto categoryDto) {
        CategoryDto categoryById = getCategoryById(categoryId);
        categoryById.setName(categoryDto.getName());
        categoryRepo.save(modelMapper.map(categoryById, Category.class));
    }

    @Override
    public CategoryDto getCategoryById(String categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("No such category found!"));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
    }

    @Override
    public void deleteCategory(String categoryId) {
        CategoryDto categoryById = getCategoryById(categoryId);
        categoryRepo.delete(modelMapper.map(categoryById, Category.class));
    }
}
