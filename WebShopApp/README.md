# WebShopApp

WebShopApp is a web application that enables users to buy and sell products. It provides a platform where users can showcase their products, including a short title, detailed description, and category. The application supports various categories such as vehicles, real estate, computers, phones, and services, allowing users to find products based on their specific interests.

## Key Features

 - `Product Listing`: Users can browse through a wide range of products offered by different sellers. Each product is accompanied by essential information such as price, condition (new or used), location, images, seller details, and contact information.

- `Filtering and Searching`: The application allows users to easily filter and search for products based on specific criteria. This feature helps users find products that match their preferences quickly.

- `Pagination`: To manage large product catalogs efficiently, the application implements pagination, enabling users to navigate through different pages of products seamlessly.

- `Detailed Product View`: Users can view comprehensive details of a specific product, including all attributes and seller information. The detailed view provides a complete overview of the product, helping users make informed decisions.

- `User Registration and Authentication`: Users can create their accounts by providing necessary details such as name, address, username, password, and optional avatar. Account activation is done through a unique PIN code sent to the user's email. Once activated, users gain access to all the features and functionalities of the application.

- `User Profile Management`: Logged-in users can manage their profiles by updating personal information (excluding the username). Additionally, users can view their purchase history and manage their own product listings.

- `Buying and Selling`: Users can purchase products by selecting their preferred payment method, such as card payment or cash on delivery. The application tracks purchases and updates the status of products accordingly. Users can also create new product listings to sell items to other users. They have full control over their active and completed listings, with the ability to remove any of their own listings.

- `User Support`: Registered users can contact the customer support team through a dedicated form within the application. User information and message content are stored to ensure efficient communication and support.

- `Responsive Design`: The WebShopApp features a responsive design, providing a visually appealing and user-friendly experience across various devices.

## Technology Stack

The WebShopApp is built using the following technologies and frameworks:

- Frontend: Angular
- Backend: Spring Boot
- Database: MySQL
- Styling: Material Design

## WebShopApp Setup

To run the "WebShopApp" application, which consists of a backend Spring Boot application and a frontend Angular application, you can follow the steps below:

### Running the Backend Spring Boot Application

To run the backend Spring Boot application for the "WebShopApp":

- Ensure that you have a Java Development Kit (JDK) installed on your machine. Follow the instructions to install the latest version of JDK for your platform from the official Oracle website -> [jdk docs](https://www.oracle.com/java/technologies/downloads/)

- Open your preferred integrated development environment (IDE) such as IntelliJ IDEA.

- Import the backend project of "WebShopApp".

- Resolve project dependencies using Maven or Gradle to download the required libraries and frameworks.

- Configure the application properties or environment variables if necessary, such as the database connection details.

- Build the project to compile the source code.

- Run the main class that contains the entry point of the Spring Boot application. This will start the backend server and make it ready to receive requests.

Note: The specific steps may vary depending on your IDE and project setup. Refer to the documentation or tutorials provided by your IDE for more detailed instructions on running a Spring Boot application.

### Running the Frontend Angular Application

To run the frontend Angular application for the "WebShopApp":

- Open a command-line interface such as Terminal (macOS/Linux) or Command Prompt (Windows).

- Navigate to the root directory of the frontend project of "WebShopApp" (where the package.json file is located).

- Install the project dependencies by running the following command:

```bash
  npm install
```

This command will download and install the required packages specified in the package.json file.

- After the dependencies are installed, start the development server by running the following command:

```bash
  ng serve
```

This will compile the Angular application and launch a development server. You can access the "WebShopApp" in your web browser at http://localhost:4200.

Please note that these instructions provide a general overview, and the specific commands or configurations may vary based on your project structure and setup. Refer to the official documentation for Spring Boot and Angular for more detailed instructions and troubleshooting tips specific to the "WebShopApp."
