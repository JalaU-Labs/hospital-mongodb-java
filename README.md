# Hospital MongoDB Java Example

A Java 21 + Gradle project demonstrating how to connect to a **MongoDB NoSQL database** and perform **CRUD operations** (Create, Read, Update, Delete).
The project follows **good practices**:

* DAO pattern for data access.
* Service layer for business logic.
* Singleton for MongoDB client connection.
* Exception handling with custom exceptions.

It also includes a **Docker Compose configuration** that starts MongoDB with seeded data so you can run everything immediately.

---

## 📂 Project Structure

```
hospital-mongodb-java/
├── docker/
│   ├── docker-compose.yml
│   └── init-mongo.js
├── src/
│   └── main/java/com/example/hospital/
│       ├── Main.java
│       ├── config/MongoClientProvider.java
│       ├── dao/ (DAOs and implementations)
│       ├── model/ (POJOs for Patient, Doctor, Appointment)
│       └── service/HospitalService.java
├── build.gradle
├── settings.gradle
├── README.md
└── LICENSE
```

---

## 🚀 How to Clone and Run

1. **Clone the repository**:

```bash
git clone https://github.com/JalaU-Labs/hospital-mongodb-java.git
cd hospital-mongodb-java
```

2. **Start MongoDB with Docker Compose**:

```bash
cd docker
docker compose up -d
```

This will:

* Start MongoDB at `localhost:27017`
* Create the database `hospital`
* Insert sample patients, doctors, and appointments
* Create useful indexes

3. **Run the Java project**:

From the root folder:

```bash
./gradlew clean build
./gradlew run
```

On Windows:

```powershell
gradlew.bat clean build
gradlew.bat run
```

4. **Check the database** (optional):

```bash
docker exec -it hospital_mongodb mongosh -u admin -p admin123
use hospital
db.patients.find().pretty()
```

---

## ⚙️ Configuration

* Default Mongo URI: `mongodb://admin:admin123@localhost:27017/?authSource=admin`
* Default Database: `hospital`

You can override via environment variables:

```bash
export MONGO_URI="mongodb://admin:admin123@localhost:27017/?authSource=admin"
export MONGO_DB=hospital
./gradlew run
```

---

## 🧪 What the App Demonstrates

* **Patients CRUD** (via DAO + Service layer)
* **Doctors CRUD** (via DAO)
* **Appointments CRUD** (via DAO)

Example in `Main.java`:

* Insert → Query → Update → Delete patients, doctors, and appointments.
* All results are printed to the console.

---

## 📦 Dependencies

* Java 21
* Gradle
* MongoDB Driver Sync (`org.mongodb:mongodb-driver-sync`)
* SLF4J (simple logger)

---

## 🏗️ Design Patterns and Best Practices

* **Singleton** → `MongoClientProvider` ensures a single client instance.
* **DAO Pattern** → `PatientDao`, `DoctorDao`, `AppointmentDao`.
* **Service Layer** → Encapsulates validation and business logic (`HospitalService`).
* **Custom Exception** → `DaoException` for consistent error handling.

---

## 📜 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## ✨ Next Steps

* Add JUnit tests for DAOs and Service layer.
* Add REST API (Spring Boot or Micronaut) to expose CRUD endpoints.
* Use POJO Codec for cleaner mapping instead of manual `Document` conversion.
* Switch to async driver for high-performance reactive applications.

---
