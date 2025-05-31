package com.bank.validators;

import com.bank.Account;


public abstract class AccountValidator {

    private AccountValidator() {}

    public static void selfValidate(Account account) {
        validateAccountNumber(account);
        validateAmount(account);
    }

    public static void validateAccountNumber(Account account) {
        if(account.getAccountNumber() == null
                || account.getAccountNumber().trim().isEmpty() || Long.parseLong(account.getAccountNumber()) <= 0) {
            throw new IllegalArgumentException("Account number cannot be null.");
        }
    }

    public static void validateAmount(Account account) {
        if(account.getBalance() == null) {
            throw new IllegalArgumentException("Account balance cannot be null.");
        }
        if(account.getBalance() < 0.0) {
            throw new IllegalArgumentException("Account balance cannot be negative.");
        }
    }
}
