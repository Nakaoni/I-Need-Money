package fr.nakaoni.inm.api;

import fr.nakaoni.inm.api.bank.BankEntity;
import fr.nakaoni.inm.domain.bank.BankRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {
    public static void main(String... args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankRepository bankRepository) {
        return args -> {
            bankRepository.save(new BankEntity("Boursorama").toDomain());
        };
    }
}