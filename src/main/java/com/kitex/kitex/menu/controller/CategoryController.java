package com.kitex.kitex.menu.controller;

import com.kitex.kitex.menu.dto.CreateCategoryDto;
import com.kitex.kitex.menu.services.CategoryService;
import com.kitex.kitex.util.Response;
import com.kitex.kitex.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN')")
    @PostMapping
    public ResponseEntity<ResponseDto> createMenuCategory(@Valid @RequestBody CreateCategoryDto payload) {
        return Response.send("Successfully create a menu category",this.categoryService.createCategory(payload),201,true);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_KITCHEN_ADMIN', 'SCOPE_ROLE_CUSTOMER','SCOPE_ROLE_DRIVER')")
    @GetMapping
    public ResponseEntity<ResponseDto> getCategories() {
        return Response.send("Successfully create a menu category",this.categoryService.getCategories(),200,true);
    }
}

