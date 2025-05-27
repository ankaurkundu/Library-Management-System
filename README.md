A **Library Management System** built using **Java Swing** for the user interface and **MySQL** as the backend database. The system allows managing books, students, and book issue/return transactions with a user-friendly GUI.

---

## 🚩 Features

- **User Authentication:** Login and Signup functionality.
- **Manage Books:** Add, update, delete, and view books.
- **Manage Students:** Add, update, delete, and view student information.
- **Issue Books:** Issue books to students and track due dates.
- **Return Books:** Record book returns and calculate defaulters.
- **View Records:** View all issue/return records and defaulter lists.

---

## 🛠 Technology Stack

| Component          | Technology / Library                  |
|--------------------|-------------------------------------|
| GUI Framework      | Java Swing (with custom components)  |
| Database           | MySQL                               |
| Database Connectivity | JDBC                             |
| Query Language     | SQL                   |

---

---

## ⚙️ Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/LibraryManagementSystem.git
   Set up MySQL database:


2. Create a MySQL database named library_ms to store all library-related records.

3. Set up the required tables in the library_ms database based on your schema (like users, books, students, issue_book_details, etc.).

4. Update the database credentials in the Java files if your MySQL username or password differs from the default (root / no password).

5. Open the project in your preferred Java IDE, such as NetBeans, IntelliJ IDEA, or Eclipse.

6. Run the application by executing the LoginPage.java file to start the system.
