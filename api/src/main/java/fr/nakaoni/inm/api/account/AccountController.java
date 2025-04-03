package fr.nakaoni.inm.api.account;

import fr.nakaoni.inm.domain.account.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/api/v1/accounts")
    public Set<Account> all() {
        Set<Account> accounts = new HashSet<>();
        accountRepository.findAll().forEach(accounts::add);

        return accounts;
    }

    @PostMapping("/api/v1/accounts")
    public ResponseEntity<Account> create(@RequestBody Account createAccountRequest) {
        Account account = new Account(createAccountRequest.name(), createAccountRequest.bank(), createAccountRequest.type());

        account = accountRepository.save(account);

        URI accountUri = showUri(account.id());

        return ResponseEntity.created(accountUri).contentType(MediaType.APPLICATION_JSON).body(account);
    }

    URI showUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @GetMapping("/api/v1/accounts/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(accountRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @PatchMapping("/api/v1/accounts/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AccountDto accountDto) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        account.setName(accountDto.name());
        accountRepository.save(account);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/api/v1/accounts/{id}")
    public ResponseEntity<?> remove(@PathVariable(value = "id") Long id) {

        Optional<Account> account = accountRepository.findById(id);

        account.ifPresent(accountRepository::delete);

        return ResponseEntity
                .accepted()
                .build();
    }
}
