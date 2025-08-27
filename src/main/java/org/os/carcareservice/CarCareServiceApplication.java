package org.os.carcareservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarCareServiceApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        System.setProperty("DB_URL" , dotenv.get("DB_URL"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("API_PORT", dotenv.get("API_PORT"));
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
        System.setProperty("JWT_EXPIRATION", dotenv.get("JWT_EXPIRATION"));

        SpringApplication.run(CarCareServiceApplication.class, args);
    }

}
