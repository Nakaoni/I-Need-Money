package fr.nakaoni.inm.api.account;

import fr.nakaoni.inm.api.bank.BankEntity;
import fr.nakaoni.inm.domain.account.Account;

public record AccountEntityDto(String name, BankEntity bank, Account.AccountType type) {}
