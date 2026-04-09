package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> temporaryPosts;

    public PostServiceImpl() {
        this.temporaryPosts = new ArrayList<>();
        // Ajout de fausses données (avec des dates au format ISO AAAA-MM-JJ pour faciliter le tri)
        UUID catId = UUID.randomUUID();
        temporaryPosts.add(new Post(UUID.randomUUID(), "Post 1", "Contenu 1", "2024-03-20", catId));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Post 2", "Contenu 2", "2024-03-24", catId)); // Le plus récent
    }

    @Override
    public List<Post> getAll() {
        // Retrieve all posts ordered by creation date (to show latest post, in home page)
        return temporaryPosts.stream()
                // Tri décroissant (du plus récent au plus ancien)
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        // Retrieve all posts per a category
        return temporaryPosts.stream()
                .filter(post -> categoryId.equals(post.getCategoryId()))
                .collect(Collectors.toList());
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, LocalDate.now().toString(), categoryId);
        temporaryPosts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
        }
        return post;
    }

    @Override
    public boolean deleteById(UUID id) {
        return temporaryPosts.removeIf(post -> id.equals(post.getId()));
    }
}