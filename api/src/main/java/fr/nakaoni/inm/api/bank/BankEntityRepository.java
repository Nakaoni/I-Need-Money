package fr.nakaoni.inm.api.bank;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankEntityRepository extends JpaRepository<BankEntity, Long> {
}
