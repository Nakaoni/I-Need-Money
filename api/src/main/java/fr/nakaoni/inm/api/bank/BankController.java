package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.bank.BankRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class BankController {
    private final BankRepository bankRepository;

    public BankController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @GetMapping("/api/v1/banks")
    public List<BankEntity> all() {
        return bankRepository.findAll()
                .stream()
                .map(BankEntity::fromDomain)
                .toList();
    }

    @PostMapping("/api/v1/banks")
    public ResponseEntity<BankEntity> create(@RequestBody BankEntity createBankRequest) {
        BankEntity bank = new BankEntity(createBankRequest.name());

        bank = BankEntity.fromDomain(bankRepository.save(bank.toDomain()));

        URI bankUri = bankUri(bank.id());

        return ResponseEntity.created(bankUri).contentType(MediaType.APPLICATION_JSON).body(bank);
    }

    URI bankUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @GetMapping("/api/v1/banks/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(bankRepository
                .findById(id)
                .map(BankEntity::fromDomain)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }
}
