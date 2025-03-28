package fr.nakaoni.inm.api.transaction;

import org.springframework.data.repository.CrudRepository;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, Long> {
}
