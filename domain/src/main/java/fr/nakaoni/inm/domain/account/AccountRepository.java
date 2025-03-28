package fr.nakaoni.inm.domain.account;

import fr.nakaoni.inm.domain.bank.Bank;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(Long id);
    List<Account> findByBank(Bank bank);
    List<Account> findAll();
    Account save(Account account);
    void remove(Account account);
}
