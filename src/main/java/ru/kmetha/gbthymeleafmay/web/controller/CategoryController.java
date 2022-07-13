package ru.kmetha.gbthymeleafmay.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapimay.category.CategoryDto;
import ru.kmetha.gbthymeleafmay.entity.Category;
import ru.kmetha.gbthymeleafmay.service.CategoryService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/all")
    public String getCategoryList(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category-list";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        CategoryDto category;
        if (id != null) {
            category = categoryService.findById(id);
        } else {
            category = new Category();
        }
        model.addAttribute("category", category);
        return "category-form";
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "categoryId") Long categoryId) {
        Category category;
        if (categoryId != null) {
            category = categoryService.findById(categoryId);
        } else {
            return "redirect:/category/all";
        }
        model.addAttribute("category", category);
        return "category-info";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveCategory(Category category) {
        categoryService.save(category);
        System.out.println("category #" + category.getId() + " was saved");
        return "redirect:/category/all";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('product.update')")
    public String deleteById(@PathVariable(name = "id") Long id) {
        categoryService.deleteById(id);
        System.out.println("category #" + id + " was deleted");
        return "redirect:/category/all";
    }
}
