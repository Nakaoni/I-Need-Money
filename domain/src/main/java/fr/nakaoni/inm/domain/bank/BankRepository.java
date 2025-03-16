package fr.nakaoni.inm.domain.bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository {
    Optional<Bank> findById(Long id);
    List<Bank> findAll();
    void save(Bank bank);
}
