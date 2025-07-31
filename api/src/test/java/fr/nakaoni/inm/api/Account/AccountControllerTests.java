package fr.nakaoni.inm.api.Account;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTests {
/*
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankRepository bankRepository;

    @Test
    @DirtiesContext
    void show() throws Exception {
        Bank bank = new Bank(null, "Boursorama");
        bankRepository.save(bank);

        Account account = new Account(null,"Compte Courant", bank, Account.AccountType.CHECKING);
        accountRepository.save(account);

        Account expectedAccount = new Account(null,"Compte Courant", bank, Account.AccountType.CHECKING);
        String expectedAccountJson = objectMapper.writeValueAsString(expectedAccount);

        mvc.perform(
                        MockMvcRequestBuilders
                                .request(HttpMethod.GET, "/api/v1/accounts/1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedAccountJson))
        ;
    }

    @Test
    @DirtiesContext
    void create() throws Exception {
        Bank bank = new Bank(null, "BforBank");
        String bankJson = objectMapper.writeValueAsString(bank);

        Account account = new Account(null, "Compte Courant", bank, Account.AccountType.CHECKING);
        String accountJson = objectMapper.writeValueAsString(account);

        mvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/accounts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bankJson)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(accountJson))
                .andExpect(header().string(
                        HttpHeaders.LOCATION,
                        fromMethodName(AccountController.class, "show", account.id())
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
                                .get("/api/v1/accounts")
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
                                .patch("/api/v1/accounts/1")
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
                                .delete("/api/v1/accounts/1")
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
                                .delete("/api/v1/accounts/666")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
        ;
    }
    */
}