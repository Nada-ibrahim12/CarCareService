package org.os.carcareservice.service;

import org.os.carcareservice.entity.ServiceForCarType;
import org.os.carcareservice.repository.ServiceForCarTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceForCarTypeService {
    private final ServiceForCarTypeRepository serviceForCarTypeRepository;

    public ServiceForCarTypeService(ServiceForCarTypeRepository serviceForCarTypeRepository) {
        this.serviceForCarTypeRepository = serviceForCarTypeRepository;
    }

    public List<ServiceForCarType> getByServiceId(Integer serviceId) {
        return serviceForCarTypeRepository.findByService_ServiceId(serviceId);
    }

    public ServiceForCarType add(ServiceForCarType serviceForCarType) {
        return serviceForCarTypeRepository.save(serviceForCarType);
    }

    public ServiceForCarType update(Integer id, ServiceForCarType updated) {
        ServiceForCarType sct = serviceForCarTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mapping not found"));
        sct.setCarType(updated.getCarType());
        sct.setService(updated.getService());
        return serviceForCarTypeRepository.save(sct);
    }

    public void delete(Integer id) {
        serviceForCarTypeRepository.deleteById(id);
    }
}
