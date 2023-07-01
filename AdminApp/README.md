# AdminApp

The Admin App is a web application designed for administrators to manage the WebShopApp system. It provides a secure login system and offers various functionalities to handle important data and monitor the application's backend.

## Key Features

- `Login`: To access the admin application, administrators need to have a direct account created in the database. The login form is available on the initial page, and upon successful authentication, administrators are granted access to the admin panel.

- `Categories`: The Categories section allows administrators to manage data related to categories and specific subcategories of products. It provides CRUD (Create, Read, Update, Delete) operations for managing category information effectively.

- `Users`: The Users section enables administrators to manage data related to users of the WebShopApp. It provides CRUD operations for managing user information, allowing administrators to add, view, update, and delete user accounts as needed.

- `Statistics`: The Statistics section allows administrators to view logs and statistics related to the WebShopApp backend. It provides insights into the application's performance and usage, helping administrators monitor and analyze system activity.

## Technology Stack

The Admin App is implemented using JSP M2 (Model 2) technology, providing a structured and efficient approach to Java web development. It leverages the following technologies and libraries:

- Java Server Pages (JSP)
- Servlets
- JavaBeans
- JSTL (JavaServer Pages Standard Tag Library)
- JDBC (Java Database Connectivity)
- HTML, CSS, and JavaScript
- Bootstrap or other UI libraries for design and styling


## AdminApp Setup

To run the "AdminApp" which is written using JSP M2 technologies, you can follow the steps below:

- Ensure that you have a Java Development Kit (JDK) installed on your machine. Follow the instructions to install the latest version of JDK for your platform from the official Oracle website -> [jdk docs](https://www.oracle.com/java/technologies/downloads/).

- Set up a web server that supports Java Servlet technology, such as Apache Tomcat or Jetty. Download and install the web server of your choice by following the instructions provided on their respective websites.

- Build the AdminApp project by compiling the Java source files and creating a WAR (Web Application Archive) file.

- Deploy the WAR file to the web server you set up in step 2. The specific steps for deploying a WAR file may vary depending on the web server you are using. Refer to the documentation of your chosen web server for detailed instructions on deploying a web application.

- Start the web server. Once the server is up and running, you can access the AdminApp by opening a web browser and entering the appropriate URL. The exact URL will depend on the configuration of your web server and the context path of the deployed AdminApp.

Please note that JSP M2 is an architectural pattern used in Java web development, and the specific steps for running the AdminApp may vary based on the project structure and setup. The above steps provide a general overview of the process. It's recommended to consult the documentation or tutorials specific to your JSP M2 framework or web server for more detailed instructions on running and deploying a JSP M2 application.