package ru.kmetha.gbthymeleafmay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbthymeleafmay.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {

}
