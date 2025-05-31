# Inventory Management System

An inventory system keeps track of products in a warehouse. The system allows adding new products, updating stock levels, and checking product availability. This exercise focuses on collections management, validation, and business logic implementation.

## Learning Objectives

This exercise focuses on:
- **Collections Management**: Using appropriate Java collections (HashMap, ArrayList)
- **Validation**: Input validation and business rule enforcement
- **Encapsulation**: Proper use of private fields and public methods
- **Exception Handling**: Custom exceptions for business logic violations
- **Immutable Objects**: Creating immutable product representations
- **Business Logic**: Implementing reorder threshold logic

## Features

The inventory system should be able to perform the following operations:

1. **Add Product**: Add a new product to the inventory with a unique product ID, name, reorder threshold and initial stock quantity.
2. **Update Stock**: Update the stock quantity for a specific product (either adding or subtracting from the current stock).
3. **Check Stock**: Check the current stock level for a specific product.
4. **Get Stock Levels**: Retrieve a list of all products currently in the inventory, along with their stock levels.
5. **Product Availability**: Check if a specific product is available (i.e., has stock greater than zero).
6. **Reorder Products**: Automatically flag products for reorder when stock falls below a specified threshold. Retrieve all products that should be reordered.

## Conditions

### Product:
- Each product must have a unique product ID.
- Products have a stock quantity that represents the available units in the warehouse.
- A product should be flagged to reorder when the stock is equal or less than its threshold.
- Product information (ID, name, threshold) should be immutable once created.

### Stock Updates:
- Stock can be increased or decreased, depending on inventory changes.
- Stock cannot go below zero.
- Stock updates should be atomic operations.

### Operations:
- Adding or removing stock should update the product's stock level accordingly.
- Availability is determined based on whether the stock is greater than zero.
- The reorder flag must be removed when the stock increases above the threshold.

## Implementation Hints

### Classes to Implement

1. **Product**: Immutable class representing a product with ID, name, reorder threshold
2. **InventoryItem**: Represents a product with its current stock level
3. **Inventory**: Main class that manages products and stock operations

### Key Design Considerations

- Use HashMap for O(1) product lookups by ID
- Implement proper validation for all operations
- Use custom exceptions for different error conditions
- Consider thread safety if needed
- Implement proper equals() and hashCode() methods

### Exception Handling

Create custom exceptions for:
- Product not found
- Duplicate product ID
- Invalid stock quantity (negative values)
- Insufficient stock for removal

### Testing Strategy

The tests will verify:
- Basic CRUD operations for products
- Stock update functionality
- Reorder threshold logic
- Edge cases and error conditions
- Validation of business rules

## Getting Started

1. Examine the test file to understand expected behavior
2. Look at the PlantUML diagram for class relationships
3. Start with the Product class (immutable)
4. Implement the InventoryItem class
5. Implement the Inventory class with core functionality
6. Run tests frequently to validate your implementation

## Advanced Considerations

Once you have the basic implementation working, consider:
- How would you implement batch operations?
- How would you add product categories?
- How would you implement inventory tracking with timestamps?
- How would you add supplier information and automatic reordering?
