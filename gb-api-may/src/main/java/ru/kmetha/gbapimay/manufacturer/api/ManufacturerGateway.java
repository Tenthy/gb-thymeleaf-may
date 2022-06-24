package ru.kmetha.gbapimay.manufacturer.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapimay.manufacturer.dto.ManufacturerDto;

import java.util.List;

public interface ManufacturerGateway {

    @GetMapping("/all")
    public String getManufacturerList(Model model);

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id);

    @GetMapping("/{manufacturerId}")
    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "manufacturerId") Long manufacturerId);

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveManufacturer(ManufacturerDto manufacturerDto);

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('product.update')")
    public String deleteById(@PathVariable(name = "id") Long id);
}
