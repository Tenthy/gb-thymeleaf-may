package ru.kmetha.gbapimay.cart.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CartGateway {

    @GetMapping
    public String getProductsIntoCart(Model model);

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable(name = "productId") Long productId);
}
