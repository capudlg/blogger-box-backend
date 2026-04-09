package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieve all categories or filter like name")
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getAllLikeName(name);
        return ResponseEntity.ok(categories); // Statut 200 OK
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id", description = "Retrieve a category by id")
    public ResponseEntity<Category> getById(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category); // Statut 200 OK
    }

    @PostMapping
    @Operation(summary = "Create new category")
    public ResponseEntity<Category> createCategory(@RequestBody CreationCategoryRequest request) {
        Category category = categoryService.create(request.getName());
        // Statut 201 Created avec l'URI de la nouvelle ressource
        return ResponseEntity
                .created(URI.create("v1/categories/" + category.getId()))
                .body(category);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing category")
    public ResponseEntity<Category> updateCategory(
            @PathVariable UUID id,
            @RequestBody CreationCategoryRequest request) throws CategoryNotFoundByIdException {
        Category updatedCategory = categoryService.update(id, request.getName());
        return ResponseEntity.ok(updatedCategory); // Statut 200 OK
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build(); // Statut 204 No Content
    }
}