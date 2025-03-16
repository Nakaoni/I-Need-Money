package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.bank.BankRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        bankRepository.save(bank.toDomain());

        return ResponseEntity.created(null).contentType(MediaType.APPLICATION_JSON).body(bank);
    }

    @GetMapping("/api/v1/banks/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.ok(bankRepository.findById(id).map(BankEntity::fromDomain).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
