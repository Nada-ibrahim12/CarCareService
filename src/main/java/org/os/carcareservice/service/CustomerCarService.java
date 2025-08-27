package org.os.carcareservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public CustomerCar addCarByCustomerId(Long customerId, CustomerCar car) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        car.setCustomer(customer);
        return carRepository.save(car);
    }

    // public List<CustomerCar> getCarsByCustomerToken(String token) {
    //     return carRepository.findByCustomer_Token(token);
    // }

    public List<CustomerCar> getCarsByCustomerId(int id) {
        return carRepository.findByCustomer_Id(id);
    }

    public CustomerCar getCarById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    // public CustomerCar getCarByToken(String token) {
    //     return carRepository.findByToken(token)
    //             .orElseThrow(() -> new RuntimeException("Car not found"));
    // }
//change to id temporarily
    public CustomerCar updateCar(int id, CustomerCar updatedCar) {
        // 1. Get the existing car by token
        CustomerCar car = getCarById(id);

        if (car == null) {
            throw new RuntimeException("Car not found with token: " + id);
        }

        // 2. Update fields
        if (updatedCar.getLicenseNumber() != null) {
            car.setLicenseNumber(updatedCar.getLicenseNumber());
        }

        if (updatedCar.getCarType() != null) {
            car.setCarType(updatedCar.getCarType());
        }

        return carRepository.save(car);
    }

    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }

    // public void deleteCarByToken(String token) {
    //     CustomerCar car = getCarByToken(token);
    //     carRepository.delete(car);
    // }

    public CustomerCar searchByPlate(String plate) {
        return carRepository.findByPlate(plate)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }
}
