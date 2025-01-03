package fr.nakaoni.ineedmoney.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientTests {

    @Value(value = "${local.server.port}")
    private int port;

    @Test void givenIAmAClient_WhenIRegister() throws Exception {
        ResponseSpec response = WebTestClient
            .bindToServer()
                .baseUrl("http://localhost:" + port)
                .build()
            .post()
                .uri("/clients")
            .exchange();

        itShouldRegisterANewClient(response);
        itShouldAllocateANewId(response);
    }

    private void itShouldRegisterANewClient(ResponseSpec response) {
       response
           .expectStatus()
           .isCreated();
    }

    private void itShouldAllocateANewId(ResponseSpec response) {
        response
            .expectBody(ClientResponse.class)
            .value(client -> {
                assertThat(client.id()).isNotEqualTo(new UUID(0, 0));
                assertThat(client.id()).isNotNull();
            });
    }
}
