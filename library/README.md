# Library Management System

## Product Overview

**As a** library system administrator  
**I want** a comprehensive library management system  
**So that** I can efficiently manage books, members, and lending operations while ensuring data integrity and providing excellent service to library patrons.

## Learning Objectives

This exercise focuses on:
- **Basic OOP concepts**: Encapsulation, classes, and objects
- **Collections management**: Using appropriate Java collections
- **State management**: Tracking object states (available/borrowed)
- **Validation**: Input validation and business rule enforcement
- **Exception handling**: Proper error handling strategies

---

## User Stories & Acceptance Criteria

### Epic 1: Book Management

#### User Story 1.1: Add Books to Library Collection
**As a** library administrator  
**I want to** add new books to the library collection  
**So that** members can discover and borrow new titles

**Acceptance Criteria:**
- **Given** a book with valid ISBN, title, and author
  **When** I add it to the library collection
  **Then** the book should be stored and available for borrowing
  **And** I should be able to retrieve the book by its ISBN

- **Given** a book with an ISBN that already exists in the collection
  **When** I try to add it to the library
  **Then** the system should reject the addition with an IllegalStateException
  **And** the original book should remain unchanged

- **Given** invalid book data (null book, null/empty ISBN, null/empty title, null/empty author)
  **When** I try to add it to the library
  **Then** the system should reject it with an IllegalArgumentException
  **And** no book should be added to the collection

#### User Story 1.2: Retrieve Book Information
**As a** library administrator  
**I want to** retrieve book information and view all books
  **So that** I can manage the collection effectively

**Acceptance Criteria:**
- **Given** a valid ISBN of an existing book
  **When** I search for the book
  **Then** the system should return the correct book object

- **Given** an ISBN that doesn't exist in the collection
  **When** I search for the book
  **Then** the system should return null

- **Given** I want to see all books in the collection
  **When** I request the complete book list
  **Then** the system should return a defensive copy of all books
  **And** modifications to the returned list should not affect the internal collection

### Epic 2: Member Management

#### User Story 2.1: Register Library Members
**As a** library administrator  
**I want to** register new members in the system  
**So that** they can borrow books from the library

**Acceptance Criteria:**
- **Given** a member with valid ID and name
  **When** I register them in the system
  **Then** the member should be stored and available for lending operations
  **And** I should be able to retrieve the member by their ID

- **Given** a member with an ID that already exists
  **When** I try to register them
  **Then** the system should reject the registration with an IllegalStateException
  **And** the original member should remain unchanged

- **Given** invalid member data (null member, zero/negative ID, null/empty name)
  **When** I try to register them
  **Then** the system should reject it with an IllegalArgumentException
  **And** no member should be added to the system

#### User Story 2.2: Retrieve Member Information
**As a** library administrator  
**I want to** retrieve member information and view all members  
**So that** I can manage memberships effectively

**Acceptance Criteria:**
- **Given** a valid ID of an existing member
  **When** I search for the member
  **Then** the system should return the correct member object

- **Given** an ID that doesn't exist in the system
  **When** I search for the member
  **Then** the system should return null

- **Given** I want to see all registered members
  **When** I request the complete member list
  **Then** the system should return a defensive copy of all members
  **And** modifications to the returned list should not affect the internal collection

### Epic 3: Book Lending Operations

#### User Story 3.1: Lend Books to Members
**As a** library administrator  
**I want to** lend available books to registered members  
**So that** members can enjoy reading while maintaining proper tracking

**Acceptance Criteria:**
- **Given** an available book and a registered member
  **When** I process a lending request
  **Then** the book should be marked as borrowed
  **And** the borrowing relationship should be tracked
  **And** the book should appear in the member's borrowed books list

- **Given** a book that is already borrowed
  **When** I try to lend it to another member
  **Then** the system should reject the request with an IllegalStateException
  **And** the current borrowing status should remain unchanged

- **Given** a non-existent book or non-existent member
  **When** I try to process a lending request
  **Then** the system should reject it with an IllegalArgumentException
  **And** no lending operation should occur

- **Given** a member who already has borrowed books
  **When** I lend them additional available books
  **Then** all books should be properly tracked in their borrowed books list
  **And** each book should be marked as borrowed

#### User Story 3.2: Return Borrowed Books
**As a** library administrator  
**I want to** process book returns from members  
**So that** books become available for other members to borrow

**Acceptance Criteria:**
- **Given** a book that is currently borrowed
  **When** I process a return request
  **Then** the book should be marked as available
  **And** the borrowing relationship should be removed
  **And** the book should no longer appear in any member's borrowed books list

- **Given** a book that is not currently borrowed
  **When** I try to process a return
  **Then** the system should reject it with an IllegalStateException
  **And** the book's status should remain unchanged

- **Given** a non-existent book
  **When** I try to process a return
  **Then** the system should reject it with an IllegalArgumentException
  **And** no return operation should occur

### Epic 4: Information Retrieval

#### User Story 4.1: Check Book Availability
**As a** library administrator  
**I want to** quickly check if a book is available for borrowing  
**So that** I can provide immediate answers to member inquiries

**Acceptance Criteria:**
- **Given** an existing book that is available
  **When** I check its availability
  **Then** the system should return true

- **Given** an existing book that is currently borrowed
  **When** I check its availability
  **Then** the system should return false

- **Given** a non-existent book
  **When** I check its availability
  **Then** the system should return false

#### User Story 4.2: View Member's Borrowed Books
**As a** library administrator  
**I want to** see all books currently borrowed by a specific member  
**So that** I can track lending history and manage returns

**Acceptance Criteria:**
- **Given** a member who has borrowed books
  **When** I request their borrowed books list
  **Then** the system should return all books currently borrowed by that member
  **And** the returned list should be a defensive copy

- **Given** a member who has not borrowed any books
  **When** I request their borrowed books list
  **Then** the system should return an empty list

- **Given** a non-existent member
  **When** I request their borrowed books list
  **Then** the system should reject it with an IllegalArgumentException

---

## Technical Implementation Requirements

### Core Classes to Implement

1. **Book**: Represents a book with ISBN, title, author, and borrowing status
   - Must be immutable except for borrowing status
   - Must implement proper equals() and hashCode() based on ISBN
   - Must validate all input parameters

2. **Member**: Represents a library member with ID and name
   - Must be immutable once created
   - Must implement proper equals() and hashCode() based on member ID
   - Must validate all input parameters

3. **Library**: Main class that manages books, members, and lending operations
   - Must use appropriate collections (HashMap for fast lookups, ArrayList for ordered data)
   - Must maintain data integrity across all operations
   - Must provide defensive copies for all collection returns

### Key Design Considerations

- **Collections Strategy**: Use HashMap<String, Book> for books (ISBN lookup), HashMap<Integer, Member> for members (ID lookup), and HashMap<String, Member> for tracking borrowing relationships
- **Encapsulation**: All fields private with appropriate public methods
- **Validation**: Comprehensive input validation with meaningful exception messages
- **Immutability**: Book and Member objects should be immutable except for book borrowing status
- **Defensive Programming**: Return defensive copies of collections to prevent external modification

### Exception Handling Strategy

Create and use appropriate exceptions:
- **IllegalArgumentException**: For null/invalid input parameters
- **IllegalStateException**: For business rule violations (duplicate entries, invalid state transitions)

### Testing Strategy

The comprehensive test suite validates:
- **Happy Path**: All normal operations work correctly
- **Edge Cases**: Null inputs, empty strings, boundary conditions
- **Error Conditions**: Proper exception handling for invalid operations
- **State Consistency**: System maintains correct state after all operations
- **Defensive Copying**: External modifications don't affect internal state

## Getting Started

1. **Examine the test file** (`LibraryTest.java`) to understand expected behavior
2. **Study the PlantUML diagram** (`library.puml`) for class relationships
3. **Start with basic classes** (Book, Member) and their validation
4. **Implement the Library class** with core functionality
5. **Run tests frequently** to validate your implementation
6. **Refactor** to improve design and follow SOLID principles

## Advanced Considerations

Once you have the basic implementation working, consider:
- How would you add due dates for borrowed books?
- How would you implement a reservation system?
- How would you add different types of library items (DVDs, magazines)?
- How would you implement a fine system for overdue books?
- How would you add search functionality by title or author?

## Success Criteria

Your implementation demonstrates mastery when:
- ✅ All unit tests pass
- ✅ Proper exception handling for all edge cases
- ✅ Defensive copying prevents external state modification
- ✅ Clean separation of concerns between classes
- ✅ Proper use of Java collections
- ✅ Code follows Java naming conventions and best practices
