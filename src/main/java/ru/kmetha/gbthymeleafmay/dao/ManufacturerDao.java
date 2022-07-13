package ru.kmetha.gbthymeleafmay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbthymeleafmay.entity.Manufacturer;

import java.util.Optional;

public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {
    Manufacturer findByNameLike(String name);
    Optional<Manufacturer> findByName(String name);
}
