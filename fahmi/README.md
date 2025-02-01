# 📌 Project Name
A Spring Boot application for managing blog posts.

## 📖 Description
This project is a simple RESTful API built with Spring Boot that allows users to create, read, update, and delete blog posts. The backend is powered by MySQL and supports user authentication.

## 🛠 Tech Stack
- **Backend**: Spring Boot, Spring Data JPA, Hibernate
- **Database**: MySQL
- **Tools**: Docker, Maven, JUnit

## 🗄 Database Structure
The application uses a MySQL database with the following schema:

### **Post Table**
| Column      | Type                | Description                |
|------------|---------------------|----------------------------|
| id         | BIGINT (Primary Key) | Auto-incremented ID       |
| title      | VARCHAR(255)        | Title of the blog post     |
| content    | TEXT                | Content of the post        |
| created_at | TIMESTAMP           | Creation timestamp         |
| updated_at | TIMESTAMP           | Last update timestamp      |
| user_id    | BIGINT (Foreign Key) | References User table      |

### **Comment Table**
| Column      | Type                | Description                |
|------------|---------------------|----------------------------|
| id         | BIGINT (Primary Key) | Auto-incremented ID       |
| content    | TEXT                | Content of the comment    |
| created_at | TIMESTAMP           | Creation timestamp        |
| post_id    | BIGINT (Foreign Key) | References Post table     |

### **User Table**
| Column  | Type                | Description          |
|--------|---------------------|----------------------|
| id     | BIGINT (Primary Key) | Auto-incremented ID |
| name   | VARCHAR(255)        | User's full name    |
| email  | VARCHAR(255)        | User's email        |
| password | VARCHAR(255)      | User's password (hashed) |

## 🚀 API Endpoints

### 📌 Create a Post
**Request:**
```
POST /api/posts
Content-Type: application/json
{
  "title": "New Post",
  "content": "This is the content of the post."
}
```
**Response:**
```
201 Created
{
  "id": 1,
  "title": "New Post",
  "content": "This is the content of the post.",
  "created_at": "2024-02-01T12:00:00"
}
```

### 📌 Get All Posts
**Request:**
```
GET /api/posts
```
**Response:**
```
200 OK
[
  {
    "id": 1,
    "title": "First Post",
    "content": "This is my first post",
    "created_at": "2024-02-01T12:00:00"
  },
  {
    "id": 2,
    "title": "Second Post",
    "content": "Another post",
    "created_at": "2024-02-02T15:00:00"
  }
]
```

### 📌 Get a Single Post
**Request:**
```
GET /api/posts/{id}
```
**Response:**
```
200 OK
{
  "id": 1,
  "title": "First Post",
  "content": "This is my first post",
  "created_at": "2024-02-01T12:00:00"
}
```

### 📌 Update a Post
**Request:**
```
PUT /api/posts/{id}
Content-Type: application/json
{
  "title": "Updated Title",
  "content": "Updated content"
}
```
**Response:**
```
200 OK
{
  "id": 1,
  "title": "Updated Title",
  "content": "Updated content",
  "created_at": "2024-02-01T12:00:00"
}
```

### 📌 Delete a Post
**Request:**
```
DELETE /api/posts/{id}
```
**Response:**
```
204 No Content
```

## 💻 How to Run Locally

### **Prerequisites**
- Install **JDK 17+**
- Install **Maven**
- Install **Docker** (if using Docker for database)

### **Steps to Run**
1. **Clone the repository**
   ```sh
   git clone https://github.com/ramdhanifahmi/spring-blog.git
   ```

2. **Start MySQL with Docker**
   ```sh
   docker-compose up -d
   ```

3. **Configure the application properties** (edit `src/main/resources/application.properties` if needed):
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
   spring.datasource.username=root
   spring.datasource.password=rootpassword
   ```

4. **Run the application**
   ```sh
   mvn spring-boot:run
   ```

5. **Access the API**
   - Open [http://localhost:4040/api/posts](http://localhost:4040/api/posts) to see posts.
   - Use **Postman** or **cURL** for testing CRUD operations.