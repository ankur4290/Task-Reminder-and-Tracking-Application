# Task Reminder and Tracking Application

## Project Overview

- This project is a Java-based Task Reminder and Tracking Application developed using Core Java concepts and Spring Boot RESTful APIs.
- It allows users to create and manage tasks, schedule reminders, track task completion, generate task overviews, and export reports in CSV format.
- The application is designed to demonstrate timers, collections, concurrency, JDBC-based persistence, and RESTful API design, without relying on heavy frameworks beyond Spring Boot, as required by the assessment.

## Project Objectives

- Provide reliable task scheduling and timely reminders
- Track completed and pending tasks accurately
- Generate task overviews and exportable CSV reports
- Expose secure and testable RESTful APIs
- Demonstrate core Java concepts (collections, concurrency, JDBC)

---

## Modules Implemented

### Module 1: Task Organization Engine

Manages task creation, updates, listing, and persistence.

#### Features
- Add, fetch, update, and complete tasks
- JDBC-based database interaction using H2
- Layered architecture (Controller → Service → Repository)

#### APIs
- `POST /tasks` – Create a new task
- `GET /tasks` – Retrieve all tasks
- `PUT /completion/mark/{id}` – Mark a task as completed

#### Concepts Used
- Java Collections
- JDBC (JdbcTemplate)
- RESTful API design
- Exception handling

---

### Module 2: Scheduling System

Handles reminder scheduling and lifecycle tracking.

#### Features
- Reminder scheduling using ScheduledExecutorService
- Concurrent reminder tracking using thread-safe collections
- Reminder lifecycle status (pending / triggered)

#### APIs
- `POST /schedule/set` – Schedule a reminder for a task
- `GET /schedule/reminders/{taskId}` – Retrieve reminder status

#### Concepts Used
- Java Concurrency
- Timers and scheduling
- Thread-safe collections (ConcurrentHashMap)

---

### Module 3: Overview & CSV Integration Hub

Provides reporting, CSV exports, and email reminder integration.

#### Features
- Task overview (total, completed, pending)
- CSV export of task data
- Email reminder integration using JavaMail API (mocked)

#### APIs
- `GET /reports/overview` – Retrieve task summary
- `POST /reports/export` – Export tasks as a CSV report

#### CSV Details
- Timestamp-based file naming
- Safe handling of null values
- Proper CSV formatting with quoted text fields

#### Email Notes
- JavaMail API is integrated
- Email sending is mocked (printed to console) for security reasons
- This approach reflects common development and testing practices

---

### Module 4: Completion Tracker

Provides task completion insights and status analytics.

#### Features
- Track completion status of individual tasks
- Structured task status response

#### APIs
- `GET /completion/status/{taskId}` – Retrieve task status

#### Status Response Includes
- Task ID
- Completion flag
- Status (PENDING / COMPLETED)
- Due date

---

## Technology Stack

- Java 21
- Spring Boot
- JDBC (JdbcTemplate)
- H2 In-Memory Database
- Maven
- Postman (API testing)

---

## Database Configuration

- H2 in-memory database
- Automatically initialized on application startup
- H2 Console available at:

http://localhost:8081/h2-console

---

## How to Run the Application

### Prerequisites
- Java 21
- Maven

### Steps
```bash
mvn spring-boot:run
Application will start on:

http://localhost:8081

Testing
All APIs were tested using Postman, including:

Task creation and updates

Reminder scheduling and status tracking

CSV export generation

Completion status insights

GitHub Commit Strategy
Each major module was implemented, tested, and pushed separately

Commit messages clearly map to assessment modules

Maintains a clean and review-friendly commit history

Notes for Evaluators
Email reminders are mocked to prevent credential exposure

Core Java concepts (collections, concurrency, JDBC) are explicitly demonstrated

Application follows a clean, modular, and assessment-aligned design