package fr.nakaoni.inm.api.Account;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.nakaoni.inm.api.account.AccountEntity;
import fr.nakaoni.inm.api.account.AccountEntityRepository;
import fr.nakaoni.inm.api.bank.BankEntity;
import fr.nakaoni.inm.api.bank.BankEntityRepository;
import fr.nakaoni.inm.domain.account.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountEntityRepository accountEntityRepository;

    @Autowired
    private BankEntityRepository bankEntityRepository;

    @Test
    @DirtiesContext
    void show() throws Exception {
        BankEntity boursoramaBankEntity = new BankEntity("Boursorama");
        bankEntityRepository.save(boursoramaBankEntity);

        AccountEntity accountEntity = new AccountEntity("Compte Couant", boursoramaBankEntity, Account.AccountType.CHECKING);
        accountEntityRepository.save(accountEntity);

        AccountEntity expectedAccountEntity = new AccountEntity(1L, "Compte Couant", boursoramaBankEntity, Account.AccountType.CHECKING);
        String expectedAccountEntityJson = objectMapper.writeValueAsString(expectedAccountEntity);

        mvc.perform(
                        MockMvcRequestBuilders
                                .request(HttpMethod.GET, "/api/v1/accounts/1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedAccountEntityJson))
        ;
    }

//    @Test
//    @DirtiesContext
//    void create() throws Exception {
//        BankEntity bankEntity = new BankEntity("BforBank");
//        String bankEntityJson = objectMapper.writeValueAsString(bankEntity);
//
//        BankEntity expectedBankEntity = new BankEntity(1L, "BforBank");
//        String expectedBankEntityJson = objectMapper.writeValueAsString(expectedBankEntity);
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .post("/api/v1/accounts")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(bankEntityJson)
//                )
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(expectedBankEntityJson))
//                .andExpect(header().string(
//                        HttpHeaders.LOCATION,
//                        fromMethodName(BankController.class, "show", expectedBankEntity.id())
//                                .build()
//                                .toUriString()
//                ))
//        ;
//    }
//
//    @Test
//    @DirtiesContext
//    void all() throws Exception {
//        BankEntity boursoramaBankEntity = new BankEntity("Boursorama");
//        bankEntityRepository.save(boursoramaBankEntity);
//
//        BankEntity fortuneoBankEntity = new BankEntity("Fortuneo");
//        bankEntityRepository.save(fortuneoBankEntity);
//
//        List<BankEntity> expectedBankEntities = List.of(
//                new BankEntity(1L, "Boursorama"),
//                new BankEntity(2L, "Fortuneo")
//        );
//        String expectedBankEntitiesJson = objectMapper.writeValueAsString(expectedBankEntities);
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .get("/api/v1/accounts")
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(expectedBankEntitiesJson))
//        ;
//    }
//
//    @Test
//    @DirtiesContext
//    void update() throws Exception {
//        BankEntity boursoramaBankEntity = new BankEntity("Crédit Agricole");
//        bankEntityRepository.save(boursoramaBankEntity);
//
//        BankEntity expectedBankEntity = new BankEntity(1L, "Boursorama");
//
//        String content = "{\"name\": \"Boursorama\"}";
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .patch("/api/v1/accounts/1")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                )
//                .andExpect(status().isNoContent())
//        ;
//
//        assertEquals(expectedBankEntity, bankEntityRepository.findById(1L).orElseThrow());
//    }
//
//    @Test
//    @DirtiesContext
//    void remove() throws Exception {
//        BankEntity boursoramaBankEntity = new BankEntity("Crédit Agricole");
//        bankEntityRepository.save(boursoramaBankEntity);
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .delete("/api/v1/accounts/1")
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isAccepted())
//        ;
//    }
//
//    @Test
//    @DirtiesContext
//    void removeNonExisting() throws Exception {
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .delete("/api/v1/accounts/666")
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isAccepted())
//        ;
//    }
}