# Object-Oriented Programming Design Exercises

## 🎯 Overview

This repository contains hands-on Object-Oriented Programming exercises designed to help you practice core OOP concepts through real-world scenarios. Each exercise focuses on different aspects of OOP design and implementation.

## 📚 Available Exercises

### 1. College Enrollment Management System (`/college`)
**Difficulty: Intermediate**  
**Focus: Object Relationships & Collection Management**

Practice implementing a many-to-many relationship system with:
- Student and Course entities
- Enrollment junction objects
- Grade management
- Comprehensive data validation

**Key OOP Concepts:**
- Encapsulation and data validation
- Many-to-many relationships
- Defensive programming
- Collection management (Maps, Sets, Lists)

**What You'll Build:**
- `Student.java` - Student entity with ID and name
- `Course.java` - Course entity with ID and title
- `Enrollment.java` - Junction object linking students and courses
- `College.java` - Main management system

### 2. Bank Account Management System (`/bank`)
**Difficulty: Advanced**  
**Focus: Immutable Objects & Business Logic**

Build a complete banking system with:
- Immutable transaction objects
- Account management with balance validation
- Atomic transfer operations
- Transaction history and reporting

**Key OOP Concepts:**
- Immutable value objects
- Encapsulation with business rules
- Atomic operations
- Exception handling
- Defensive programming

**What You'll Build:**
- `Transaction.java` - Immutable transaction records
- `Account.java` - Bank account entity with balance management
- `Bank.java` - Main banking service orchestrator

## 🚀 Getting Started

1. **Choose an Exercise**: Start with the one that interests you most
2. **Read the README**: Each project has detailed implementation guidelines
3. **Study the Tests**: Understand requirements through comprehensive test suites
4. **Implement Step by Step**: Follow the suggested implementation order
5. **Test Frequently**: Run `mvn test` to validate your progress

## 🧪 Running Tests

Each project includes comprehensive test suites:

```bash
# College project
cd college
mvn test

# Bank project  
cd bank
mvn test
```

## 📋 Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Basic understanding of OOP concepts
- Familiarity with Java collections

## 🎓 Learning Path Recommendation

**For Beginners:**
1. Start with College project (simpler relationships)
2. Then tackle Bank project (more complex business logic)

**For Intermediate Developers:**
1. Try Bank project first (immutability and business rules)
2. Then College project (focus on relationship management)

## 💡 Key Learning Outcomes

After completing these exercises, you will have practiced:

- **Encapsulation**: Proper use of private fields and public methods
- **Object Relationships**: One-to-many and many-to-many relationships
- **Immutability**: Creating objects that cannot be modified after creation
- **Data Validation**: Robust input validation and error handling
- **Defensive Programming**: Protecting internal state and returning safe copies
- **Exception Handling**: Using appropriate exception types
- **Collection Management**: Working effectively with Java collections
- **Business Logic**: Implementing real-world rules and constraints
- **Test-Driven Development**: Understanding requirements through tests

## 🔧 Project Structure

Each exercise follows Maven standard directory layout:

```
project-name/
├── pom.xml                    # Maven configuration
├── README.md                  # Detailed implementation guide
├── src/
│   ├── main/java/            # Your implementation goes here (currently empty)
│   └── test/java/            # Comprehensive test suites (provided)
└── *.puml                    # UML diagrams for reference
```

## ⚠️ Important Notes

- **No Implementation Provided**: You need to implement all classes from scratch
- **Tests Are Your Guide**: Use the test files to understand expected behavior
- **Focus on OOP Principles**: Write clean, well-encapsulated code
- **Validate Everything**: Practice defensive programming with input validation
- **Think About Edge Cases**: Consider null values, empty inputs, and error conditions

## 🚀 Extension Ideas

Once you complete the basic exercises:

- Add new features to existing systems
- Combine concepts from both projects
- Implement additional design patterns
- Add persistence layers
- Create web interfaces
- Add concurrent access support

## 📚 Additional Resources

- [Java Documentation](https://docs.oracle.com/javase/tutorial/)
- [Effective Java by Joshua Bloch](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/)
- [Clean Code by Robert Martin](https://www.oreilly.com/library/view/clean-code-a/9780136083238/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

**Happy Coding!** 🚀 These exercises will give you solid hands-on experience with Object-Oriented Programming principles and best practices. 