# Database Setup

Ensure that you have a MySQL database server installed and running on your local machine. You can download and install MySQL Community Edition from the official MySQL [website](https://dev.mysql.com/downloads/)

To set up the database for your project, execute the provided SQL script, using `script.sql` file, using a MySQL client. You can run the script using the following command:

```bash
  mysql -u <username> -p <database_name> < script.sql
```
Replace `<username>` with your MySQL username and `<database_name>` with the name of your database. Enter the MySQL password when prompted.
  
To populate your MySQL database with data, you can use the `data.sql` file, using a MySQL client. The `data.sql` script contains SQL statements to insert data into the respective tables.
  
```bash
  mysql -u <username> -p <database_name> < data.sql
```
                                                   
Make sure to replace `<username>` with your MySQL username, `<database_name>` with the name of your database, and data.sql with the actual name of your script if it differs. 

In this folder, you will also find an image (conceptual_model.png) that illustrates the conceptual model of the web shop application. This model provides an overview of the database structure and the relationships between different entities. You can refer to this image to better understand the database design.