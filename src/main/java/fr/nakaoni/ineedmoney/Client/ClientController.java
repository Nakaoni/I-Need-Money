package fr.nakaoni.ineedmoney.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClientController {

    @PostMapping("/clients")
    ResponseEntity<Client> registerNewClient() {
        Client client = Client.register();
        return ResponseEntity.created(null).body(client);
    }

}
