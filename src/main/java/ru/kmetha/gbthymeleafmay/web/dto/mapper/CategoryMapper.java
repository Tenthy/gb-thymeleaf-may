package ru.kmetha.gbthymeleafmay.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.kmetha.gbapimay.category.dto.CategoryDto;
import ru.kmetha.gbthymeleafmay.entity.Category;

@Mapper
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);
}
