package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.bank.Bank;
import fr.nakaoni.inm.domain.bank.BankRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaBankRepository implements BankRepository {
    private final BankEntityRepository repository;

    public JpaBankRepository(BankEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Bank> findById(Long id) {
        return repository.findById(id).map(BankEntity::toDomain);
    }

    @Override
    public void save(Bank bank) {
        repository.save(BankEntity.fromDomain(bank));
    }
}
