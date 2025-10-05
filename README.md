# Shopping Cart Implementation — Approach & Explanation

# 1. Overview

Refactoring is proposed to enhance the code's readability, maintainability, efficiency, and correctness while strictly preserving the existing business functionality—a basic e-commerce shopping cart operation. 


# 2. Approaches

## Initial Single File Approach

The initial approach was to keep the code in one file for simplicity; just fixing bad data structure, removing duplication of logic, addressing precision issues, introducing model classes, and improving readability by fixing variable names, and removing too much inline logic.

Steps taken:

- Introduce a CartItem class with fields: name, price (BigDecimal), quantity.

- Use a Cart class that stores a list of CartItems and a method getTotal().

- Use BigDecimal instead of double.

- Extract repeated logic into helper methods.

- Improve readability with clear variable names and better structure.

## Object-Oriented Design Apprach

Improving on the first approach, the code logic was separated into clear, single-purpose classes. The separation aligns with the Single Responsibility Principle and makes each class easier to maintain, modify, and test independently. 

Steps taken:

- CartItem — Represents a single product with a name, price, and quantity.

- Cart — Manages multiple CartItem objects, handles quantity aggregation, and calculates totals.

- ShoppingCartController — Provides REST endpoints for adding items and retrieving totals.

## Data and Type Improvements


- Replaced double with BigDecimal for price and total calculations to ensure precision in monetary values.

- Used a Map<String, Cart> instead of a Map<String, Object> to eliminate type casting and improve clarity.

- Applied computeIfAbsent to simplify the “get or create cart” logic and reduce boilerplate.


## Readability and Maintainability

- Used clear and descriptive method and variable names (addItem, getTotal, increaseQuantity).

- Reduced nested logic by moving item-handling and total-calculation responsibilities into their respective domain classes.

- Added inline comments only where they aid understanding — avoiding noise.


## Correctness and Consistancy

- Improved total calculation by using a precise accumulation pattern with BigDecimal.ZERO.

- Simplified the logic for merging duplicate items (matching on both name and price).

- Ensured all public endpoints return meaningful messages (e.g., "Cart not found").


# 3. Implementations

| Concern             | Original Implementation | Improved Implementation                               |
| ------------------- | ----------------------- | ----------------------------------------------------- |
| Type Safety         | `Map<String, Object>`   | `Map<String, Cart>`                                   |
| Currency Precision  | `double`                | `BigDecimal`                                          |
| Item Management     | Inline, repetitive      | Encapsulated in `Cart` and `CartItem`                 |
| Cart Initialization | Manual null check       | `computeIfAbsent` one-liner                           |
| Readability         | Mixed responsibilities  | Clear domain boundaries                               |
| Extendability       | Hard to evolve          | Ready for features like remove/update item, discounts |


# 5. Summary

The refactored implementation achieves:

- Cleaner and modular structure

- Improved correctness and precision

- Better adherence to software design principles

The business functionality remains the same, but the codebase is now far easier to read, extend, and maintain — which is the essence of this exercise.