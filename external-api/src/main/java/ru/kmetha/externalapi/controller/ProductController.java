package ru.kmetha.externalapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapimay.product.api.ProductGateway;
import ru.kmetha.gbapimay.product.dto.ProductDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private final ProductGateway productGateway;

    @GetMapping("/all")
    public String getProductList(Model model) {
        return productGateway.getProductList(model);
    }

    @GetMapping
//    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        return productGateway.showForm(model, id);
    }

    @GetMapping("/{productId}")
//    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "productId") Long productId) {
        return showInfo(model, productId);
    }

    @PostMapping
//    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveProduct(ProductDto productDto) {
        return saveProduct(productDto);
    }

    @GetMapping("/delete/{id}")
//    @PreAuthorize("hasAnyAuthority('product.update')")
    public String deleteById(@PathVariable(name = "id") Long id) {
        return productGateway.deleteById(id);
    }

    @GetMapping("/add/{productId}")
    public String addProductInCart(@PathVariable(name = "productId") Long productId) {
        return productGateway.addProductInCart(productId);
    }
}
