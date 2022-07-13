package ru.kmetha.externalapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapimay.manufacturer.api.ManufacturerGateway;
import ru.kmetha.gbapimay.manufacturer.dto.ManufacturerDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private ManufacturerGateway manufacturerGateway;

    @GetMapping("/all")
    public String getManufacturerList(Model model) {
        return manufacturerGateway.getManufacturerList(model);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        return manufacturerGateway.showForm(model, id);
    }

    @GetMapping("/{manufacturerId}")
    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "manufacturerId") Long manufacturerId) {
        return manufacturerGateway.showInfo(model, manufacturerId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveManufacturer(ManufacturerDto manufacturerDto) {
        return manufacturerGateway.saveManufacturer(manufacturerDto);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('product.update')")
    public String deleteById(@PathVariable(name = "id") Long id) {
        return manufacturerGateway.deleteById(id);
    }
}
