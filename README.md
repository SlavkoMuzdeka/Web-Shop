# WebShopApp

## Project Motivation
The motivation behind this web programming project for the Internet Programming course is to create a practical and functional system that simulates the operations of a fictional web shop. The project aims to provide an immersive and engaging online shopping experience for users, as well as efficient management tools for administrators and a reliable customer support system.

## Getting started

### Key Dependencies & Platforms

- [Spring Boot](https://spring.io/projects/spring-boot)
Spring Boot: The project utilizes Spring Boot, a popular Java framework for building web applications. It provides a streamlined development experience and offers various features for creating RESTful APIs and managing dependencies.

- [Angular](https://angular.io/)
Angular is a widely used TypeScript-based framework for building dynamic web applications on the client-side. It enables the creation of responsive and interactive user interfaces, allowing seamless interaction with the backend APIs.

- [MySQL](https://www.mysql.com/)
MySQL is a widely-used open-source relational database management system known for its scalability, performance, and robustness. It provides a comprehensive set of features for storing and managing data, making it an ideal choice for integrating with your Java Spring Boot application. MySQL offers strong data integrity and security mechanisms, ensuring the reliability and confidentiality of your application's data.

- [JSP](https://docs.oracle.com/javaee/5/tutorial/doc/bnagx.html)
JSP is a technology that enables the creation of dynamic web pages using Java. It allows embedding Java code within HTML, facilitating the generation of dynamic content on the server-side.

- [JSP M2](https://docs.oracle.com/javaee/5/tutorial/doc/bnagx.html)
JSP M2 (Model 2) is an architectural pattern used in Java web development. It promotes the separation of concerns by separating the user interface (JSP pages) from the business logic (Java classes) and data management (database operations).

- [Bootstrap](https://getbootstrap.com/)
Bootstrap is a popular CSS framework that provides a set of pre-designed UI components and styles. It enables the creation of responsive and visually appealing user interfaces without extensive CSS coding.

- [Material Design](https://m3.material.io/)
Material Design is a design language created by Google that offers a set of design guidelines and components for creating modern and visually consistent interfaces. It provides a clean and intuitive user experience across different devices and platforms.

- [Maven](https://maven.apache.org/)
Maven is used as a build automation tool and dependency management system for the Java project. It simplifies the process of managing project dependencies, compiling code, running tests, and packaging the application for deployment.

### Running Locally

#### Installing Dependencies

##### JDK 17

Java Development Kit (JDK) installed on your machine. Follow the instructions to install the latest version of JDK for your platform from the official Oracle website. [jdk docs](https://www.oracle.com/java/technologies/downloads/)

#### Database Setup

Ensure that you have a MySQL database server installed and running on your local machine. You can download and install MySQL Community Edition from the official MySQL [website](https://dev.mysql.com/downloads/)

To set up the database for your project, execute the provided SQL script, using `script.sql` file provided in `database` folder, using a MySQL client. You can run the script using the following command:

```bash
  mysql -u <username> -p <database_name> < script.sql
```
Replace <username> with your MySQL username and <database_name> with the name of your database. Enter the MySQL password when prompted.
  
To populate your MySQL database with data, you can use the `data.sql` script provided. The `data.sql` script contains SQL statements to insert data into the respective tables.
  
```bash
  mysql -u <username> -p <database_name> < data.sql
```
