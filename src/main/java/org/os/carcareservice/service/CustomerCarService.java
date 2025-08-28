package org.os.carcareservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.os.carcareservice.dto.CustomerCarDTO;
import org.os.carcareservice.entity.CarType;
import org.os.carcareservice.entity.Customer;
import org.os.carcareservice.entity.CustomerCar;
import org.os.carcareservice.repository.CustomerCarRepository;
import org.os.carcareservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerCarService {

    private final CustomerRepository customerRepository;
    private final CustomerCarRepository carRepository;

    @PersistenceContext
    private EntityManager entityManager;
    public CustomerCarService(CustomerRepository customerRepository,
            CustomerCarRepository customerCarRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = customerCarRepository;
    }
//add car by token

    // public CustomerCar addCar(String customerToken, CustomerCar car) {
    //     Customer customer = customerRepository.findByToken(customerToken)
    //             .orElseThrow(() -> new RuntimeException("Customer not found"));
    //     car.setCustomer(customer);
    //     return carRepository.save(car);
    // }
// add car by id just for test

    public CustomerCarDTO addCarByCustomerId(Long customerId, CustomerCarDTO dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CarType carType = entityManager.getReference(CarType.class, dto.getCarTypeId());

        CustomerCar car = new CustomerCar();
        car.setLicenseNumber(dto.getLicenseNumber());
        car.setPlate(dto.getPlate());
        car.setCreatedAt(LocalDateTime.now());
        car.setCustomer(customer);
        car.setCarType(carType);
        car.setCustomer(customer);

        carRepository.save(car);
        return toDTO(car);
    }

    // public List<CustomerCar> getCarsByCustomerToken(String token) {
    //     return carRepository.findByCustomer_Token(token);
    // }

    public List<CustomerCarDTO> getCarsByCustomerId(int id) {
        return carRepository.findByCustomer_Id(id)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CustomerCarDTO getCarById(int id) {
        CustomerCar car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return toDTO(car);
    }

    // public CustomerCar getCarByToken(String token) {
    //     return carRepository.findByToken(token)
    //             .orElseThrow(() -> new RuntimeException("Car not found"));
    // }
//change to id temporarily
    public CustomerCarDTO updateCar(int id, CustomerCar updatedCar) {
        // 1. Get the existing entity
        CustomerCar car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));

        // 2. Update fields
        if (updatedCar.getLicenseNumber() != null) {
            car.setLicenseNumber(updatedCar.getLicenseNumber());
        }
        if (updatedCar.getPlate() != null) {
            car.setPlate(updatedCar.getPlate());
        }
        if (updatedCar.getCarType() != null) {
            car.setCarType(updatedCar.getCarType());
        }

        return toDTO(carRepository.save(car));
    }

    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }

    // public void deleteCarByToken(String token) {
    //     CustomerCar car = getCarByToken(token);
    //     carRepository.delete(car);
    // }

    public CustomerCarDTO searchByPlate(String plate) {
        CustomerCar car = carRepository.findByPlate(plate)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return toDTO(car);
    }

    private CustomerCarDTO toDTO(CustomerCar car) {
        return new CustomerCarDTO(
                car.getCustomer().getId(),
                car.getCarId(),
                car.getLicenseNumber(),
                car.getPlate(),
                car.getCarType() != null ? car.getCarType().getCarTypeId() : null,
                car.getCreatedAt()
        );
    }

}
