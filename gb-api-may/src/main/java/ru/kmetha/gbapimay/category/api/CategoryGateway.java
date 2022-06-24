package ru.kmetha.gbapimay.category.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kmetha.gbapimay.category.dto.CategoryDto;

import java.util.Locale;

public interface CategoryGateway {

    @GetMapping("/all")
    public String getCategoryList(Model model);

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id);

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "categoryId") Long categoryId);

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveCategory(CategoryDto categoryDto);

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('product.update')")
    public String deleteById(@PathVariable(name = "id") Long id);
}
