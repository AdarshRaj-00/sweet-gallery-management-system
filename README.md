# Sweet Gallery - E-Commerce Management System

Sweet Gallery is a premium, full-stack e-commerce web application designed for browsing, managing, and purchasing authentic Indian sweets. Built using a robust Java **Spring Boot** backend, secure **Spring Security with JWT**, and a dynamic **React** frontend (powered by Vite and modern vanilla CSS styling).

---

## Features

### Authentication & Security
- **JWT-Based Authentication**: Secure stateless authentication using JSON Web Tokens.
- **Password Hardening**: Passwords safely hashed and validated via BCrypt.
- **Guest Browsing Mode**: Visitors can explore sweets, search, and filter without logging in.
- **Protected Actions**: Adding to cart or wishlist automatically prompts guests to log in/register.
- **Self-Healing Session**: Frontend interceptor detects expired tokens or database resets, automatically clearing the session and returning to Login.

### Shopping Cart System
- **Quantity Controls**: Adjust order quantities directly using `[-] quantity [+]` pill selectors on sweet cards.
- **Active Subtotal Calculations**: Instant subtotal updates and free delivery calculations.
- **Mock Checkout**: Address and contact form validation with a realistic order completion workflow.

### Sweets Catalog & Styling
- **Dynamic Search & Filtering**: Fast search bar filters by sweet names or categories (Milk Sweets, Bengali Sweets, Dry Fruits, Festival Sweets).
- **Aesthetic UI**: Crafted with a premium terracotta, cream, and saffron gold theme with elegant Google Typography (Plus Jakarta Sans and Playfair Display).
- **Smooth Animations**: Animated shimmer loading skeletons during data fetching and CSS-based toast notifications.

---

## Tech Stack

### Backend
- **Java 17 / 21**
- **Spring Boot 3.x**
- **Spring Security**
- **JJWT (JSON Web Token 0.12+)**
- **Spring Data JPA & Hibernate**
- **H2 In-Memory Database** (Auto-seeded on startup)

### Frontend
- **React 18**
- **Vite**
- **Axios** (With custom interceptor instances)
- **Vanilla CSS** (Theme tokens, glassmorphism, responsive grid layout)

---

## API Endpoints

### Authentication (`/api/auth/**`)
- `POST /api/auth/register` - Register a new customer
- `POST /api/auth/login` - Login to receive JWT token

### Sweets Inventory (`/api/sweets/**`)
- `GET    /api/sweets` - Fetch all sweets (Public)
- `POST   /api/sweets` - Add a new sweet (Admin only)
- `DELETE /api/sweets/{id}` - Delete a sweet (Admin only)

### Shopping Cart (`/api/cart/**` - Protected)
- `GET    /api/cart` - Retrieve authenticated user's cart items
- `POST   /api/cart/add` - Add item to cart (expects `{ sweetId, quantity }`)
- `PUT    /api/cart/update` - Update item quantity (expects `{ sweetId, quantity }`)
- `DELETE /api/cart/remove/{sweetId}` - Remove item from cart
- `DELETE /api/cart/clear` - Empty cart (Checkout completion)

---

## Setup & Installation Guide

### Prerequisites
- **JDK 17 or 21** installed.
- **Node.js** (v18+) installed.
- **Apache Maven** installed globally (or compile via your IDE).

### 1. Run the Backend
1. Navigate to the `backend` directory:
   ```bash
   cd backend
   ```
2. Build and run the Spring Boot app:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   *The backend will boot up on `http://localhost:8080`.*
   
> [!NOTE]
> **Auto-Seeding**: The backend automatically seeds 10 default sweets with high-definition CDN images, along with a demo customer account:
> - **Username**: `user`
> - **Password**: `user123`

### 2. Run the Frontend
1. Navigate to the `frontend` directory:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Vite development server:
   ```bash
   npm run dev
   ```
   *The frontend will run on `http://localhost:5173` (or the next available port like `5174`).*

---

## Architecture Layout

```
      [ Browser / React App ]
                 │
                 ▼ (Axios Instance with Bearer Token)
     [ JWT / CORS Security Filters ]
                 │
                 ▼
       [ Cart & Sweets Controllers ]
                 │
                 ▼
       [ Services (Business Logic) ]
                 │
                 ▼
       [ JPA Repositories ]
                 │
                 ▼
       [ H2 In-Memory Database ]
```

---

## Deployment Note
For production deployments (e.g. Render, Railway, AWS, Heroku):
1. **JWT Secret**: Hardcode a secure secret key or inject it using `System.getenv("JWT_SECRET")` inside the Spring Boot config.
2. **Database**: Swap the in-memory H2 database dependency with PostgreSQL or MySQL in `backend/src/main/resources/application.properties`.
