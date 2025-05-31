package com.bank.validators;

import com.bank.Account;
import com.bank.Transaction;
import com.bank.enums.TransactionType;

import java.time.LocalDateTime;

public abstract class TransactionValidator {

    private TransactionValidator(){}

    public static void selfValidate(Transaction transaction) {
        validateAmount(transaction);
        validateDescription(transaction);
        validateDate(transaction);
    }

    public static void validateTransaction(Transaction transaction, Account account) {
        validateAmount(transaction);
        validateDescription(transaction);
        validateDate(transaction);

        if(isTransferOrWithdraw(transaction)) {
            validateTransferAndWithdraw(transaction, account);
        }
    }

    private static void validateDescription(Transaction transaction) {
        if(transaction.getDescription() == null ) {
            throw new IllegalArgumentException("Transaction description cannot be null.");
        }
    }

    private static void validateDate(Transaction transaction) {
        LocalDateTime now = LocalDateTime.now();

        if(transaction.getDate() == null ) {
            throw new IllegalStateException("Transaction date cannot be null.");
        }

        if(transaction.getDate().isBefore(now.minusSeconds(2))) {
            throw new IllegalStateException("Transaction date cannot be in the past.");
        }
    }

    private static void validateAmount(Transaction transaction) {
        if(transaction.getAmount() == null) {
            throw new IllegalArgumentException("Transaction amount cannot be null");
        }

        if(transaction.getAmount() == 0.0) {
            throw new IllegalArgumentException("Transaction amount cannot be zero");
        }

        if(transaction.getAmount() < 0.0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative.");
        }
    }

    private static void validateTransferAndWithdraw(Transaction transaction, Account account) {
        if(account.getBalance() < transaction.getAmount()) {
            throw new IllegalStateException("Not enough balance to make operation");
        }
    }

    public static Boolean isTransferOrWithdraw(Transaction transaction) {
        return (transaction.getType().name().equals(TransactionType.WITHDRAW.name())
                || transaction.getType().name().equals(TransactionType.TRANSFER.name()));
    }

}
