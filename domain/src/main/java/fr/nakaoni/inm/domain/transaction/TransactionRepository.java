package fr.nakaoni.inm.domain.transaction;

import fr.nakaoni.inm.domain.account.Account;

import java.util.Optional;
import java.util.Set;

public interface TransactionRepository {
    Optional<Transaction> findById(Long id);
    Set<Transaction> findByAccount(Account account);
    Transaction save(Transaction transaction);
    Set<Transaction> findAll();
    void remove(Transaction transaction);
}
