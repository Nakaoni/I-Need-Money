package fr.nakaoni.ineedmoney.Client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ClientController {

    @PostMapping("/clients")
    ResponseEntity<Client> registerNewClient() {
        Client newClient = Client.register();

        URI newClientLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newClient.id())
                .toUri();

        return ResponseEntity.created(newClientLocation).body(newClient);
    }

}
