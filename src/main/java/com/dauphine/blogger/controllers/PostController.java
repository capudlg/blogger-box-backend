package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts") // Tip #2 & #3: Versioning and consistent path
@Tag(name = "Post API", description = "Endpoints for managing posts")
public class PostController {

    @GetMapping
    @Operation(summary = "Retrieve all posts", description = "Returns all posts ordered by creation date")
    public List<Post> retrieveAllPosts() {
        // TODO: Implement (Sort by creation date here to show latest post on home page)
        return null;
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post createPost(@RequestBody CreationPostRequest request) {
        return null; // TODO: Implement
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post updatePost(
            @Parameter(description = "Post UUID") @PathVariable UUID id,
            @RequestBody CreationPostRequest request) {
        return null; // TODO: Implement
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public void deletePost(
            @Parameter(description = "Post UUID") @PathVariable UUID id) {
        // TODO: Implement
    }
}