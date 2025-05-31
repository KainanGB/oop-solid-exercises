package com.bank;

import com.bank.enums.TransactionType;
import com.bank.validators.AccountValidator;
import com.bank.validators.TransactionValidator;

import java.util.Objects;

public class Account {

    private final String accountNumber;
    private Double amount;

    public Account(String name, Double amount) {
        this.accountNumber = name;
        this.amount = amount;
        AccountValidator.selfValidate(this);
    }

    public Double getBalance() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAmount(Double amount) {
        this.amount += amount;
        AccountValidator.selfValidate(this);
    }

    public void processTransaction(Transaction transaction) {
        if(TransactionValidator.isTransferOrWithdraw(transaction)) {
            TransactionValidator.validateTransaction(transaction, this);
            if(this.amount < transaction.getAmount()) {
                throw new IllegalStateException("Not enough balance to make operation");
            }
            this.amount -= transaction.getAmount();
        }

        if(transaction.getType().name().equals(TransactionType.DEPOSIT.name())) {
            this.setAmount(transaction.getAmount());
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(amount, account.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, amount);
    }
}
