package org.os.carcareservice.controller;

import java.util.List;

import org.os.carcareservice.dto.CustomerCarDTO;
import org.os.carcareservice.entity.CustomerCar;
import org.os.carcareservice.entity.User;
import org.os.carcareservice.service.CustomerCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerCarController {

    private final CustomerCarService carService;

    public CustomerCarController(CustomerCarService carService) {
        this.carService = carService;
    }

    // POST /customers/{token}/cars
//     @PostMapping("/customers/{token}/cars")
//     public CustomerCar addCar(@PathVariable String token, @RequestBody CustomerCar car) {
//         return carService.addCar(token, car);
//     }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/customers/cars")
    public CustomerCarDTO addCarByCustomerId(Authentication authentication, @RequestBody CustomerCarDTO car) {
        User currentUser = (User) authentication.getPrincipal();
        Long userId = currentUser.getId();
        return carService.addCarByCustomerId(userId, car);
    }

    // GET /customers/{token}/cars
    // @GetMapping("/customers/{token}/cars")
    // public List<CustomerCar> getCarsByCustomerToken(@PathVariable String token) {
    //     return carService.getCarsByCustomerToken(token);
    // }

    @PreAuthorize("hasRole('CUSTOMER')")
    // GET /customers/{id}/cars
    @GetMapping("/customers/cars")
    public List<CustomerCarDTO> getCarsByCustomerId(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        int userId = Math.toIntExact(currentUser.getId());
        return carService.getCarsByCustomerId(userId);
    }

    // GET /cars/{id}
    @GetMapping("/car/{id}")
    public CustomerCarDTO getCarById(@PathVariable Integer id) {
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
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/car/{id}")
    public void deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }

    // DELETE /cars/{token}
    // @DeleteMapping("/token/{token}")
    // public void deleteCarByToken(@PathVariable String token) {
    //     carService.deleteCarByToken(token);
    // }

    // GET /cars/search?plate={plate}
    @GetMapping("/car/search")
    public CustomerCarDTO searchCarByPlate(@RequestParam String plate) {
        return carService.searchByPlate(plate);
    }

}
