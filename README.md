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

Create a database named library_ms.

Create required tables (users, books, students, issue_book_details, etc.) as per your schema.

Configure database credentials:

Update the MySQL username and password in the Java files if different from defaults (root / empty password).

Open the project in an IDE:

Use NetBeans, IntelliJ IDEA, or Eclipse.

Run the application:

Start by running LoginPage.java.

