package fr.nakaoni.inm.api.transaction;

import fr.nakaoni.inm.api.bank.BankEntity;
import fr.nakaoni.inm.domain.account.Account;

public record TransactionEntityDto(String name, BankEntity bank, Account.AccountType type) {}
