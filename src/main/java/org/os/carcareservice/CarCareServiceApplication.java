package org.os.carcareservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CarCareServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarCareServiceApplication.class, args);
    }

}
