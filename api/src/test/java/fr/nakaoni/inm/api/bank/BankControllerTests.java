package fr.nakaoni.inm.api.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankEntityRepository bankEntityRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
    }

    @Test
    @DirtiesContext
    void show() throws Exception {
        BankEntity boursoramaBankEntity = new BankEntity("Boursorama");
        bankEntityRepository.save(boursoramaBankEntity);

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
    @DirtiesContext
    void create() throws Exception {
        BankEntity bankEntity = new BankEntity("BforBank");
        String bankEntityJson = objectMapper.writeValueAsString(bankEntity);

        BankEntity expectedBankEntity = new BankEntity(1L, "BforBank");
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
    @DirtiesContext
    void all() throws Exception {
        BankEntity boursoramaBankEntity = new BankEntity("Boursorama");
        bankEntityRepository.save(boursoramaBankEntity);

        BankEntity fortuneoBankEntity = new BankEntity("Fortuneo");
        bankEntityRepository.save(fortuneoBankEntity);

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

    @Test
    @DirtiesContext
    void update() throws Exception {
        BankEntity boursoramaBankEntity = new BankEntity("Crédit Agricole");
        bankEntityRepository.save(boursoramaBankEntity);

        BankEntity expectedBankEntity = new BankEntity(1L, "Boursorama");

        String content = "{\"name\": \"Boursorama\"}";

        mvc.perform(
                        MockMvcRequestBuilders
                                .patch("/api/v1/banks/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andExpect(status().isNoContent())
        ;

        assertEquals(expectedBankEntity, bankEntityRepository.findById(1L).orElseThrow());
    }

    @Test
    @DirtiesContext
    void remove() throws Exception {
        BankEntity boursoramaBankEntity = new BankEntity("Crédit Agricole");
        bankEntityRepository.save(boursoramaBankEntity);

        mvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/banks/1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
        ;
    }

    @Test
    @DirtiesContext
    void removeNonExisting() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/v1/banks/666")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
        ;
    }
}