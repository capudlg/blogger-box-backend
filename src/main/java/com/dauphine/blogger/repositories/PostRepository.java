package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    // Pour récupérer les articles d'une catégorie
    List<Post> findAllByCategoryId(UUID categoryId);

    // Pour récupérer tous les articles triés par date (du plus récent au plus ancien)
    List<Post> findAllByOrderByCreatedDateDesc();
}