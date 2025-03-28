package fr.nakaoni.inm.domain.bank;

import java.util.Set;
import java.util.Optional;

public interface BankRepository {
    Optional<Bank> findById(Long id);
    Set<Bank> findAll();
    Bank save(Bank bank);
    void remove(Bank bank);
}
