package org.os.carcareservice.service;

import org.os.carcareservice.dto.ServiceDTO;
import org.os.carcareservice.entity.Provider;
import org.os.carcareservice.entity.Service;
import org.os.carcareservice.repository.ProviderRepository;
import org.os.carcareservice.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ProviderRepository providerRepository;


    public ServiceService(ServiceRepository serviceRepository, ProviderRepository providerRepository) {
        this.serviceRepository = serviceRepository;
        this.providerRepository = providerRepository;
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(Integer id) {
        return serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public Service saveService(ServiceDTO serviceDTO) {
        Service service = new Service();
        service.setServiceName(serviceDTO.getName());
        service.setServiceStatus(serviceDTO.getStatus());
        service.setServicePrice(serviceDTO.getPrice());

        Provider provider = providerRepository.findById(serviceDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        service.setProvider(provider);

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
