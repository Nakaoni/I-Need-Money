package fr.nakaoni.inm.api.transaction;

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
public class TransactionController {
    private final AccountRepository accountRepository;

    public TransactionController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/api/v1/accounts")
    public List<TransactionEntity> all() {
        return accountRepository.findAll()
                .stream()
                .map(TransactionEntity::fromDomain)
                .toList();
    }

    @PostMapping("/api/v1/accounts")
    public ResponseEntity<TransactionEntity> create(@RequestBody TransactionEntity createAccountRequest) {
        TransactionEntity account = new TransactionEntity(createAccountRequest.name(), createAccountRequest.bank(), createAccountRequest.type());

        account = TransactionEntity.fromDomain(accountRepository.save(account.toDomain()));

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
                .map(TransactionEntity::fromDomain)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @PatchMapping("/api/v1/accounts/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TransactionEntityDto accountEntityDto) {
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
