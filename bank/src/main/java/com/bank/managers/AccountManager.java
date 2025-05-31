package com.bank.managers;

import com.bank.Account;
import com.bank.validators.AccountValidator;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {

    private final Map<String, Account> accounts = new HashMap<>();

    public void createAccount(String accountNumber, Double amount) {
        Account newAccount = new Account(accountNumber, amount);

        AccountValidator.selfValidate(newAccount);

        setAccount(newAccount.getAccountNumber(), newAccount);
    }

    public Account getAccount(String accountNumber) {
        Account account = this.accounts.get(accountNumber);

        if(account == null) {
            throw new IllegalArgumentException("Account do not exists");
        }

        return account;
    }

    public Boolean accountExists(String accountNumber) {
        return this.accounts.containsKey(accountNumber);
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccount(String accountNumber, Account newAccount) {
        if(Boolean.TRUE.equals(accountExists(accountNumber))) {
            throw new IllegalStateException("Account already exists");
        }

        this.accounts.put(accountNumber, newAccount);
    }
}
