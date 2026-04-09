package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts") // Versioning et pluriel [cite: 3014, 3078]
@Tag(name = "Post API", description = "Endpoints pour la gestion des articles")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts ordered by creation date",
            description = "Renvoie tous les articles triés du plus récent au plus ancien")
    public List<Post> retrieveAllPosts() {
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post createPost(@RequestBody CreationPostRequest request) {
        return service.create(request.getTitle(), request.getContent(), request.getCategoryId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post updatePost(
            @Parameter(description = "ID de l'article") @PathVariable UUID id,
            @RequestBody CreationPostRequest request) {
        return service.update(id, request.getTitle(), request.getContent());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public void deletePost(
            @Parameter(description = "ID de l'article") @PathVariable UUID id) {
        service.deleteById(id);
    }
}