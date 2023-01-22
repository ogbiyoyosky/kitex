package com.kitex.kitex.menu.services;

import com.kitex.kitex.menu.dto.CreateCategoryDto;
import com.kitex.kitex.menu.entity.Category;
import com.kitex.kitex.menu.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private  final CategoryRepository categoryRepository;

    public Category createCategory( CreateCategoryDto payload ) {
        Category category = new Category();
        category.setName(payload.getName());

        return this.categoryRepository.save(category);
    }

    public List<Category> getCategories() {
       return this.categoryRepository.findAll();
    }

}
