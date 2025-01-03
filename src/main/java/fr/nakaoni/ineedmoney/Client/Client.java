package fr.nakaoni.ineedmoney.Client;

import java.util.UUID;

public record Client(UUID id) {
    public static Client register() {
        return new Client(UUID.randomUUID());
    }
}
