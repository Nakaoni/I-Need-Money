package fr.nakaoni.inm.domain.transaction;

import fr.nakaoni.inm.domain.account.Account;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> findById(Long id);

    List<Transaction> findByAccount(Account account);

    void save(Transaction transaction);
}
