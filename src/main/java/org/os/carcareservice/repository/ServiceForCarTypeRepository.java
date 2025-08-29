package org.os.carcareservice.repository;

import org.os.carcareservice.entity.ServiceForCarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceForCarTypeRepository extends JpaRepository<ServiceForCarType, Integer> {
    List<ServiceForCarType> findByService_ServiceId(Integer serviceId);
}
