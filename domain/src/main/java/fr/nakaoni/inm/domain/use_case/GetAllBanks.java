package fr.nakaoni.inm.domain.use_case;

import fr.nakaoni.inm.domain.entity.Bank;

import java.util.Set;

public class GetAllBanks {
    private final BankPort bankPort;

    public GetAllBanks(BankPort bankPort) {
        this.bankPort = bankPort;
    }

    public Set<Bank> execute() {
        return bankPort.getAll();
    }
}
