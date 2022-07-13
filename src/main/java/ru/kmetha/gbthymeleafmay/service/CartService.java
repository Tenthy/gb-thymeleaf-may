package ru.kmetha.gbthymeleafmay.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kmetha.gbthymeleafmay.dao.CartDao;
import ru.kmetha.gbthymeleafmay.entity.Cart;

@Service
@RequiredArgsConstructor
public class CartService {

    public final CartDao cartDao;

    public Cart save(Cart cart) {
        return cartDao.saveAndFlush(cart);
    }

    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null);
    }
}
