package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.bank.Bank;
import fr.nakaoni.inm.domain.bank.BankRepository;
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

    @PatchMapping("/api/v1/banks/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody BankEntityDto bankEntityDto) {
        Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        bank.setName(bankEntityDto.name());
        bankRepository.save(bank);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/api/v1/banks/{id}")
    public ResponseEntity<?> remove(@PathVariable(value = "id") Long id) {

        Optional<Bank> bank = bankRepository.findById(id);

        bank.ifPresent(bankRepository::remove);

        return ResponseEntity
                .accepted()
                .build();
    }
}
