Student Performance Monitoring System
A simple web-based system built with Java, SQL, HTML, and CSS to help teachers monitor and manage student performance. Teachers can add student records, assign subject scores, and view the academic performance of each student.

Table of Contents
Features
Technologies Used
Installation
Usage
Database Schema
Screenshots
License
Features
Add Student: Register new students with name and class details.
Add Score: Assign a score to a student for a specific subject.
View Scores: Retrieve and display all scores for a given student.
Technologies Used
Backend: Java Servlet (with JDBC for database connectivity)
Frontend: HTML and CSS
Database: MySQL (or any SQL-based RDBMS)
Server: Apache Tomcat (or any compatible Java servlet container)
Installation
Prerequisites
Java JDK (version 8 or above).
MySQL server or any SQL-based RDBMS.
Apache Tomcat (or any compatible servlet container).

Steps
1. Clone the repository:
2. Set up the Database:
   Open MySQL and create a database called StudentPerformance.
   Run the following SQL commands to create the required tables:
   sql
   Copy code
3. Configure Database Connection:
   Open StudentServlet.java and update the database URL, username, and password as per your MySQL setup:
4.Deploy the Application:
  Package the application as a .war file and deploy it to your Tomcat server.
  Alternatively, if you're using an IDE like Eclipse, add the project to your Tomcat server and run it.
  
5.Access the Application:
  Open your web browser and go to http://localhost:8080/student-performance-monitoring-system/index.html.


Usage
Add a Student:

1. In the "Add Student" section, fill in the student's name and class, then submit.
   Add a Score:

2. In the "Add Score" section, enter the student ID, subject, and score, then submit.
   View Scores:

3. Enter the student ID in the "View Scores" section to display all subjects and scores for that student.

Database Schema

1.students table:
student_id: INT, primary key, auto-increment.
name: VARCHAR(100), student’s name.
class: VARCHAR(20), class of the student.

2.scores table:
score_id: INT, primary key, auto-increment.
student_id: INT, foreign key referencing students(student_id).
subject: VARCHAR(50), subject name.
score: INT, score obtained in the subject.
