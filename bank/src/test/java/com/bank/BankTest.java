package com.bank;

import com.bank.enums.TransactionType;
import com.bank.managers.AccountManager;
import com.bank.managers.TransactionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Comprehensive test suite for the Bank Account Management System.
 * 
 * These tests validate all the functionality you need to implement.
 * Make sure all tests pass to verify your implementation is correct.
 * 
 * Key concepts tested:
 * - Account creation and validation
 * - Transaction processing (deposits, withdrawals, transfers)
 * - Date validation and business rules
 * - Exception handling and error cases
 * - Report generation and data retrieval
 */
@DisplayName("Bank Account Management System Tests")
class BankTest {

    private Bank bank;
    private TransactionManager transactionManager;
    private AccountManager accountManager;

    private LocalDateTime today;
    private LocalDateTime yesterday;
    private LocalDateTime tomorrow;

    @BeforeEach
    void setUp() {
        accountManager = new AccountManager();
        transactionManager = new TransactionManager(accountManager);
        bank = new Bank(accountManager, transactionManager);


        today = LocalDateTime.now();
        yesterday = today.minusDays(1);
        tomorrow = today.plusDays(1);
    }

    @Nested
    @DisplayName("Account Creation Tests")
    class AccountCreationTests {

        @Test
        @DisplayName("Should create account successfully")
        void shouldCreateAccountSuccessfully() {
            accountManager.createAccount("1001", 100.0);
            
            Account account = accountManager.getAccount("1001");
            assertThat(account).isNotNull();
            assertThat(account.getAccountNumber()).isEqualTo("1001");
            assertThat(account.getBalance()).isEqualTo(100.0);
        }

        @Test
        @DisplayName("Should create account with zero initial balance")
        void shouldCreateAccountWithZeroInitialBalance() {
            accountManager.createAccount("1002", 0.0);
            
            Account account = accountManager.getAccount("1002");
            assertThat(account).isNotNull();
            assertThat(account.getBalance()).isEqualTo(0.0);
        }

        @Test
        @DisplayName("Should not create account with negative initial balance")
        void shouldNotCreateAccountWithNegativeInitialBalance() {
            assertThatThrownBy(() -> accountManager.createAccount("1003", -100.0))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not create account with invalid account number")
        void shouldNotCreateAccountWithInvalidAccountNumber() {
            assertThatThrownBy(() -> accountManager.createAccount("0", 100.0))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not create account with negative account number")
        void shouldNotCreateAccountWithNegativeAccountNumber() {
            assertThatThrownBy(() -> accountManager.createAccount("-1", 100.0))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not create duplicate account")
        void shouldNotCreateDuplicateAccount() {
            accountManager.createAccount("1004", 100.0);
            
            assertThatThrownBy(() -> accountManager.createAccount("1004", 200.0))
                .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("Deposit Tests")
    class DepositTests {

        @BeforeEach
        void setUpDeposit() {
            accountManager.createAccount("2001", 100.0);
        }

        @Test
        @DisplayName("Should deposit money successfully")
        void shouldDepositMoneySuccessfully() {
            bank.deposit("2001", 50.0, "description", today);
            
            Account account = accountManager.getAccount("2001");
            assertThat(account.getBalance()).isEqualTo(150.0);
            
            List<Transaction> history = transactionManager.getTransactionHistoryByAccountNumber("2001");
            assertThat(history).hasSize(1);
            assertThat(history.get(0).getAmount()).isEqualTo(50.0);
            assertThat(history.get(0).getType().name()).isEqualTo(TransactionType.DEPOSIT.name());
        }

        @Test
        @DisplayName("Should not deposit zero amount")
        void shouldNotDepositZeroAmount() {
            assertThatThrownBy(() -> bank.deposit("2001", 0.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not deposit negative amount")
        void shouldNotDepositNegativeAmount() {
            assertThatThrownBy(() -> bank.deposit("2001", -50.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not deposit to non-existent account")
        void shouldNotDepositToNonExistentAccount() {
            assertThatThrownBy(() -> bank.deposit("9999", 50.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not deposit with past date")
        void shouldNotDepositWithPastDate() {
            assertThatThrownBy(() -> bank.deposit("2001", 50.0, "description", yesterday))
                .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("Withdrawal Tests")
    class WithdrawalTests {

        @BeforeEach
        void setUpWithdrawal() {
            accountManager.createAccount("3001", 100.0);
        }

        @Test
        @DisplayName("Should withdraw money successfully")
        void shouldWithdrawMoneySuccessfully() {
            bank.withdraw("3001", 30.0, "description", today);
            
            Account account = accountManager.getAccount("3001");
            assertThat(account.getBalance()).isEqualTo(70.0);
            
            List<Transaction> history = transactionManager.getTransactionHistoryByAccountNumber("3001");
            assertThat(history).hasSize(1);
            assertThat(history.get(0).getAmount()).isEqualTo(30.0);
            assertThat(history.get(0).getType().name()).isEqualTo(TransactionType.WITHDRAW.name());
        }

        @Test
        @DisplayName("Should not withdraw zero amount")
        void shouldNotWithdrawZeroAmount() {
            assertThatThrownBy(() -> bank.withdraw("3001", 0.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not withdraw negative amount")
        void shouldNotWithdrawNegativeAmount() {
            assertThatThrownBy(() -> bank.withdraw("3001", -30.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not withdraw more than balance")
        void shouldNotWithdrawMoreThanBalance() {
            assertThatThrownBy(() -> bank.withdraw("3001", 150.0, "description", today))
                .isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("Should not withdraw from non-existent account")
        void shouldNotWithdrawFromNonExistentAccount() {
            assertThatThrownBy(() -> bank.withdraw("9999", 30.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not withdraw with past date")
        void shouldNotWithdrawWithPastDate() {
            assertThatThrownBy(() -> bank.withdraw("3001", 30.0, "description", yesterday))
                .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("Transfer Tests")
    class TransferTests {

        @BeforeEach
        void setUpTransfer() {
            accountManager.createAccount("4001", 100.0);
            accountManager.createAccount("4002", 50.0);
        }

        @Test
        @DisplayName("Should transfer money successfully")
        void shouldTransferMoneySuccessfully() {
            bank.transfer("4001", "4002", 30.0, "description", today);
            
            Account fromAccount = accountManager.getAccount("4001");
            Account toAccount = accountManager.getAccount("4002");
            
            assertThat(fromAccount.getBalance()).isEqualTo(70.0);
            assertThat(toAccount.getBalance()).isEqualTo(80.0);
            
            List<Transaction> fromHistory = transactionManager.getTransactionHistoryByAccountNumber("4001");
            List<Transaction> toHistory = transactionManager.getTransactionHistoryByAccountNumber("4002");
            
            assertThat(fromHistory).hasSize(1);
            assertThat(fromHistory.get(0).getAmount()).isEqualTo(30.0);
            assertThat(fromHistory.get(0).getType().name().equals(TransactionType.TRANSFER.name()));
            
            assertThat(toHistory).hasSize(1);
            assertThat(toHistory.get(0).getAmount()).isEqualTo(30.0);
            assertThat(fromHistory.get(0).getType().name().equals(TransactionType.DEPOSIT.name()));
        }

        @Test
        @DisplayName("Should not transfer zero amount")
        void shouldNotTransferZeroAmount() {
            assertThatThrownBy(() -> bank.transfer("4001", "4002", 0.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not transfer more than balance")
        void shouldNotTransferMoreThanBalance() {
            assertThatThrownBy(() -> bank.transfer("4001", "4002", 150.0, "description", today))
                .isInstanceOf(IllegalStateException.class);
        }

        @Test
        @DisplayName("Should not transfer from non-existent account")
        void shouldNotTransferFromNonExistentAccount() {
            assertThatThrownBy(() -> bank.transfer("9999", "4002", 30.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not transfer to non-existent account")
        void shouldNotTransferToNonExistentAccount() {
            assertThatThrownBy(() -> bank.transfer("4001", "9999", 30.0, "description", today))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should not transfer with past date")
        void shouldNotTransferWithPastDate() {
            assertThatThrownBy(() -> bank.transfer("4001", "4002", 30.0, "description", yesterday))
                .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("Transaction History Tests")
    class TransactionHistoryTests {

        @BeforeEach
        void setUpHistory() {
            accountManager.createAccount("6001", 100.0);
        }

        @Test
        @DisplayName("Should return empty history for new account")
        void shouldReturnEmptyHistoryForNewAccount() {
            List<Transaction> history = transactionManager.getTransactionHistoryByAccountNumber("6001");
            assertThat(history).isEmpty();
        }

        @Test
        @DisplayName("Should return transaction history in order")
        void shouldReturnTransactionHistoryInOrder() {
            bank.deposit("6001", 50.0, "description",today);
            bank.withdraw("6001", 20.0,"description", today);
            bank.deposit("6001", 30.0, "description", today);
            
            List<Transaction> history = transactionManager.getTransactionHistoryByAccountNumber("6001");
            assertThat(history).hasSize(3);
            assertThat(history.get(0).getType().name().equals(TransactionType.DEPOSIT.name()));
            assertThat(history.get(0).getAmount()).isEqualTo(50.0);
            assertThat(history.get(1).getType().name().equals(TransactionType.WITHDRAW.name()));
            assertThat(history.get(1).getAmount()).isEqualTo(20.0);
            assertThat(history.get(2).getType().name().equals(TransactionType.DEPOSIT.name()));
            assertThat(history.get(2).getAmount()).isEqualTo(30.0);
        }

        @Test
        @DisplayName("Should return defensive copy of transaction history")
        void shouldReturnDefensiveCopyOfTransactionHistory() {
            bank.deposit("6001", 50.0, "description", today);

            List<Transaction> history1 = transactionManager.getTransactionHistoryByAccountNumber("6001");
            List<Transaction> history2 = transactionManager.getTransactionHistoryByAccountNumber("6001");
            
            assertThat(history1).isNotSameAs(history2);
            assertThat(history1).isEqualTo(history2);
        }

        @Test
        @DisplayName("Should not get history for non-existent account")
        void shouldNotGetHistoryForNonExistentAccount() {
            assertThatThrownBy(() -> transactionManager.getTransactionHistoryByAccountNumber("9999"))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Should handle complex banking scenario")
        void shouldHandleComplexBankingScenario() {
            // Create multiple accounts
            accountManager.createAccount("7001", 1000.0);
            accountManager.createAccount("7002", 500.0);
            accountManager.createAccount("7003", 0.0);
            
            // Perform various operations
            bank.deposit("7001", 200.0, "description", today);
            bank.withdraw("7001", 150.0, "description", today);
            bank.transfer("7001", "7002", 300.0, "description", today);
            bank.deposit("7003", 100.0, "description", today);
            bank.transfer("7002", "7003", 50.0, "description", today);
            
            // Verify final balances
            assertThat(accountManager.getAccount("7001").getBalance()).isEqualTo(750.0); // 1000 + 200 - 150 - 300
            assertThat(accountManager.getAccount("7002").getBalance()).isEqualTo(750.0); // 500 + 300 - 50
            assertThat(accountManager.getAccount("7003").getBalance()).isEqualTo(150.0); // 0 + 100 + 50
            
            // Verify transaction counts
            assertThat(transactionManager.getTransactionHistoryByAccountNumber("7001")).hasSize(3); // deposit, withdraw, transfer out
            assertThat(transactionManager.getTransactionHistoryByAccountNumber("7002")).hasSize(2); // transfer in, transfer out
            assertThat(transactionManager.getTransactionHistoryByAccountNumber("7003")).hasSize(2); // deposit, transfer in
            
        }

        @Test
        @DisplayName("Should maintain data consistency across operations")
        void shouldMaintainDataConsistencyAcrossOperations() {
            accountManager.createAccount("8001", 500.0);
            accountManager.createAccount("8002", 300.0);
            
            double initialTotal = accountManager.getAccount("8001").getBalance() + accountManager.getAccount("8002").getBalance();
            
            // Perform transfers (should not change total money in system)
            bank.transfer("8001", "8002", 100.0, "description", today);
            bank.transfer("8002", "8001", 50.0, "description", today);
            
            double finalTotal = accountManager.getAccount("8001").getBalance() + accountManager.getAccount("8002").getBalance();
            
            assertThat(finalTotal).isEqualTo(initialTotal);
        }
    }
} 