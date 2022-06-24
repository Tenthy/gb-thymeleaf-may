package ru.kmetha.gbthymeleafmay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.gbthymeleafmay.entity.Manufacturer;

public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {

}
