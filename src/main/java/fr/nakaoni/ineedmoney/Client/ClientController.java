package fr.nakaoni.ineedmoney.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @PostMapping("/clients")
    ResponseEntity<String> registerNewClient() {
        return ResponseEntity.created(null).body("");
    }

}
