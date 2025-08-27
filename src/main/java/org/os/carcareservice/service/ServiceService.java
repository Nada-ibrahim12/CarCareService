package org.os.carcareservice.service;

import org.os.carcareservice.entity.Service;
import org.os.carcareservice.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(Integer id) {
        return serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public Service saveService(Service service) {
        return serviceRepository.save(service);
    }

    public Service updateService(Integer id, Service updatedService) {
        Service service = getServiceById(id);
        service.setServiceName(updatedService.getServiceName());
        service.setServiceStatus(updatedService.getServiceStatus());
        service.setServicePrice(updatedService.getServicePrice());
        return serviceRepository.save(service);
    }

    public void deleteService(Integer id) {
        serviceRepository.deleteById(id);
    }

    public List<Service> searchByName(String name) {
        return serviceRepository.findByServiceNameContainingIgnoreCase(name);
    }
}
