package ru.kmetha.gbthymeleafmay.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.kmetha.gbthymeleafmay.entity.Manufacturer;
import ru.kmetha.gbthymeleafmay.service.ManufacturerService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private ManufacturerService manufacturerService;

    @GetMapping("/all")
    public String getManufacturerList(Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "manufacturer-list";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        Manufacturer manufacturer;
        if (id != null) {
            manufacturer = manufacturerService.findById(id);
        } else {
            manufacturer = new Manufacturer();
        }
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer-form";
    }

    @GetMapping("/{manufacturerId}")
    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "manufacturerId") Long manufacturerId) {
        Manufacturer manufacturer;
        if (manufacturerId != null) {
            manufacturer = manufacturerService.findById(manufacturerId);
        } else {
            return "redirect:/manufacturer/all";
        }
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer-info";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveManufacturer(Manufacturer manufacturer) {
        manufacturerService.save(manufacturer);
        System.out.println("manufacturer #" + manufacturer.getId() + " was saved");
        return "redirect:/manufacturer/all";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('product.update')")
    public String deleteById(@PathVariable(name = "id") Long id) {
        manufacturerService.deleteById(id);
        System.out.println("manufacturer #" + id + " was deleted");
        return "redirect:/manufacturer/all";
    }
}
