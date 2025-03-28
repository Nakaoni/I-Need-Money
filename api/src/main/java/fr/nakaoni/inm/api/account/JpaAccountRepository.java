package fr.nakaoni.inm.api.account;

import fr.nakaoni.inm.domain.account.Account;
import fr.nakaoni.inm.domain.account.AccountRepository;
import fr.nakaoni.inm.domain.bank.Bank;
import fr.nakaoni.inm.domain.bank.BankRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaAccountRepository implements AccountRepository {
    private final AccountEntityRepository repository;

    public JpaAccountRepository(AccountEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id).map(AccountEntity::toDomain);
    }

    @Override
    public List<Account> findByBank(Bank bank) {
        List<Account> accounts = new ArrayList<>();

        repository.findAll().forEach((AccountEntity account) -> {
            if (account.bank().toDomain().equals(bank)) {
                accounts.add(account.toDomain());
            }
        });

        return accounts;
    }

    @Override
    public Account save(Account account) {
        return repository.save(AccountEntity.fromDomain(account)).toDomain();
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        repository.findAll().forEach(a -> accounts.add(a.toDomain()));

        return accounts;
    }

    @Override
    public void remove(Account account) {
        repository.delete(AccountEntity.fromDomain(account));
    }
}
