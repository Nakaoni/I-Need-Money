package fr.nakaoni.inm.api.bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    @PostMapping("/api/v1/banks")
    public ResponseEntity<BankEntity> create(@RequestBody BankEntity createBankRequest) {
        BankEntity bank = new BankEntity(createBankRequest.name());

        return ResponseEntity.created(null).body(bank);
    }
}
