package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.Category;
import com.radoslawsawicki.backendreactnotesapp.exception.CategoryNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getCategory(final Long id) throws CategoryNotFoundException{
        return repository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public Category saveCategory(final Category category) {
        return repository.save(category);
    }
}
