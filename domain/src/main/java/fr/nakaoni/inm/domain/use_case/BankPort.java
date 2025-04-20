package fr.nakaoni.inm.domain.use_case;

import fr.nakaoni.inm.domain.entity.Bank;

import java.util.Set;

public interface BankPort {
    Set<Bank> getAll();

    void save(Bank bank);
}
