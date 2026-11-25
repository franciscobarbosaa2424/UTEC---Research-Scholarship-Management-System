# SECU – Research Scholarship Management System

Collaborative academic project developed at UTEC (Universidad Tecnológica del Uruguay) to manage research scholarship applications and evaluations through an interactive command-line interface (CLI).

## Features

- Scholarship application and evaluation management.
- Interactive command-line menus.
- User authentication and input validation.
- Email notifications.
- Database transaction management.
- Object-oriented programming and design patterns: DAO, Factory, Singleton and Proxy.
- Collaborative development, functional testing and version control using Git.

## Technologies

- Java
- PostgreSQL
- JDBC
- SQL
- JavaMail
- Git

## Database

The system uses **PostgreSQL** as its relational database management system, with **JDBC** for database connectivity and the **DAO pattern** for data persistence.

### Database features

- Relational database modeling.
- Primary and foreign keys.
- Data integrity constraints.
- Parameterized SQL queries using PreparedStatement.
- Transaction management with commit and rollback.
- Database indexes.

### Database setup

1. Install PostgreSQL.
2. Create a new database.
3. Import the SQL script located at [`database/SECU-database.sql`](./database/SECU-database.sql).
4. Configure the database connection credentials in the Java application.
5. Run the application.
