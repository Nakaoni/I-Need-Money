package fr.nakaoni.inm.api.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

@Sql(scripts = "classpath:reset-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankEntityRepository bankEntityRepository;

    @BeforeEach
    void setup() throws Exception {
        BankEntity boursoramaBankEntity = new BankEntity("Boursorama");
        bankEntityRepository.save(boursoramaBankEntity);

        BankEntity fortuneoBankEntity = new BankEntity("Fortuneo");
        bankEntityRepository.save(fortuneoBankEntity);
    }

    @Test
    void show() throws Exception {
        BankEntity bankEntity = new BankEntity(1L, "Boursorama");

        String bankEntityJson = objectMapper.writeValueAsString(bankEntity);

        mvc.perform(
                        MockMvcRequestBuilders
                                .request(HttpMethod.GET, "/api/v1/banks/1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(bankEntityJson))
        ;
    }

    @Test
    void create() throws Exception {
        BankEntity bankEntity = new BankEntity("BforBank");
        String bankEntityJson = objectMapper.writeValueAsString(bankEntity);

        BankEntity expectedBankEntity = new BankEntity(3L, "BforBank");
        String expectedBankEntityJson = objectMapper.writeValueAsString(expectedBankEntity);

        mvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/banks")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bankEntityJson)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedBankEntityJson))
                .andExpect(header().string(
                        HttpHeaders.LOCATION,
                        fromMethodName(BankController.class, "show", expectedBankEntity.id())
                                .build()
                                .toUriString()
                ))
        ;
    }

    @Test
    void all() throws Exception {
        List<BankEntity> expectedBankEntities = List.of(
                new BankEntity(1L, "Boursorama"),
                new BankEntity(2L, "Fortuneo")
        );
        String expectedBankEntitiesJson = objectMapper.writeValueAsString(expectedBankEntities);

        mvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/banks")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedBankEntitiesJson))
        ;
    }
}