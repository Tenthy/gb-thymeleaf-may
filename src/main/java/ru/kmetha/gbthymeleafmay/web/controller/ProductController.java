package ru.kmetha.gbthymeleafmay.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbthymeleafmay.entity.Cart;
import ru.kmetha.gbthymeleafmay.entity.Product;
import ru.kmetha.gbthymeleafmay.service.CartService;
import ru.kmetha.gbthymeleafmay.service.ProductService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Product product;
        if (id != null) {
            product = productService.findById(id);
        } else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "productId") Long productId) {
        Product product;
        if (productId != null) {
            product = productService.findById(productId);
        } else {
            return "redirect:/product/all";
        }
        model.addAttribute("product", product);
        return "product-info";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveProduct(Product product) {
        product.setManufactureDate(LocalDate.now());
        productService.save(product);
        System.out.println("product #" + product.getId() + " was saved");
        return "redirect:/product/all";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('product.update')")
    public String deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        System.out.println("product #" + id + " was deleted");
        return "redirect:/product/all";
    }

    @GetMapping("/add/{productId}")
    public String addProductInCart(@PathVariable(name = "productId") Long productId) {
        Product product = productService.findById(productId);
        Cart cart = cartService.findById(1L);
        cart.addProduct(product);
        cartService.save(cart);
        return "redirect:/product/all";
    }
}
