package ru.kmetha.gbthymeleafmay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbthymeleafmay.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

}
