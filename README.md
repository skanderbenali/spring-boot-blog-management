# Blog Management Application

## Overview
Welcome to the Blog Management Application! This is a backend system built with Spring Boot that provides robust features for managing blog posts, comments, ratings, and user interactions. The application includes modules for banning users, filtering bad words, and recognizing sentiment in post text.

# Blog Management Application

## Overview
Welcome to the Blog Management Application! This is a backend system built with Spring Boot that provides robust features for managing blog posts, comments, ratings, and user interactions. The application includes modules for banning users, filtering bad words, and recognizing sentiment in post text.

## Features
- **Post Management**: Create, read, update, and delete blog posts.
- **Comments**: Add, edit, and delete comments on posts.
- **Ratings**: Rate posts to provide feedback.
- **Ban Module**: Ban users based on predefined rules.
- **Bad Word Filter**: Automatically detect and filter inappropriate language.
- **Sentiment Recognition**: Analyze the sentiment of the post text to understand user emotions.

## Technologies Used
- **Spring Boot 2.6.4**: Backend framework for building the application.
- **Spring Data JPA**: For database interactions.
- **Spring Web**: For building RESTful APIs.
- **Spring Web Services**: For SOAP-based web services.
- **MySQL**: Relational database to store data.
- **Java 8**: Programming language used.
- **Lombok**: To reduce boilerplate code.
- **Commons IO**: For IO operations.

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven
- MySQL

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/skanderbenali/spring-boot-blog-management.git
   cd spring-boot-blog-management
   
2. **Configure the database:**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/shevoice_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   
3. **Build the application:**
   ```bash
   mvn clean install

4. **Run the application::**
   ```bash
   mvn spring-boot:run

   
***Bad Word Filter***
The application automatically filters out inappropriate language in posts and comments using a predefined list of bad words.

***Sentiment Recognition***
The sentiment recognition module analyzes the text of posts to determine the sentiment (positive, neutral, negative) using natural language processing techniques.

***Contributing***
Contributions are welcome! Please create a pull request with detailed information about the changes.

***Contact***
For any inquiries or questions, please contact me at dev.skander.benali@gmail.com.

Thank you for using the Blog Management Application!








