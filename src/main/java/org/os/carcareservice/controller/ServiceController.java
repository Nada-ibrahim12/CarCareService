package org.os.carcareservice.controller;

import org.os.carcareservice.dto.ServiceDTO;
import org.os.carcareservice.entity.Provider;
import org.os.carcareservice.entity.Service;
import org.os.carcareservice.repository.ProviderRepository;
import org.os.carcareservice.service.ProviderService;
import org.os.carcareservice.service.ServiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Service> getAll() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public Service getById(@PathVariable Integer id) {
        return serviceService.getServiceById(id);
    }

    @PreAuthorize("hasRole('PROVIDER')")
    @PostMapping
    public Service create(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.saveService(serviceDTO);
    }

    @PreAuthorize("hasRole('PROVIDER')")
    @PutMapping("/{id}")
    public Service update(@PathVariable Integer id, @RequestBody Service service) {
        return serviceService.updateService(id, service);
    }

    @PreAuthorize("hasRole('PROVIDER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        serviceService.deleteService(id);
    }

    @GetMapping("/search")
    public List<Service> search(@RequestParam String name) {
        return serviceService.searchByName(name);
    }
}
