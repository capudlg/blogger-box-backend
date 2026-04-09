package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> getAll() {
        // Utilise la méthode de tri définie dans le repository
        return repository.findAllByOrderByCreatedDateDesc();
    }
    @Override
    public List<Post> getAllByTitleOrContent(String value) {
        return repository.findAllByTitleOrContent(value);
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    @Override
    public Post getById(UUID id) throws PostNotFoundByIdException {
        return repository.findById(id)
                .orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, LocalDateTime.now(), categoryId);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) throws PostNotFoundByIdException {
        Post post = getById(id);
        post.setTitle(title);
        post.setContent(content);
        return repository.save(post);
    }

    // AJOUT DU THROWS ICI
    @Override
    public boolean deleteById(UUID id) throws PostNotFoundByIdException {
        Post post = getById(id);
        repository.delete(post);
        return true;
    }
}