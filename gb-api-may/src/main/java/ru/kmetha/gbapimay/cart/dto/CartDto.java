package ru.kmetha.gbapimay.cart.dto;

import lombok.*;
import ru.kmetha.gbapimay.product.dto.ProductDto;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private Long id;
    private String status;
    private Set<ProductDto> products;
}
