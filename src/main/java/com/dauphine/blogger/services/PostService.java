package com.dauphine.blogger.services;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException; // 1. IMPORT AJOUTÉ
import com.dauphine.blogger.models.Post;
import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAll();
    List<Post> getAllByCategoryId(UUID categoryId);
    List<Post> getAllByTitleOrContent(String value);
    Post getById(UUID id) throws PostNotFoundByIdException;
    Post create(String title, String content, UUID categoryId);
    Post update(UUID id, String title, String content) throws PostNotFoundByIdException;
    boolean deleteById(UUID id) throws PostNotFoundByIdException;
}