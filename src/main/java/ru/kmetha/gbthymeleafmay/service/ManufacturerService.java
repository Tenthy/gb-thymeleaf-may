package ru.kmetha.gbthymeleafmay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kmetha.gbthymeleafmay.dao.ManufacturerDao;
import ru.kmetha.gbthymeleafmay.entity.Manufacturer;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManufacturerService {

    private final ManufacturerDao manufacturerDao;

    public Manufacturer save(Manufacturer manufacturer) {
        if (manufacturer.getId() != null) {
            Optional<Manufacturer> manufacturerFromDBOptional = manufacturerDao.findById(manufacturer.getId());
            if (manufacturerFromDBOptional.isPresent()) {
                Manufacturer manufacturerFromDB = manufacturerFromDBOptional.get();
                manufacturerFromDB.setName(manufacturer.getName());
                return manufacturerDao.save(manufacturerFromDB);
            }
        }
        return manufacturerDao.save(manufacturer);
    }

    @Transactional(readOnly = true)
    public Manufacturer findById(Long id) {
        return manufacturerDao.findById(id).orElse(null);
    }

    public List<Manufacturer> findAll() {
        return manufacturerDao.findAll();
    }

    public void deleteById(Long id) {
        try {
            manufacturerDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }
}
