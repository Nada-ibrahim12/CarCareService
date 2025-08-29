package org.os.carcareservice.controller;

import org.os.carcareservice.entity.ServiceForCarType;
import org.os.carcareservice.service.ServiceForCarTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceForCarTypeController {
    private final ServiceForCarTypeService serviceForCarTypeService;

    public ServiceForCarTypeController(ServiceForCarTypeService serviceForCarTypeService) {
        this.serviceForCarTypeService = serviceForCarTypeService;
    }

    @GetMapping("/services/{id}/car-types")
    public List<ServiceForCarType> getByService(@PathVariable Integer id) {
        return serviceForCarTypeService.getByServiceId(id);
    }

    @PostMapping("/service-for-car-type")
    public ServiceForCarType create(@RequestBody ServiceForCarType serviceForCarType) {
        return serviceForCarTypeService.add(serviceForCarType);
    }

    @PutMapping("/service-for-car-type/{id}")
    public ServiceForCarType update(@PathVariable Integer id, @RequestBody ServiceForCarType serviceForCarType) {
        return serviceForCarTypeService.update(id, serviceForCarType);
    }

    @DeleteMapping("/service-for-car-type/{id}")
    public void delete(@PathVariable Integer id) {
        serviceForCarTypeService.delete(id);
    }
}
