package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories") // Versioning et pluriel [cite: 3013, 3078]
@Tag(name = "Category API", description = "Endpoints pour la gestion des catégories")
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    // Injection des deux services pour gérer les sous-ressources [cite: 3371-3375, 3387-3389]
    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieve all categories or filter like name"
    ) // [cite: 421, 422, 423, 424, 425]
    public List<Category> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getAllLikeName(name); // [cite: 426, 427, 428]
        return categories; // [cite: 429]
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by id")
    public Category retrieveCategoryById(
            @Parameter(description = "ID de la catégorie") @PathVariable UUID id) {
        return categoryService.getById(id);
    }

    @GetMapping("/{id}/posts") // Sous-ressource : posts d'une catégorie [cite: 3065-3068, 3122]
    @Operation(summary = "Retrieve all posts per a category")
    public List<Post> retrievePostsByCategory(
            @Parameter(description = "ID de la catégorie") @PathVariable UUID id) {
        return postService.getAllByCategoryId(id);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category createCategory(@RequestBody CreationCategoryRequest request) {
        return categoryService.create(request.getName());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a category")
    public Category updateCategory(
            @PathVariable UUID id,
            @RequestBody CreationCategoryRequest request) {
        return categoryService.update(id, request.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category")
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteById(id);
    }
}