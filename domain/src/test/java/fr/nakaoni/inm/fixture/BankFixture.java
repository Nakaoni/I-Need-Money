package fr.nakaoni.inm.fixture;

import fr.nakaoni.inm.domain.entity.Bank;

public class BankFixture {
    public static Bank aBank() {
        return new Bank(1L, "bank_1");
    }
}
