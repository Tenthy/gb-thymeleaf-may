package ru.kmetha.gbthymeleafmay.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kmetha.gbapimay.cart.api.CartGateway;
import ru.kmetha.gbapimay.cart.dto.CartDto;
import ru.kmetha.gbapimay.product.api.ProductGateway;
import ru.kmetha.gbthymeleafmay.entity.Cart;
import ru.kmetha.gbthymeleafmay.entity.Product;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartGateway cartGateway;
    private final ProductGateway productGateway;

    @GetMapping
    public String getProductsIntoCart(Model model) {
        CartDto cartDto = cartGateway.findById(1L);
        model.addAttribute("cart", cart.findAllProductInCart());
        return "product-list-into-cart";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable(name = "productId") Long productId) {
        Product product = productGateway.findById(productId);
        Cart cart = cartGateway.findById(1L);
        cart.deleteProduct(product);
        cartGateway.save(cart);
        return "redirect:/cart";
    }
}
