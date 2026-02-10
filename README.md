
# Spring Boot RESTful API Assignment

**Student Name:** Irera Mukawera Jochebed  
**Student ID:** 27453  
**GitHub Repository:** [https://github.com/irerajochebed/restFull_api_27453](https://github.com/irerajochebed/restFull_api_27453)



## Overview

This project contains a comprehensive implementation of RESTful APIs using Spring Boot. The assignment covers five main questions plus a bonus question, each implementing different aspects of REST API development including CRUD operations, filtering, searching, and pagination.

All APIs follow REST best practices with proper HTTP methods (GET, POST, PUT, PATCH, DELETE) and appropriate HTTP status codes (200, 201, 204, 404).

---

##  Technologies Used

- **Java** 17 or higher
- **Spring Boot** 3.x
- **Spring Web** (REST Controllers)
- **Maven** (Dependency Management)
- **Postman** (API Testing)

---

## How to Run

### Prerequisites
- JDK 17 or higher installed
- Maven installed

### Steps to Run

1. **Build the project:**
   ```bash
   mvn clean install
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   
   OR run the main application class directly from your IDE.

3. **Access the APIs:**
   - Base URL: `http://localhost:8080`
   - All endpoints are accessible at this base URL

4. **Test using Postman or Browser:**
   - Import the provided Postman collection (if available)
   - Or manually test using the endpoints documented below

---

## API Endpoints Documentation

---

### Question 1: Library Book Management API

**Base URL:** `/api/books`

#### Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/books` | Get all books | 200 OK |
| GET | `/api/books/{id}` | Get book by ID | 200 OK / 404 Not Found |
| GET | `/api/books/search?title={title}` | Search books by title | 200 OK |
| GET | `/api/books/filter?year={year}` | Filter books by publication year | 200 OK |
| POST | `/api/books` | Add a new book | 201 Created |
| DELETE | `/api/books/{id}` | Delete a book | 204 No Content / 404 Not Found |

#### Sample Data
The system is pre-loaded with 3 books:
- Clean Code by Robert Martin (2008)
- The Pragmatic Programmer by Andrew Hunt (1999)
- Design Patterns by Gang of Four (1994)

#### Sample Request - Add New Book
```http
POST http://localhost:8080/api/books
Content-Type: application/json

{
    "title": "Java",
    "author": "Joshua Bloch",
    "isbn": "978",
    "publicationYear": 2018
}
```

#### Sample Response
```json
{
    "id": 4,
    "title": "Java",
    "author": "Joshua ",
    "isbn": "978",
    "publicationYear": 2018
}
```

---

### Question 2: Student Registration API

**Base URL:** `/api/students`

#### Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/students` | Get all students | 200 OK |
| GET | `/api/students/{studentId}` | Get student by ID | 200 OK / 404 Not Found |
| GET | `/api/students/major/{major}` | Get students by major | 200 OK |
| GET | `/api/students/filter?gpa={minGpa}` | Filter students by minimum GPA | 200 OK |
| POST | `/api/students` | Register a new student | 201 Created |
| PUT | `/api/students/{studentId}` | Update student information | 200 OK / 404 Not Found |

#### Sample Data
The system is pre-loaded with 5 students:
- Irera Jochebed - Computer Science (GPA: 5.7)
- Joyce Munezero - Nursing (GPA: 2.8)
- Ineza Lidia - Networking (GPA: 1.7)
- Ndiku Josue - Computer Science (GPA: 3.2)
- Igisubizo Isaac - Bigdata (GPA: 4.8)

#### Sample Request - Register New Student
```http
POST http://localhost:8080/api/students
Content-Type: application/json

{
    "firstName": "Sarah",
    "lastName": "Davis",
    "email": "sarah.davis@university.edu",
    "major": "Mathematics",
    "gpa": 3.9
}
```

#### Sample Response
```json
{
    "studentId": 6,
    "firstName": "Sarah",
    "lastName": "Davis",
    "email": "sarah.davis@university.edu",
    "major": "Mathematics",
    "gpa": 3.9
}
```

#### Testing Scenarios
```bash
# Get students by Computer Science major
GET http://localhost:8080/api/students/major/Computer Science

# Filter students with GPA >= 3.5
GET http://localhost:8080/api/students/filter?gpa=3.5
```

---

### Question 3: Restaurant Menu API

**Base URL:** `/api/menu`

#### Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/menu` | Get all menu items | 200 OK |
| GET | `/api/menu/{id}` | Get menu item by ID | 200 OK / 404 Not Found |
| GET | `/api/menu/category/{category}` | Get items by category | 200 OK |
| GET | `/api/menu/available?available={true/false}` | Get items by availability | 200 OK |
| GET | `/api/menu/search?name={name}` | Search items by name | 200 OK |
| POST | `/api/menu` | Add new menu item | 201 Created |
| PUT | `/api/menu/{id}/availability` | Toggle item availability | 200 OK / 404 Not Found |
| DELETE | `/api/menu/{id}` | Delete menu item | 204 No Content / 404 Not Found |

#### Sample Data
The system includes 8+ menu items across categories:
- **Appetizers:** Spring Rolls, Chicken Wings
- **Main Course:** Grilled Chicken, Beef Burger
- **Dessert:** Chocolate Cake, Ice Cream
- **Beverage:** Coca Cola, Fresh Juice

#### Sample Request - Add Menu Item
```http
POST http://localhost:8080/api/menu
Content-Type: application/json

{
    "name": "Grilled Salmon",
    "description": "Fresh Atlantic salmon with lemon butter sauce",
    "price": 24.99,
    "category": "Main Course",
    "available": true
}
```

---

### Question 4: E-Commerce Product API

**Base URL:** `/api/products`

#### Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/products` | Get all products | 200 OK |
| GET | `/api/products?page={page}&limit={limit}` | Get products with pagination | 200 OK |
| GET | `/api/products/{productId}` | Get product by ID | 200 OK / 404 Not Found |
| GET | `/api/products/category/{category}` | Get products by category | 200 OK |
| GET | `/api/products/brand/{brand}` | Get products by brand | 200 OK |
| GET | `/api/products/search?keyword={keyword}` | Search products | 200 OK |
| GET | `/api/products/price-range?min={min}&max={max}` | Filter by price range | 200 OK |
| GET | `/api/products/in-stock` | Get in-stock products | 200 OK |
| POST | `/api/products` | Add new product | 201 Created |
| PUT | `/api/products/{productId}` | Update product | 200 OK / 404 Not Found |
| PATCH | `/api/products/{productId}/stock?quantity={qty}` | Update stock quantity | 200 OK / 404 Not Found |
| DELETE | `/api/products/{productId}` | Delete product | 204 No Content / 404 Not Found |

#### Sample Data
The system includes 10+ products across categories:
- **Electronics:** MacBook Pro, iPhone, Samsung Galaxy
- **Clothing:** Nike Sneakers, Adidas T-Shirt
- **Books:** Clean Code, Design Patterns
- **Home & Garden:** Coffee Maker, Desk Lamp

#### Sample Request - Add Product
```http
POST http://localhost:8080/api/products
Content-Type: application/json

{
    "name": "iPhone 15 Pro",
    "description": "Latest flagship smartphone from Apple",
    "price": 1199.99,
    "category": "Electronics",
    "stockQuantity": 50,
    "brand": "Apple"
}
```

#### Sample Request - Pagination
```http
GET http://localhost:8080/api/products?page=1&limit=5
```

#### Sample Request - Price Range Filter
```http
GET http://localhost:8080/api/products/price-range?min=100&max=500
```

---

### Question 5: Task Management API

**Base URL:** `/api/tasks`

#### Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/tasks` | Get all tasks | 200 OK |
| GET | `/api/tasks/{taskId}` | Get task by ID | 200 OK / 404 Not Found |
| GET | `/api/tasks/status?completed={true/false}` | Get tasks by status | 200 OK |
| GET | `/api/tasks/priority/{priority}` | Get tasks by priority | 200 OK |
| POST | `/api/tasks` | Create new task | 201 Created |
| PUT | `/api/tasks/{taskId}` | Update task | 200 OK / 404 Not Found |
| PATCH | `/api/tasks/{taskId}/complete` | Mark task as completed | 200 OK / 404 Not Found |
| DELETE | `/api/tasks/{taskId}` | Delete task | 204 No Content / 404 Not Found |

#### Sample Request - Create Task
```http
POST http://localhost:8080/api/tasks
Content-Type: application/json

{
    "title": "Complete Spring Boot Assignment",
    "description": "Finish all 5 questions for RESTful API assignment",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-15"
}
```

#### Priority Levels
- `HIGH` - Urgent tasks
- `MEDIUM` - Normal priority
- `LOW` - Can be deferred

---

### Bonus: User Profile API

**Base URL:** `/api/users`

#### Endpoints

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/users` | Get all users | 200 OK |
| GET | `/api/users/{userId}` | Get user by ID | 200 OK / 404 Not Found |
| GET | `/api/users/username/{username}` | Search by username | 200 OK / 404 Not Found |
| GET | `/api/users/country/{country}` | Get users by country | 200 OK |
| GET | `/api/users/age-range?min={min}&max={max}` | Filter by age range | 200 OK |
| GET | `/api/users/active?active={true/false}` | Get users by active status | 200 OK |
| POST | `/api/users` | Create new user | 201 Created |
| PUT | `/api/users/{userId}` | Update user | 200 OK / 404 Not Found |
| PATCH | `/api/users/{userId}/activate` | Activate user | 200 OK / 404 Not Found |
| PATCH | `/api/users/{userId}/deactivate` | Deactivate user | 200 OK / 404 Not Found |
| DELETE | `/api/users/{userId}` | Delete user | 204 No Content / 404 Not Found |

#### Sample Request - Create User
```http
POST http://localhost:8080/api/users
Content-Type: application/json

{
    "username": "john_doe",
    "email": "john@example.com",
    "fullName": "John Doe",
    "age": 25,
    "country": "Rwanda",
    "bio": "Software developer passionate about Spring Boot",
    "active": true
}
```

#### Sample Response - With ApiResponse Wrapper
```json
{
    "success": true,
    "message": "User profile created successfully",
    "data": {
        "userId": 1,
        "username": "john_doe",
        "email": "john@example.com",
        "fullName": "John Doe",
        "age": 25,
        "country": "Rwanda",
        "bio": "Software developer passionate about Spring Boot",
        "active": true
    }
}
```

---

## Testing

### Using Postman

1. **Import Postman Collection** (if provided in repository)
2. **Set Base URL:** `http://localhost:8080`
3. **Test each endpoint** following the example above

### Testing Checklist

For each API, I verify:
-  GET all items returns 200 OK
-  GET by ID returns 200 OK for existing items
-  GET by ID returns 404 Not Found for non-existing items
-  POST creates new item and returns 201 Created
-  PUT updates existing item and returns 200 OK
-  PATCH updates specific fields and returns 200 OK
-  DELETE removes item and returns 204 No Content
-  Search/Filter endpoints return correct results
-  All required fields are validated

### Sample Test Flow

```bash
# 1. Start with GET all
GET http://localhost:8080/api/students

# 2. Create new resource
POST http://localhost:8080/api/students
# (with JSON body)

# 3. Verify creation
GET http://localhost:8080/api/students

# 4. Update resource
PUT http://localhost:8080/api/students/6
# (with JSON body)

# 5. Test filters
GET http://localhost:8080/api/students/filter?gpa=3.5

# 6. Delete resource
DELETE http://localhost:8080/api/students/6

# 7. Verify deletion
GET http://localhost:8080/api/students/6
# (should return 404)
```

---

## HTTP Status Codes Used

| Status Code | Meaning | When Used |
|-------------|---------|-----------|
| 200 OK | Success | GET, PUT requests successful |
| 201 Created | Resource created | POST requests successful |
| 204 No Content | Success with no body | DELETE requests successful |
| 404 Not Found | Resource not found | GET/PUT/DELETE on non-existing resource |
| 400 Bad Request | Invalid request | Missing required fields or invalid data |

---

##  Key Features Implemented

### 1. Proper REST Architecture
- Correct use of HTTP methods (GET, POST, PUT, PATCH, DELETE)
- Appropriate HTTP status codes
- RESTful URL naming conventions

### 2. Path Variables & Query Parameters
- `@PathVariable` for resource identifiers
- `@RequestParam` for filtering and searching
- Optional parameters for flexible queries

### 3. Request & Response Handling
- `@RequestBody` for POST/PUT operations
- `ResponseEntity<T>` for full HTTP response control
- JSON serialization/deserialization

### 4. Data Validation
- Field presence validation
- Type validation (String, Long, Double, Boolean)
- Business logic validation

### 5. Search & Filter Capabilities
- Search by single field (title, name, username)
- Filter by range (GPA, price, age)
- Filter by boolean (available, active, completed)
- Filter by category/type

### 6. Advanced Features
- Pagination (for Product API)
- Stock management (PATCH endpoint)
- Toggle operations (availability, active status)
- Response wrapper pattern (User Profile API)

---

## Development Notes

### Code Quality
- Clean, readable code with meaningful variable names
- Proper Java naming conventions followed
- Comprehensive console logging with emojis for easy debugging
- Well-organized package structure

### Best Practices
- Separation of concerns (Controller and Model layers)
- RESTful endpoint design
- Consistent error handling
- Comprehensive documentation

---

## Submission Details

- **Student Name:** Irera Mukawera Jochebed
- **Student ID:** 27453
- **Branch Name:** `restFull_api_27453`
- **Repository:** [https://github.com/irerajochebed/restFull_api_27453](https://github.com/irerajochebed/restFull_api_27453)




