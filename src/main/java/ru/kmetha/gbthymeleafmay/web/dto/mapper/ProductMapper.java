package ru.kmetha.gbthymeleafmay.web.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kmetha.gbapimay.category.dto.CategoryDto;
import ru.kmetha.gbapimay.product.dto.ProductDto;
import ru.kmetha.gbthymeleafmay.dao.CategoryDao;
import ru.kmetha.gbthymeleafmay.dao.ManufacturerDao;
import ru.kmetha.gbthymeleafmay.entity.Category;
import ru.kmetha.gbthymeleafmay.entity.Manufacturer;
import ru.kmetha.gbthymeleafmay.entity.Product;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(uses = ManufacturerMapper.class)
public interface ProductMapper {
    Product toProduct(ProductDto productDto, @Context ManufacturerDao manufacturerDao, @Context CategoryDao categoryDao);

    ProductDto toProductDto(Product product);

    default Manufacturer getManufacturer(String manufacturer, @Context ManufacturerDao manufacturerDao) {
        return manufacturerDao.findByName(manufacturer).orElseThrow(
                () -> new NoSuchElementException("There isn't manufacturer with name " + manufacturer)
        );
    }

    default String getManufacturer(Manufacturer manufacturer) {
        return manufacturer.getName();
    }

    default Set<Category> categoryDtoSetToCategorySet(Set<CategoryDto> categories, @Context CategoryDao categoryDao) {
        return categories.stream().map(c -> categoryDao.findById(c.getId())
                        .orElseThrow(
                                () -> new NoSuchElementException("There isn't category with id: " + c.getId())
                        ))
                .collect(Collectors.toSet());
    }
}
