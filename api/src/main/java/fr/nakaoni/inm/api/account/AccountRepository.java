package fr.nakaoni.inm.api.account;

import fr.nakaoni.inm.domain.account.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
