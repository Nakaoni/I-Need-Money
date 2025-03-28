package fr.nakaoni.inm.domain.account;

import fr.nakaoni.inm.domain.bank.Bank;

import java.util.Optional;
import java.util.Set;

public interface AccountRepository {
    Optional<Account> findById(Long id);
    Set<Account> findByBank(Bank bank);
    Set<Account> findAll();
    Account save(Account account);
    void remove(Account account);
}
