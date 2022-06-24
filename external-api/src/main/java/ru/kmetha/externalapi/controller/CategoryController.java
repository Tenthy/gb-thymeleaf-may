package ru.kmetha.externalapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapimay.category.api.CategoryGateway;
import ru.kmetha.gbapimay.category.dto.CategoryDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private CategoryGateway categoryGateway;

    @GetMapping("/all")
    public String getCategoryList(Model model) {
        return categoryGateway.getCategoryList(model);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        return categoryGateway.showForm(model, id);
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "categoryId") Long categoryId) {
        return categoryGateway.showForm(model, categoryId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveCategory(CategoryDto categoryDto) {
        return categoryGateway.saveCategory(categoryDto);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('product.update')")
    public String deleteById(@PathVariable(name = "id") Long id) {
        return categoryGateway.deleteById(id);
    }
}
