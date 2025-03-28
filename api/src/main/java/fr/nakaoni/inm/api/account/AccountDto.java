package fr.nakaoni.inm.api.account;

import fr.nakaoni.inm.domain.account.Account;
import fr.nakaoni.inm.domain.bank.Bank;

public record AccountDto(String name, Bank bank, Account.AccountType type) {}
