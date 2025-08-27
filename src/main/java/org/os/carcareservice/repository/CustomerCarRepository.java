package org.os.carcareservice.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.type.descriptor.java.IntegerJavaType;
import org.os.carcareservice.entity.CustomerCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCarRepository extends JpaRepository<CustomerCar, Integer> {
        Optional<CustomerCar> findByToken(String token);
    List<CustomerCar> findByCustomer_Token(String token);
    List<CustomerCar> findByCustomer_Id(Integer id);
    Optional<CustomerCar> findByPlate(String plate);
}
