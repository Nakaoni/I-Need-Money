package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.bank.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank, Long>, fr.nakaoni.inm.domain.bank.BankRepository {
}
