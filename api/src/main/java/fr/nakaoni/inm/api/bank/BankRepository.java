package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.entity.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank, Long> {
}
