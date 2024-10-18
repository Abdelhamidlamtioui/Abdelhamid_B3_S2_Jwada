Here's the updated **README.md** with your email:

```markdown
# Online Order Management System

## Project Context
An enterprise aims to expand its online order management capabilities by developing a secure web application that allows customers to place orders and administrators to manage users and products.

## Objectives
- Implement user management with session-based authentication (HttpSession), differentiating between Admin and Client roles.
- Use **Thymeleaf** as the template engine to create views, completely replacing JSP and JSTL.
- Utilize **Thymeleaf** to create dynamic layouts for easy navigation.
- Manage products, orders, and users via CRUD functionalities.
- Implement unit testing with **JUnit** and **Mockito** to test business and data access components.
- Apply a layered architecture (MVC) to separate presentation, business logic, and data persistence.

## Key Features
The application contains 4 main pages:

1. **Authentication Page**  
   Allows users to log in and manage their sessions based on their roles.

2. **Product Management Page** (Accessible to Admins Only)
   - Display a paginated and searchable list of products.
   - Create, update, and delete products.

3. **Order Management Page** (Accessible to Admins and Clients)
   - **Clients**: 
     - Place new orders, modify or delete orders that are still "Pending" or "In Process."
     - View and search their order history.
     - Check the current status of each order.
     - Orders cannot be modified or deleted once they are "Shipped" or beyond.
   - **Admins**:
     - View and search all orders.
     - Update the status of orders.

4. **User Management Page** (Accessible to Admins Only)
   - Display a paginated and searchable list of users (Admins and Clients).
   - Create, update, and delete users.

## Core Class Structure
- **User**: Contains fields like `firstName`, `lastName`, `email`, `password`, `role` (Enum: Admin, Client)
- **Admin**: Includes `accessLevel` (e.g., 1 for super admin, 2 for tiered admin)
- **Client**: Includes `shippingAddress`, `paymentMethod`
- **Product**: Includes `name`, `description`, `price`, `stock`
- **Order**: Includes `orderDate`, `status` (Enum: Pending, In Process, Shipped, Delivered, Cancelled)

### Order Statuses:
- **Pending**: Order placed but not yet confirmed
- **In Process**: Order confirmed, preparation in progress
- **Shipped**: Order sent to the client
- **Delivered**: Order received by the client
- **Cancelled**: Order cancelled by the client or admin

### Security
- Passwords are never stored in plain text.

## Technical Requirements
- **Java 8**
- **Thymeleaf** (No JSP and JSTL)
- **Maven** for dependency management
- **Tomcat** as the web container
- **JUnit & Mockito** for unit testing (following TDD)
- **CSS Framework** of your choice and **FIGMA** for prototyping
- **web.xml** configuration (no URL mapping annotations)
- **PostgreSQL** as the database
- **JPA & Hibernate** for data access
- **JIRA** for project management (Scrum methodology)
- **Git** with branches and proper integration with JIRA
- **Logging** via a logging framework (e.g., Log4j, SLF4J)
- **Pagination** implementation
- **Git-flow** workflow

### Database Design Considerations:
- Use **indexes**, **GRANT**, **UNIQUE**, and **NOT NULL** constraints in the SQL schema.

### Advanced Java Concepts
- **Java Time API & Collection API**
- **HashMaps**
- **Lambda Expressions**
- **Java Stream API**
- **Optional**

## Project Setup

### Prerequisites
- Install **Java 8**
- Install **PostgreSQL**
- Install **Maven**
- Install **Tomcat**
- Install an **IDE** of your choice (e.g., IntelliJ IDEA, Eclipse)

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/online-order-management.git
   ```
2. Navigate to the project directory:
   ```bash
   cd online-order-management
   ```
3. Build the project with Maven:
   ```bash
   mvn clean install
   ```
4. Set up the PostgreSQL database:
   - Create a new database named `Jawda`.
   - Configure the database credentials in `persistence.xml`:
     ```xml
     <property name="javax.persistence.jdbc.user" value="cosk"/>
     <property name="javax.persistence.jdbc.password" value="123456789"/>
     ```
5. Deploy the WAR file to Tomcat:
   - Copy the generated WAR file (`target/online-order-management.war`) to the `webapps` directory of Tomcat.
   - Start Tomcat.

### Running Tests
Run the following command to execute unit tests:
```bash
mvn test
```

## Development Workflow
1. **Branching**: Follow Git-flow. Develop on feature branches, merge into `develop`, and create release branches as needed.
2. **Task Management**: Use JIRA tasks for feature implementation, bug fixes, and enhancements. Link Git commits to JIRA issues.
3. **Code Review**: Perform code reviews before merging into `main`.

## JIRA Project Management
For project management, visit the [JIRA Board](https://oneno9847.atlassian.net/jira/software/projects/SCRUM/boards/1/backlog?atlOrigin=eyJpIjoiZGU4MzI3N2Y1MzlhNDZiYzk0NGM2ZTRlYjFmMjQ4ZjIiLCJwIjoiaiJ9).

## Contribution
1. Fork the repository.
2. Create a new feature branch.
3. Commit your changes.
4. Push to your forked repository.
5. Create a pull request.

## License
This project is licensed under the [MIT License](LICENSE).

## Contact
For any inquiries or issues, please contact **Abdelhamid Lamtioui** at [oneno9847@gmail.com](mailto:oneno9847@gmail.com).
```

This README should now be complete with your information. Let me know if you need any more changes!