package org.os.carcareservice.controller;

import java.util.List;

import org.os.carcareservice.dto.CustomerCarDTO;
import org.os.carcareservice.entity.CustomerCar;
import org.os.carcareservice.service.CustomerCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CustomerCarController {

    private final CustomerCarService carService;

    public CustomerCarController(CustomerCarService carService) {
        this.carService = carService;
    }

    // POST /customers/{token}/cars
    // @PostMapping("/customers/{token}/cars")
    // public CustomerCar addCar(@PathVariable String token, @RequestBody CustomerCar car) {
    //     return carService.addCar(token, car);
    // }

    @PostMapping("/customers/{id}/cars")
    public CustomerCar addCarByCustomerId(@PathVariable Long id, @RequestBody CustomerCar car) {
        return carService.addCarByCustomerId(id, car);
    }

    // GET /customers/{token}/cars
    // @GetMapping("/customers/{token}/cars")
    // public List<CustomerCar> getCarsByCustomerToken(@PathVariable String token) {
    //     return carService.getCarsByCustomerToken(token);
    // }

    // GET /customers/{id}/cars
    @GetMapping("/customers/{id}/cars")
    public List<CustomerCar> getCarsByCustomerId(@PathVariable Integer id) {
        return carService.getCarsByCustomerId(id);
    }

    // GET /cars/{id}
    @GetMapping("/{id}")
    public CustomerCar getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    // GET /cars/{token}
    // @GetMapping("/token/{token}")
    // public CustomerCar getCarByToken(@PathVariable String token) {
    //     return carService.getCarByToken(token);
    // }

    // // PUT /cars/{token}
    // @PutMapping("/{token}")
    // public CustomerCar updateCar(@PathVariable String token, @RequestBody CustomerCar updatedCar) {
    //     return carService.updateCar(token, updatedCar);
    // }

    // DELETE /cars/{id}
    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }

    // DELETE /cars/{token}
    // @DeleteMapping("/token/{token}")
    // public void deleteCarByToken(@PathVariable String token) {
    //     carService.deleteCarByToken(token);
    // }

    // GET /cars/search?plate={plate}
    @GetMapping("/search")
    public CustomerCar searchCarByPlate(@RequestParam String plate) {
        return carService.searchByPlate(plate);
    }

}
