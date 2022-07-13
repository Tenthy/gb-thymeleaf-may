package ru.kmetha.gbthymeleafmay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kmetha.gbthymeleafmay.dao.CategoryDao;
import ru.kmetha.gbthymeleafmay.entity.Category;
import ru.kmetha.gbthymeleafmay.entity.Manufacturer;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryDao categoryDao;

    public Category save(Category category) {
        if (category.getId() != null) {
            Optional<Category> categoryFromDBOptional = categoryDao.findById(category.getId());
            if (categoryFromDBOptional.isPresent()) {
                Category categoryFromDB = categoryFromDBOptional.get();
                categoryFromDB.setTitle(category.getTitle());
                return categoryDao.save(categoryFromDB);
            }
        }
        return categoryDao.save(category);
    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryDao.findById(id).orElse(null);
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public void deleteById(Long id) {
        try {
            categoryDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }
}
