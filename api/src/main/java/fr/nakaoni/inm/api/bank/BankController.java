package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.entity.Bank;
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
public class BankController {
    private final BankRepository bankRepository;

    public BankController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @GetMapping("/api/v1/banks")
    public Set<Bank> all() {
        Set<Bank> banks = new HashSet<>();
        bankRepository.findAll().forEach(banks::add);
        return banks;
    }

    @PostMapping("/api/v1/banks")
    public ResponseEntity<Bank> create(@RequestBody Bank createBankRequest) {
        Bank bank = new Bank(createBankRequest.name());

        bank = bankRepository.save(bank);

        URI bankUri = showUri(bank.id());

        return ResponseEntity.created(bankUri).contentType(MediaType.APPLICATION_JSON).body(bank);
    }

    URI showUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @GetMapping("/api/v1/banks/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(bankRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }

    @PatchMapping("/api/v1/banks/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody BankDto bankDto) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        bank.setName(bankDto.name());
        bankRepository.save(bank);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/api/v1/banks/{id}")
    public ResponseEntity<?> remove(@PathVariable(value = "id") Long id) {

        Optional<Bank> bank = bankRepository.findById(id);

        bank.ifPresent(bankRepository::delete);

        return ResponseEntity
                .accepted()
                .build();
    }
}
