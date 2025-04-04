package fr.nakaoni.inm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"fr.nakaoni.inm.api", "fr.nakaoni.inm.domain"})
@ComponentScan({"fr.nakaoni.inm.api", "fr.nakaoni.inm.domain"})
public class ApiApplication {
    public static void main(String... args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}