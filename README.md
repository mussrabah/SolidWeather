# 🌦️ SolidWeather

A modern, simple weather application for Android — built from the ground up as a **practical case study** in **SOLID principles**, **Clean Architecture**, and **MVI**.

This project serves as the **official, open-source capstone** to my Medium series on applying SOLID principles in Android.

---

## 📱 App Showcase

![App Screenshot](screenshots/screen.gif)

---

## 🚀 The Goal: From Theory to Practice

The goal of this repository isn't just to build another weather app — it's to create a **clear, real-world example** of how to apply modern architectural patterns.

This project demonstrates how **Clean Architecture** (Data, Domain, Presentation) and **SOLID principles** work together to create an app that is:

- **Decoupled:** The UI, business logic, and data sources are all separate.  
- **Testable:** Each component can be unit-tested in isolation.  
- **Maintainable:** Adding new features (like a new API or a database) is easy and won't break existing code.  

---

## 🛠️ Tech Stack & Architecture

- **100% Kotlin**
- **Jetpack Compose** – Modern, declarative UI toolkit  
- **Clean Architecture** – Data / Domain / Presentation separation  
- **MVI (Model-View-Intent)** – Unidirectional data flow using State and Event  
- **Hilt** – Dependency injection  
- **Retrofit & Moshi** – Type-safe networking  
- **Kotlin Coroutines & Flow** – Asynchronous operations  
- **Accompanist** – System UI control & pull-to-refresh  

---

## 🏛️ Architecture Deep Dive

The app is split into **three core modules**, following Clean Architecture principles:

### 1. `:presentation` (The UI Layer)
**Contains:**  
`WeatherScreen.kt`, `WeatherViewModel.kt`, `WeatherState.kt`, `WeatherEvent.kt`, and all Composables.  

**Pattern:**  
**MVI** — The `WeatherScreen` observes a `WeatherState` from the `ViewModel` and sends `WeatherEvents` (like `RefreshWeather`) to it.  

**Dependencies:**  
Only depends on the `:domain` layer — it has no idea where the data comes from.

---

### 2. `:domain` (The Business Logic Layer)
**Contains:**  
`GetWeatherUseCase.kt`, `WeatherRepository.kt` (interface), and clean models (`WeatherInfo.kt`, `WeatherType.kt`).  

**Rules:**  
Pure Kotlin module — no Android dependencies.  

**Purpose:**  
Encapsulates business rules (e.g., “get weather from repository”).  
The `ViewModel` calls the `GetWeatherUseCase`.

---

### 3. `:data` (The Data Layer)
**Contains:**  
`WeatherRepositoryImpl.kt`, `WeatherApi.kt` (Retrofit), `WeatherDto.kt` (Moshi), `WeatherMapper.kt`.  

**Purpose:**  
Implements `WeatherRepository` from the domain layer.  
Fetches data from the network and maps DTOs to clean models used by the domain layer.

---

## 🔬 SOLID Principles in Practice

### **S — Single Responsibility Principle (SRP)**
- `GetWeatherUseCase.kt`: Fetches weather — nothing else.  
- `WeatherMapper.kt`: Maps network DTO → domain model.  
- `WeatherViewModel.kt`: Manages UI state — not data fetching.  

### **O — Open/Closed Principle (OCP)**
- `WeatherType.kt`: Sealed class open for extension but closed for modification.  
- `WeatherRepository.kt`: New implementations (e.g., `DatabaseWeatherRepositoryImpl`) can be added without modifying existing ones.  

### **L — Liskov Substitution Principle (LSP)**
- `WeatherRepository.kt`: The `GetWeatherUseCase` depends on the interface, not the implementation.  
  Substitute `WeatherRepositoryImpl` with a `FakeWeatherRepository` for testing seamlessly.  

### **I — Interface Segregation Principle (ISP)**
- `WeatherRepository.kt`: Focused and minimal.  
  Future features like city search would get a separate `CitySearchRepository`.  

### **D — Dependency Inversion Principle (DIP)**
- `WeatherViewModel.kt` depends on `GetWeatherUseCase` (an abstraction).  
- `GetWeatherUseCase.kt` depends on `WeatherRepository` (an abstraction).  
- `AppModule.kt` (Hilt) binds concrete implementations (`WeatherRepositoryImpl`) to abstractions.

---

## ▶️ How to Run

1. **Clone this repository:**
   ```bash
   git clone https://github.com/mussrabah/solidweather.git
2. Open the project in Android Studio.

3. Build and run.

> The app uses the **Open-Meteo API**, which does not require an API key.

## 📄 License
### MIT Licence
Copyright (c) 2025 Mustapha Rabah

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

