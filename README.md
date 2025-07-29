# Star Wars Web Application 🌟

> **Technical Test Implementation** - A modern web application displaying Star Wars data from SWAPI with microservices architecture.

## 📋 Overview

This project implements a Star Wars-themed web application that allows fans to explore information about **People** and **Planets** from the Star Wars universe. The application follows microservices architecture with a Spring Boot backend and Angular frontend, both containerized with Docker.

## ✨ Features

### Core Functionality
- 🚀 **Dual Data Views**: Display People and Planets from SWAPI (https://swapi.co/)
- 📄 **Smart Pagination**: 15 items per page with intuitive navigation
- 🔍 **Advanced Search**: Case-insensitive partial name matching (e.g., "sky" finds "Luke Skywalker")
- 📊 **Flexible Sorting**: Sort by "name" and "created" fields in both ascending/descending order
- 🏗️ **Clean Architecture**: Backend sorting follows Open-Closed Principle

### Technical Features
- 🐳 **Containerized**: Full Docker Compose deployment
- 🎯 **Microservices**: Separate frontend and backend services
- 🌐 **Modern Stack**: Angular 20 + Spring Boot + Java 21
- 🎨 **Star Wars UI**: Custom themed interface with authentic fonts and styling
- 📱 **Responsive Design**: Mobile-friendly layout
- ⚡ **Performance Optimized**: Efficient API calls and caching

## 🛠️ Technology Stack

### Frontend
- **Framework**: Angular 20.1.0
- **Language**: TypeScript 5.8.2
- **Styling**: SCSS with custom Star Wars theme
- **HTTP Client**: Angular HttpClient with RxJS
- **Container**: Node.js 20 Alpine

### Backend
- **Framework**: Spring Boot 3.x
- **Language**: Java 21
- **Build Tool**: Maven
- **Container**: OpenJDK 21 Slim
- **API**: RESTful services consuming SWAPI

### Infrastructure
- **Containerization**: Docker & Docker Compose
- **Networking**: Custom bridge network
- **Port Configuration**: Frontend (6969), Backend (8080)

## 🚀 Quick Start

### Prerequisites
- Docker and Docker Compose installed
- Git for cloning the repository

### Installation & Deployment

1. **Clone the repository**
```bash
git clone https://github.com/RubenDiazVieiro/starwars-app.git
cd starwars-app
```

2. **Deploy with Docker Compose**
```bash
# Build and start all services
docker-compose up --build

```

3. **Access the application**
- **Frontend**: http://localhost:6969
- **Backend API**: http://localhost:8080

## 🎨 UI/UX Features

### Star Wars Theme
- Custom Star Wars fonts (Star Jedi family)
- Authentic color palette (SW Yellow, Blue, etc.)
- Space-themed backgrounds and effects
- Smooth animations and transitions

### Responsive Design
- Mobile-first approach
- Tablet and desktop optimized
- Touch-friendly interactions
- Accessible navigation

## 🤝 Development Guidelines

### Code Quality Standards
- **Clean Code**: Meaningful names, small functions, clear structure
- **TypeScript**: Strong typing throughout the frontend
- **Java Best Practices**: Following Spring Boot conventions
- **SCSS Organization**: Modular stylesheets with variables

### Software Engineering Principles
- SOLID principles implementation
- DRY (Don't Repeat Yourself)
- KISS (Keep It Simple, Stupid)
- Test-Driven Development approach

## 🔮 Future Enhancements

This application serves as a solid foundation for a comprehensive Star Wars data explorer. Here are planned improvements and expansions:

### 🧪 Testing & Quality Assurance
- **Integration Testing with WireMock**: Implement comprehensive integration tests using WireMock to mock SWAPI responses, ensuring robust testing without external dependencies
- **End-to-End Testing**: Add Cypress or Playwright tests for complete user journey validation

### 🚀 Additional Features & Categories
- **New SWAPI Categories**:
    - 🚗 **Vehicles**: Explore land-based transportation from the Star Wars universe
    - 🚀 **Starships**: Detailed information about space vessels and their specifications
    - 🎬 **Films**: Movie information with character and planet connections
    - 🌌 **Species**: Alien races and their characteristics
- **Advanced Filtering**:
    - Multi-field search (search across multiple properties simultaneously)
    - Date range filtering for creation dates
    - Numeric range filters (height, mass, population, etc.)
    - Boolean filters (has_films, is_droid, etc.)
- **Enhanced Sorting**:
    - Multi-column sorting capabilities
    - Custom sort orders and saved preferences
    - Smart sorting for numeric vs. text fields

### 📊 Data & Analytics
- **Favorites System**: Allow users to bookmark favorite characters, planets, etc.
- **Comparison Tool**: Side-by-side comparison of different entities
- **Statistics Dashboard**: Visual charts