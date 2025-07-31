package fr.nakaoni.inm.api.bank;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTests {

//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private BankRepository bankRepository;
//
//    @Test
//    @DirtiesContext
//    void show() throws Exception {
//        Bank boursoramaBankEntity = new Bank(null, "Boursorama");
//        bankRepository.save(boursoramaBankEntity);
//
//        Bank bank = new Bank(null, "Boursorama");
//        String bankJson = objectMapper.writeValueAsString(bank);
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .request(HttpMethod.GET, "/api/v1/banks/1")
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(bankJson))
//        ;
//    }
//
//    @Test
//    @DirtiesContext
//    void create() throws Exception {
//        Bank bank = new Bank(null, "BforBank");
//        String bankJson = objectMapper.writeValueAsString(bank);
//
//        Bank expectedBank = new Bank(null, "BforBank");
//        String expectedBankJson = objectMapper.writeValueAsString(expectedBank);
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .post("/api/v1/banks")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(bankJson)
//                )
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(expectedBankJson))
//                .andExpect(header().string(
//                        HttpHeaders.LOCATION,
//                        fromMethodName(BankController.class, "show", 1L)
//                                .build()
//                                .toUriString()
//                ))
//        ;
//    }
//
//    @Test
//    @DirtiesContext
//    void all() throws Exception {
//        Bank bank1 = new Bank(null, "Boursorama");
//        bankRepository.save(bank1);
//
//        Bank bank2 = new Bank(null, "Fortuneo");
//        bankRepository.save(bank2);
//
//        Bank expectedBank1 = new Bank(null, "Boursorama");
//        Bank expectedBank2 = new Bank(null, "Fortuneo");
//
//        List<Bank> expectedBanks = List.of(expectedBank1, expectedBank2);
//        String expectedBanksJson = objectMapper.writeValueAsString(expectedBanks);
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .get("/api/v1/banks")
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(expectedBanksJson))
//        ;
//    }
//
//    @Test
//    @DirtiesContext
//    void update() throws Exception {
//        Bank bank = new Bank(null, "Crédit Agricole");
//        bankRepository.save(bank);
//
//        Bank expectedBank = new Bank(null, "Boursorama");
//
//        String content = "{\"name\": \"Boursorama\"}";
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .patch("/api/v1/banks/1")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                )
//                .andExpect(status().isNoContent())
//        ;
//
//        assertEquals(expectedBank, bankRepository.findById(1L).orElseThrow());
//    }
//
//    @Test
//    @DirtiesContext
//    void remove() throws Exception {
//        Bank bank = new Bank(null, "Crédit Agricole");
//        bankRepository.save(bank);
//
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .delete("/api/v1/banks/1")
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
//                                .delete("/api/v1/banks/666")
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isAccepted())
//        ;
//    }
}