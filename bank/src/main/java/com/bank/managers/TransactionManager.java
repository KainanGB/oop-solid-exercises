package com.bank.managers;

import com.bank.Account;
import com.bank.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionManager {

    private final AccountManager accountManager;
    private final Map<String, List<Transaction>> transactions = new HashMap<>();

    public TransactionManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public List<Transaction> getTransactions() {
        return transactions.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionHistoryByAccountNumber(String accountNumber) {
        Account account = accountManager.getAccount(accountNumber);
        if(transactions.get(account.getAccountNumber()) != null) {
            return new ArrayList<>(transactions.get(accountNumber));
        }
        return new ArrayList<>();
    }

    public void setTransaction(String accountNumber, Transaction transaction) {
        if(hasTransactions(accountNumber)) {
            transactions.get(accountNumber).add(transaction);
            return;
        }

        transactions.put(accountNumber, new ArrayList<>(List.of(transaction)));
    }

    public boolean hasTransactions(String accountNumber) {
        return transactions.containsKey(accountNumber);
    }
}
