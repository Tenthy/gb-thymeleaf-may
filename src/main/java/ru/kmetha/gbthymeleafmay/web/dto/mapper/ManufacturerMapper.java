package ru.kmetha.gbthymeleafmay.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.kmetha.gbapimay.manufacturer.dto.ManufacturerDto;
import ru.kmetha.gbthymeleafmay.entity.Manufacturer;

@Mapper
public interface ManufacturerMapper {
    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);

    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);
}
