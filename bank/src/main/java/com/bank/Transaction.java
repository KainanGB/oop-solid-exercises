package com.bank;

import com.bank.enums.TransactionType;
import com.bank.validators.TransactionValidator;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private final String accountNumber;
    private final Double amount;
    private final String description;
    private final String toAccount;
    private final Enum<TransactionType> type;
    private final LocalDateTime date;

    public Transaction(String accountNumber, Double amount, String description, String toAccount, Enum<TransactionType> type, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
        this.toAccount = toAccount;
        this.type = type;
        this.date = date;
       TransactionValidator.selfValidate(this);
    }

    public Double getAmount() {
        return amount;
    }

    public Enum<TransactionType> getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(accountNumber, that.accountNumber) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(toAccount, that.toAccount) && Objects.equals(type, that.type) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, amount, description, toAccount, type, date);
    }

}
