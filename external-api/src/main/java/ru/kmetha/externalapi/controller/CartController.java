package ru.kmetha.externalapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kmetha.gbapimay.cart.api.CartGateway;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private final CartGateway cartGateway;

    @GetMapping
    public String getProductsIntoCart(Model model) {
        return cartGateway.getProductsIntoCart(model);
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable(name = "productId") Long productId) {
        return cartGateway.deleteProduct(productId);
    }
}
