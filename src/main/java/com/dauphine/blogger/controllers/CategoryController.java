package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API", description = "Endpoints for managing categories")
public class CategoryController {

    @GetMapping
    @Operation(summary = "Retrieve all categories", description = "Returns a list of all categories")
    public List<Category> retrieveAllCategories() {
        return null; // TODO: Implement
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by id", description = "Returns a category based on its UUID")
    public Category retrieveCategoryById(
            @Parameter(description = "Category UUID") @PathVariable UUID id) {
        return null; // TODO: Implement
    }

    @GetMapping("/{id}/posts")
    @Operation(summary = "Retrieve all posts per a category", description = "Returns all posts belonging to a specific category")
    public List<Post> retrievePostsByCategory(
            @Parameter(description = "Category UUID") @PathVariable UUID id) {
        return null; // TODO: Implement
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category createCategory(@RequestBody CreationCategoryRequest request) {
        return null; // TODO: Implement
    }

    @PutMapping("/{id}") // Or @PatchMapping depending on partial/full update
    @Operation(summary = "Update the name of a category")
    public Category updateCategory(
            @Parameter(description = "Category UUID") @PathVariable UUID id,
            @RequestBody CreationCategoryRequest request) {
        return null; // TODO: Implement
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category")
    public void deleteCategory(
            @Parameter(description = "Category UUID") @PathVariable UUID id) {
        // TODO: Implement
    }
}