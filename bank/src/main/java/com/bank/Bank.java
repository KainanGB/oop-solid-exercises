package com.bank;

import com.bank.enums.TransactionType;
import com.bank.managers.AccountManager;
import com.bank.managers.TransactionManager;
import com.bank.validators.AccountValidator;

import java.time.LocalDateTime;

public class Bank {

    private final AccountManager accountManager;
    private final TransactionManager transactionManager;

    public Bank(AccountManager accountManager, TransactionManager transactionManager) {
         this.accountManager = accountManager;
         this.transactionManager = transactionManager;
    }

    public void deposit(String accountNumber,
                        Double amount,
                        String description,
                        LocalDateTime date) {

        AccountValidator.selfValidate(accountManager.getAccount(accountNumber));

        Transaction transaction = new Transaction(accountNumber,
                 amount,
                 description,
                 null,
                 TransactionType.DEPOSIT,
                 date);

        accountManager.getAccount(accountNumber).processTransaction(transaction);
        transactionManager.setTransaction(accountNumber, transaction);
    }

    public void withdraw(String accountNumber,
                        Double amount,
                        String description,
                        LocalDateTime date) {
        AccountValidator.selfValidate(accountManager.getAccount(accountNumber));

        Transaction transaction = new Transaction(accountNumber,
                amount,
                description,
                null,
                TransactionType.WITHDRAW,
                date);

        accountManager.getAccount(accountNumber).processTransaction(transaction);
        transactionManager.setTransaction(accountNumber, transaction);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber,
                         Double amount,
                         String description,
                         LocalDateTime date) {
        Account fromAccount = accountManager.getAccount(fromAccountNumber);
        Account toAaccount = accountManager.getAccount(toAccountNumber);

        Transaction fromTransaction = new Transaction(fromAccountNumber,
                amount,
                description,
                toAccountNumber,
                TransactionType.TRANSFER,
                date);

        Transaction toTransaction = new Transaction(toAccountNumber,
                amount,
                description,
                null,
                TransactionType.DEPOSIT,
                date);
        fromAccount.processTransaction(fromTransaction);
        toAaccount.processTransaction(toTransaction);

        transactionManager.setTransaction(fromAccountNumber, fromTransaction);
        transactionManager.setTransaction(toAccountNumber, toTransaction);
    }

}
