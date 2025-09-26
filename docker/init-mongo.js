// init-mongo.js
// This runs as root / during container initialization

db = db.getSiblingDB('hospital');

// Create collections (Mongo creates them on first insert)
// Seed patients
db.patients.insertMany([
    {
        patient_id: 1,
        first_name: "Juan",
        last_name: "Pérez",
        date_of_birth: "1980-05-15",
        gender: "M",
        phone_number: "3101234567",
        email: "juan.perez@example.com"
    },
    {
        patient_id: 2,
        first_name: "Laura",
        last_name: "Martínez",
        date_of_birth: "1995-03-10",
        gender: "F",
        phone_number: "3209876543",
        email: "laura.martinez@example.com"
    }
]);

db.doctors.insertMany([
    { doctor_id: 1, first_name: "Ana", last_name: "Gómez", specialty: "Cardiology" },
    { doctor_id: 2, first_name: "Carlos", last_name: "Ruiz", specialty: "Pediatrics" }
]);

db.appointments.insertMany([
    {
        appointment_id: 1,
        patient_id: 1,
        doctor_id: 1,
        appointment_datetime: ISODate("2025-09-20T10:00:00Z"),
        reason: "General checkup"
    },
    {
        appointment_id: 2,
        patient_id: 2,
        doctor_id: 2,
        appointment_datetime: ISODate("2025-09-21T14:30:00Z"),
        reason: "Headache"
    }
]);

// Create indexes for common queries
db.patients.createIndex({ patient_id: 1 }, { unique: true });
db.patients.createIndex({ email: 1 }, { unique: true });
db.doctors.createIndex({ doctor_id: 1 }, { unique: true });
db.appointments.createIndex({ appointment_id: 1 }, { unique: true });
db.appointments.createIndex({ appointment_datetime: -1 });
