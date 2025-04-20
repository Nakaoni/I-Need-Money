package fr.nakaoni.inm.domain.use_case;

import fr.nakaoni.inm.domain.entity.Bank;
import fr.nakaoni.inm.fixture.BankFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class GetAllBanksTest {
    @InjectMocks
    GetAllBanks getAllBanks;

    @Mock
    BankPort bankPort;

    @Test
    void execute_shouldReturnBanksFromProvider() {
        // Given
        Set<Bank> expectedBanks = Sets.newSet(BankFixture.aBank());
        doReturn(expectedBanks).when(bankPort).getAll();

        // When
        Set<Bank> actualBanks = getAllBanks.execute();

        // Then
        assertSame(expectedBanks, actualBanks);
    }
}
