# College Enrollment Management System - OOP Practice Exercise

## 🎯 Overview

This is a hands-on Object-Oriented Programming exercise where you'll build a college enrollment management system from scratch. This exercise will help you practice core OOP concepts including encapsulation, relationships between objects, data validation, and defensive programming.

## 🎓 Learning Objectives

By completing this exercise, you will master:

- **Encapsulation**: Proper use of private fields and public methods
- **Object Relationships**: Implementing many-to-many relationships between entities
- **Data Validation**: Robust input validation and error handling
- **Collection Management**: Working with Maps, Sets, and Lists effectively
- **Defensive Programming**: Protecting internal state and returning safe copies
- **Exception Handling**: Using appropriate exception types for different scenarios
- **equals() and hashCode()**: Implementing proper object equality and hashing
- **Test-Driven Development**: Understanding requirements through comprehensive tests

## 📋 System Requirements

Your system must manage:

1. **Students**: Each with a unique ID and name
2. **Courses**: Each with a unique ID and title  
3. **Enrollments**: The many-to-many relationship between students and courses
4. **Grades**: Optional grades that can be assigned to enrollments

## 🏗️ What You Need to Implement

### 1. Student Class (`src/main/java/com/college/Student.java`)

**Requirements:**
- Private fields: `studentId` (String), `name` (String)
- Constructor that validates inputs (no null/empty values)
- Getter methods for both fields
- Proper `equals()`, `hashCode()`, and `toString()` methods based on studentId
- Should throw `IllegalArgumentException` for invalid inputs

**Key Points:**
- Two students are equal if they have the same studentId
- The hashCode should be based on studentId only
- toString() should return a meaningful representation

### 2. Course Class (`src/main/java/com/college/Course.java`)

**Requirements:**
- Private fields: `courseId` (String), `title` (String)
- Constructor that validates inputs (no null/empty values)
- Getter methods for both fields
- Proper `equals()`, `hashCode()`, and `toString()` methods based on courseId
- Should throw `IllegalArgumentException` for invalid inputs

**Key Points:**
- Two courses are equal if they have the same courseId
- The hashCode should be based on courseId only
- toString() should return a meaningful representation

### 3. Enrollment Class (`src/main/java/com/college/Enrollment.java`)

**Requirements:**
- Private fields: `student` (Student), `course` (Course), `grade` (Double)
- Constructor taking student and course (grade starts as null)
- Method to assign grade: `assignGrade(Double grade)`
- Method to check if graded: `isGraded()`
- Getter methods for all fields
- Proper `equals()`, `hashCode()`, and `toString()` methods based on student and course

**Key Points:**
- Grade can be null (not yet assigned)
- Two enrollments are equal if they have the same student and course
- Should validate that student and course are not null
- Grade validation should ensure it's between 0.0 and 4.0 (if not null)

### 4. College Class (`src/main/java/com/college/College.java`)

This is the main management class that orchestrates all operations.

**Required Fields:**
- Collection to store students
- Collection to store courses  
- Collection to store enrollments

**Required Methods:**

#### Student Management
- `registerStudent(String studentId, String name)` - Register a new student
- `getStudent(String studentId)` - Retrieve a student by ID
- `getAllStudents()` - Get all registered students
- `isStudentRegistered(String studentId)` - Check if student exists

#### Course Management  
- `createCourse(String courseId, String title)` - Create a new course
- `getCourse(String courseId)` - Retrieve a course by ID
- `getAllCourses()` - Get all created courses
- `isCourseCreated(String courseId)` - Check if course exists

#### Enrollment Management
- `enrollStudent(String studentId, String courseId)` - Enroll student in course
- `isStudentEnrolled(String studentId, String courseId)` - Check enrollment status
- `getEnrollment(String studentId, String courseId)` - Get specific enrollment
- `getStudentEnrollments(String studentId)` - Get all enrollments for a student
- `getCourseEnrollments(String courseId)` - Get all enrollments for a course

#### Grade Management
- `assignGrade(String studentId, String courseId, Double grade)` - Assign grade to enrollment
- `getGrade(String studentId, String courseId)` - Get grade for specific enrollment
- `getStudentGrades(String studentId)` - Get all grades for a student

**Important Implementation Notes:**
- All methods should validate inputs and throw appropriate exceptions
- Return defensive copies of collections (never expose internal state)
- Prevent duplicate registrations/enrollments
- Ensure students and courses exist before enrollment
- Use `IllegalArgumentException` for invalid inputs
- Use `IllegalStateException` for invalid operations (e.g., enrolling non-existent student)

## 🧪 Testing Your Implementation

The project includes comprehensive tests in `CollegeTest.java`. Run them with:

```bash
mvn test
```

**Test Categories:**
- Student Management (8 tests)
- Course Management (8 tests) 
- Enrollment Management (8 tests)
- Grade Management (6 tests)
- Enrollment Retrieval (8 tests)
- Integration Tests (2 tests)

**Total: 40 tests** - All should pass when correctly implemented!

## 🚀 Getting Started

1. **Study the Tests First**: Examine `CollegeTest.java` to understand expected behavior
2. **Implement in Order**: 
   - Start with `Student.java` (simplest)
   - Then `Course.java` (similar to Student)
   - Next `Enrollment.java` (introduces relationships)
   - Finally `College.java` (most complex)
3. **Test Frequently**: Run `mvn test` after implementing each class
4. **Read Error Messages**: Test failures provide valuable implementation hints

## 💡 Key OOP Concepts Demonstrated

### Encapsulation
- Private fields with controlled access through public methods
- Internal validation logic hidden from clients
- Data integrity maintained through proper encapsulation

### Object Relationships
- Many-to-many relationship between Students and Courses
- Junction entity (Enrollment) to manage the relationship
- Proper navigation between related objects

### Defensive Programming
- Input validation at all entry points
- Returning copies of collections to prevent external modification
- Null checks and appropriate exception handling

### Data Integrity
- Preventing duplicate registrations
- Ensuring referential integrity (students/courses exist before enrollment)
- Maintaining consistent state across all operations

## ⚠️ Common Pitfalls to Avoid

1. **Exposing Internal Collections**: Always return defensive copies
2. **Forgetting Validation**: Validate all inputs in constructors and methods
3. **Incorrect equals()/hashCode()**: Base on the right fields (IDs for entities)
4. **Missing Null Checks**: Always check for null before processing
5. **Wrong Exception Types**: Use `IllegalArgumentException` for bad inputs
6. **Mutable State Exposure**: Don't let external code modify your internal state

## 🎯 Success Tips

1. **Start Simple**: Get basic functionality working before adding validation
2. **Use Your IDE**: Let it generate `equals()`, `hashCode()`, and `toString()` methods
3. **Test One Method at a Time**: Implement and test incrementally
4. **Read the Test Names**: They often describe exactly what should happen
5. **Think About Edge Cases**: What happens with null, empty, or duplicate values?

## 🚀 Extension Ideas

Once you complete the basic implementation:

- Add course prerequisites and validation
- Implement GPA calculation for students
- Add semester/term management
- Create course capacity limits
- Add student graduation requirements
- Implement course scheduling with time conflicts

## 📚 Resources

- [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/)
- [Effective Java - Item 10: Obey the general contract when overriding equals](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

**Ready to start coding?** Begin with the `Student` class and work your way up! 🚀

Remember: The goal is to practice OOP principles, so focus on writing clean, well-encapsulated code that properly validates inputs and maintains data integrity.