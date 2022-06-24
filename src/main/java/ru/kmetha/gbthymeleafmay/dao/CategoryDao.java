package ru.kmetha.gbthymeleafmay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbthymeleafmay.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
