# Bank Account Management System - OOP Practice Exercise

## 🎯 Overview

This is a comprehensive Object-Oriented Programming exercise where you'll build a complete bank account management system from scratch. This exercise focuses on real-world business logic, immutable objects, defensive programming, and proper exception handling.

## 🎓 Learning Objectives

By completing this exercise, you will master:

- **Object-Oriented Design**: Creating well-structured classes with clear responsibilities
- **Encapsulation**: Using private fields and public methods appropriately
- **Immutable Objects**: Creating objects that cannot be modified after creation
- **Data Validation**: Implementing robust input validation and error handling
- **Collections Management**: Working with Maps and Lists for data storage
- **Exception Handling**: Using appropriate exception types for different error conditions
- **Defensive Programming**: Returning defensive copies to maintain data integrity
- **Business Logic**: Implementing real-world banking rules and constraints
- **Atomic Operations**: Ensuring complex operations succeed or fail completely

## 📋 System Requirements

Your banking system must support:

### Core Features
1. **Account Creation**: Create accounts with unique account numbers and initial balances
2. **Deposits**: Add money to accounts with complete transaction tracking
3. **Withdrawals**: Remove money from accounts with balance validation
4. **Transfers**: Move money between accounts atomically (both succeed or both fail)
5. **Transaction History**: Track all transactions for each account with full audit trail
6. **Report Generation**: Generate transaction reports for specific date ranges

### Business Rules
- Account numbers must be positive integers (stored as strings)
- Initial account balance must be non-negative (>= 0)
- Transaction amounts must be positive (> 0)
- Account balance cannot go negative
- Transaction dates cannot be in the future
- All transactions must be tracked with complete audit trail
- Transfer operations must be atomic (both accounts updated or neither)

## 🏗️ What You Need to Implement

### 1. Transaction Class (`src/main/java/com/bank/Transaction.java`)

This is an **immutable value object** representing a single financial transaction.

**Requirements:**
- Private final fields: `accountNumber` (String), `amount` (double), `description` (String), `date` (LocalDate)
- Constructor that validates all inputs
- Getter methods for all fields (no setters - immutable!)
- Proper `equals()`, `hashCode()`, and `toString()` methods based on ALL fields
- Should throw `IllegalArgumentException` for invalid inputs

**Validation Rules:**
- Account number cannot be null or empty
- Amount must be positive (> 0)
- Description cannot be null or empty
- Date cannot be null or in the future

**Key Points:**
- Once created, a Transaction object cannot be modified
- Two transactions are equal if ALL fields are equal
- toString() should format as: "YYYY-MM-DD - Description: $Amount"
- This represents the "what happened" in the banking system

### 2. Account Class (`src/main/java/com/bank/Account.java`)

This represents a bank account entity that can process transactions.

**Requirements:**
- Private fields: `accountNumber` (String), `balance` (double)
- Constructor that validates account number and initial balance
- Method: `processTransaction(Transaction transaction)` - processes a transaction and updates balance
- Method: `getBalance()` - returns current balance
- Method: `getAccountNumber()` - returns account number
- Proper `equals()`, `hashCode()`, and `toString()` methods based on account number ONLY

**Validation Rules:**
- Account number must be a positive integer (as string)
- Initial balance must be non-negative (>= 0)
- processTransaction() must verify the transaction belongs to this account
- Balance cannot go negative after processing a withdrawal

**Key Points:**
- Two accounts are equal if they have the same account number
- processTransaction() should validate transaction ownership and balance constraints
- Should throw appropriate exceptions for invalid operations

### 3. Bank Class (`src/main/java/com/bank/Bank.java`)

This is the main service class that orchestrates all banking operations.

**Required Fields:**
- Collection to store accounts (suggest: `Map<String, Account>`)
- Collection to store transaction history (suggest: `Map<String, List<Transaction>>`)

**Required Methods:**

#### Account Management
- `createAccount(String accountNumber, double initialBalance)` - Create new account
- `getAccount(String accountNumber)` - Retrieve account by number
- `accountExists(String accountNumber)` - Check if account exists
- `getBalance(String accountNumber)` - Get current balance for account

#### Transaction Operations
- `deposit(String accountNumber, double amount, String description, LocalDate date)` - Add money
- `withdraw(String accountNumber, double amount, String description, LocalDate date)` - Remove money
- `transfer(String fromAccount, String toAccount, double amount, String description, LocalDate date)` - Move money between accounts

#### Transaction History & Reporting
- `getTransactionHistory(String accountNumber)` - Get all transactions for account
- `getTransactionsByDateRange(String accountNumber, LocalDate startDate, LocalDate endDate)` - Get transactions in date range
- `getAllTransactions()` - Get all transactions in the system

**Important Implementation Notes:**
- All methods should validate inputs and throw appropriate exceptions
- Return defensive copies of collections (never expose internal state)
- Prevent duplicate account creation
- Ensure accounts exist before performing operations
- Transfer operations must be atomic (use try-catch to rollback if needed)
- Use `IllegalArgumentException` for invalid inputs
- Use `IllegalStateException` for business rule violations (e.g., insufficient funds)

## 🧪 Testing Your Implementation

The project includes comprehensive tests in `BankTest.java`. Run them with:

```bash
mvn test
```

**Test Categories:**
- Transaction Creation and Validation
- Account Creation and Management
- Deposit Operations
- Withdrawal Operations (including insufficient funds)
- Transfer Operations (including atomic behavior)
- Transaction History and Reporting
- Error Handling and Edge Cases

**All tests should pass when correctly implemented!**

## 🚀 Getting Started

1. **Study the Tests First**: Examine `BankTest.java` to understand expected behavior
2. **Implement in Order**:
   - Start with `Transaction.java` (immutable value object)
   - Then `Account.java` (entity with business logic)
   - Finally `Bank.java` (service orchestrator)
3. **Test Frequently**: Run `mvn test` after implementing each class
4. **Focus on One Method at a Time**: Implement and test incrementally

## 💡 Key OOP Concepts Demonstrated

### Immutability (Transaction)
- Objects that cannot be modified after creation
- All fields are final and private
- No setter methods provided
- Defensive copying if needed for mutable fields

### Encapsulation (Account)
- Private fields with controlled access
- Business logic encapsulated within the class
- State changes only through well-defined methods

### Service Layer (Bank)
- Orchestrates operations between multiple objects
- Manages collections and relationships
- Implements complex business rules and validation

### Defensive Programming
- Input validation at all entry points
- Returning copies of collections to prevent external modification
- Comprehensive error handling with meaningful messages

### Atomic Operations
- Transfer operations that succeed completely or fail completely
- Maintaining data consistency across multiple accounts
- Proper rollback mechanisms for failed operations

## ⚠️ Common Pitfalls to Avoid

1. **Making Transaction Mutable**: Don't add setters to Transaction class
2. **Exposing Internal Collections**: Always return defensive copies from Bank methods
3. **Forgetting Validation**: Validate all inputs in constructors and methods
4. **Non-Atomic Transfers**: Ensure transfer operations are all-or-nothing
5. **Wrong Exception Types**: Use `IllegalArgumentException` for bad inputs, `IllegalStateException` for business rule violations
6. **Incorrect equals()/hashCode()**: Transaction should use ALL fields, Account should use only account number
7. **Missing Transaction Ownership**: Verify transactions belong to the correct account

## 🎯 Success Tips

1. **Start with Transaction**: It's the simplest class and foundation for others
2. **Use Your IDE**: Generate `equals()`, `hashCode()`, and `toString()` methods
3. **Test Edge Cases**: What happens with zero amounts, non-existent accounts, etc.?
4. **Read Test Names**: They describe exactly what should happen
5. **Validate Early**: Check inputs before processing to fail fast
6. **Think About State**: What should happen if an operation partially fails?

## 🚀 Extension Ideas

Once you complete the basic implementation:

- Add different account types (Checking, Savings, Credit) with different rules
- Implement interest calculation for savings accounts
- Add transaction fees for different operations
- Create account statements with formatted output
- Add concurrent access support for multiple users
- Implement overdraft protection
- Add transaction categories and budgeting features

## 📚 Resources

- [Java LocalDate Documentation](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)
- [Effective Java - Item 17: Minimize mutability](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/)
- [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/)
- [Exception Handling Best Practices](https://docs.oracle.com/javase/tutorial/essential/exceptions/)

---

**Ready to start coding?** Begin with the `Transaction` class and build your way up! 🚀

Remember: Focus on writing clean, well-encapsulated code that properly validates inputs, maintains data integrity, and implements real-world business rules. This exercise simulates actual banking software requirements!
