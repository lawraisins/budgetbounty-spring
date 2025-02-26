# BudgetBounty
A Spring Boot application that allows users to manage bills and make timely payments. Users can earn points by making timely payments and redeem the accumulated points for rewards.
## Tech Stack
- Java 17
- Spring Boot 3.0
- Spring Data JPA
- Hibernate
- Oracle Database
## Prerequisites
- Java 17+
- Maven 3+
- Oracle DB
## Installation

### 1. Clone the repository:
git clone https://github.com/lawraisins/budgetbounty-spring.git
cd budgetbounty-spring

### 2. Configure database details in application.properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/ORCLCDB
spring.datasource.username=system
spring.datasource.password=mypassword1

### 3. Build and run project
mvn clean install
mvn spring-boot:run


