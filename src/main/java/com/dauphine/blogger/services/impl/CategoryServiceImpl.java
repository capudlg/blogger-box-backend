package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> getAllLikeName(String name) {
        return repository.findAllLikeName(name);
    }

    @Override
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }

    @Override
    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);
        return repository.save(category);
    }

    @Override
    public Category update(UUID id, String name) throws CategoryNotFoundByIdException {
        Category category = getById(id);
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public boolean deleteById(UUID id) throws CategoryNotFoundByIdException {
        Category category = getById(id);
        repository.delete(category);
        return true;
    }
}