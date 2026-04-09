package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException; // N'oubliez pas l'import !
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts")
    public ResponseEntity<List<Post>> retrieveAllPosts(@RequestParam(required = false) String value) {
        List<Post> posts = value == null || value.isBlank()
                ? service.getAll()
                : service.getAllByTitleOrContent(value);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a post by id")
    public ResponseEntity<Post> retrievePostById(@PathVariable UUID id) throws PostNotFoundByIdException {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public ResponseEntity<Post> createPost(@RequestBody CreationPostRequest request) {
        Post post = service.create(request.getTitle(), request.getContent(), request.getCategoryId());
        return ResponseEntity
                .created(URI.create("v1/posts/" + post.getId()))
                .body(post);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public ResponseEntity<Post> updatePost(
            @PathVariable UUID id,
            @RequestBody CreationPostRequest request) throws PostNotFoundByIdException {
        Post updatedPost = service.update(id, request.getTitle(), request.getContent());
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) throws PostNotFoundByIdException {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}