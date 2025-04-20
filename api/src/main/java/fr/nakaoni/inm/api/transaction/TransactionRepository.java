package fr.nakaoni.inm.api.transaction;

import fr.nakaoni.inm.domain.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
