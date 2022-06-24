package ru.kmetha.gbapimay.product.dto;

import lombok.*;
import ru.kmetha.gbapimay.cart.dto.CartDto;
import ru.kmetha.gbapimay.category.dto.CategoryDto;
import ru.kmetha.gbapimay.common.enums.Status;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    private BigDecimal cost;
    @PastOrPresent
    private LocalDate manufactureDate;
    @NotNull
    private Status status;
    private String manufacturer;
    private Set<CategoryDto> categories;
    private Set<CartDto> carts;
}
