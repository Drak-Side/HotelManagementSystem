---

# Hotel Management System

This Java-based Hotel Management System allows you to manage various hotel operations such as customer information, room allocation, and employee management. The application is built using Java Swing for the GUI and MySQL for the backend database.

## Features

- **Customer Management**: Add, update, and manage customer details.
- **Room Allocation**: Automatic room assignment based on availability.
- **Employee Management**: Manage staff information and roles.
- **Database Integration**: All data is stored and managed in a MySQL database.

## Prerequisites

- **Java Development Kit (JDK)**: JDK 8 or above
- **MySQL**: Ensure MySQL is installed properly and running on port 3306 with the username `root` and password `root`.
- **MySQL Workbench**: For managing the database.
- **JavaFX SDK**: Required for GUI development and running the application.

## Setting Up the Project

1. **Clone the Repository:**

   `
   git clone https://github.com/Drak-Side/HotelManagementSystem.git
   cd HotelManagementSystem
   `

2. **Set Up the Database:**

   - Open MySQL Workbench and log in to your root account.
   - From the `File` menu, click on `Open SQL Script`.
   - Navigate to the `database` file located in the root of the cloned repository and select the `hotel.sql` file.
   - From the `Query` menu, select `Execute All` and wait for the execution to finish.

3. **Import the Project:**
   - Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
   - Ensure the JavaFX library is properly set up and configured.

4. **Run the Application:**

   - Compile and run the `Main.java` or any of the specific module classes like `CustomerInfo.java`.

## Running the Application via Command Line

1. Navigate to the directory where the compiled JAR file is located:

   `
   cd HotelManagementSystem/jar/
   `

2. Run the JAR file using the following command:

   `
   java -jar HotelManagementSystem.jar
   `

   You're all set!

## Usage

Use the GUI to navigate through different options like adding customers, assigning rooms, and managing staff.


## Acknowledgements

- Java Swing for GUI development
- MySQL for database management
- [Drak-Side](https://github.com/Drak-Side) for project development and maintenance

---
