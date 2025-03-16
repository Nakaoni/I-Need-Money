package fr.nakaoni.inm.api.bank;

import fr.nakaoni.inm.domain.bank.BankRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    private final BankRepository bankRepository;

    public BankController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @PostMapping("/api/v1/banks")
    public ResponseEntity<BankEntity> create(@RequestBody BankEntity createBankRequest) {
        BankEntity bank = new BankEntity(createBankRequest.name());

        bankRepository.save(bank.toDomain());

        return ResponseEntity.created(null).contentType(MediaType.APPLICATION_JSON).body(bank);
    }
}
