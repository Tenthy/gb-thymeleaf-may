package ru.kmetha.gbthymeleafmay.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kmetha.gbthymeleafmay.dao.ProductDao;
import ru.kmetha.gbthymeleafmay.entity.Product;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductDao productDao;

    public Product save(Product product) {
        if (product.getId() != null) {
            Optional<Product> productFromDBOptional = productDao.findById(product.getId());
            if (productFromDBOptional.isPresent()) {
                Product productFromDB = productFromDBOptional.get();
                productFromDB.setTitle(product.getTitle());
                productFromDB.setCost(product.getCost());
                productFromDB.setManufactureDate(product.getManufactureDate());
                productFromDB.setStatus(product.getStatus());
                return productDao.save(productFromDB);
            }
        }
        return productDao.save(product);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public void deleteById(Long id) {
        try {
            productDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }
}
