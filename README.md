# DSCommerce API

DSCommerce is a RESTful e-commerce backend system developed in Java using Spring Boot. This project was created as part of the DevSuperior learning path and aims to simulate a real-world online store, covering essential backend functionalities such as user authentication, product management, shopping cart handling, and order processing.

## Features

- Public product catalog (with filtering by name)
- Shopping cart management (add, remove, update quantities)
- User authentication and role-based access control (JWT)
- Admin area with full CRUD for:
  - Products
  - Categories
  - Users
- Order management (create, list user orders, change status)
- Order status: pending, paid, shipped, delivered, canceled
- Payment registration (with timestamp)
- Order reports (Admin only)

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **Hibernate**
- **H2 / PostgreSQL**
- **Maven**
- **ModelMapper**
- **Bean Validation**

## Installation & Running

To run the project locally:

```bash
# Clone the repository
git clone https://github.com/rafatav/dscommerce.git
cd dscommerce

# Run the project
./mvnw spring-boot:run
