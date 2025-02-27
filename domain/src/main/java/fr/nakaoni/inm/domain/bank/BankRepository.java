package fr.nakaoni.inm.domain.bank;

import java.util.Optional;

public interface BankRepository {
    Optional<Bank> findById(Long id);
    void save(Bank bank);
}
