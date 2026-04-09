package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    // On remplace la liste par le Repository JPA
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll(); // Requête SQL générée : SELECT * FROM category
    }

    @Override
    public Category getById(UUID id) {
        // findById renvoie un "Optional". S'il ne trouve rien, on renvoie null pour le moment.
        return repository.findById(id).orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);
        return repository.save(category); // Requête SQL générée : INSERT INTO category ...
    }

    @Override
    public Category update(UUID id, String name) {
        Category category = getById(id);
        if (category != null) {
            category.setName(name);
            return repository.save(category); // Si l'ID existe, save() fera un UPDATE
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        if (getById(id) != null) {
            repository.deleteById(id); // Requête SQL générée : DELETE FROM category WHERE id = ?
            return true;
        }
        return false;
    }
}