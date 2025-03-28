package fr.nakaoni.inm.api.account;

import fr.nakaoni.inm.domain.account.Account;
import fr.nakaoni.inm.domain.account.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/api/v1/accounts")
    public List<AccountEntity> all() {
        return accountRepository.findAll()
                .stream()
                .map(AccountEntity::fromDomain)
                .toList();
    }

    @PostMapping("/api/v1/accounts")
    public ResponseEntity<AccountEntity> create(@RequestBody AccountEntity createAccountRequest) {
        AccountEntity account = new AccountEntity(createAccountRequest.name(), createAccountRequest.bank(), createAccountRequest.type());

        account = AccountEntity.fromDomain(accountRepository.save(account.toDomain()));

        URI accountUri = AccountUri(account.id());

        return ResponseEntity.created(accountUri).contentType(MediaType.APPLICATION_JSON).body(account);
    }

    URI AccountUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @GetMapping("/api/v1/accounts/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(accountRepository
                .findById(id)
                .map(AccountEntity::fromDomain)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @PatchMapping("/api/v1/accounts/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AccountEntityDto accountEntityDto) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        account.setName(accountEntityDto.name());
        accountRepository.save(account);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/api/v1/accounts/{id}")
    public ResponseEntity<?> remove(@PathVariable(value = "id") Long id) {

        Optional<Account> account = accountRepository.findById(id);

        account.ifPresent(accountRepository::remove);

        return ResponseEntity
                .accepted()
                .build();
    }
}
