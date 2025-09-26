package com.example.hospital;

import com.example.hospital.config.MongoClientProvider;
import com.example.hospital.dao.DaoException;
import com.example.hospital.dao.PatientDao;
import com.example.hospital.dao.PatientDaoImpl;
import com.example.hospital.model.Patient;
import com.example.hospital.service.HospitalService;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String dbName = System.getenv().getOrDefault("MONGO_DB", "hospital");
        PatientDao patientDao = new PatientDaoImpl(dbName);
        HospitalService service = new HospitalService(patientDao);

        try {
            System.out.println("=== Creating a new patient ===");
            Patient p = new Patient(3, "Miguel", "Torres", "1990-01-01", "M", "3000000000", "miguel.torres@example.com");
            service.addPatient(p);
            System.out.println("Created patient: " + p);

            System.out.println("\n=== Listing all patients ===");
            List<Patient> patients = service.listPatients();
            patients.forEach(System.out::println);

            System.out.println("\n=== Reading patient id=3 ===");
            Optional<Patient> found = service.getPatient(3);
            found.ifPresent(System.out::println);

            System.out.println("\n=== Updating patient id=3 phone ===");
            p.setPhoneNumber("3112223333");
            service.updatePatient(p);
            System.out.println("Updated: " + service.getPatient(3).orElse(null));

            System.out.println("\n=== Deleting patient id=3 ===");
            service.deletePatient(3);
            System.out.println("After delete, exists? " + service.getPatient(3).isPresent());

        } catch (DaoException e) {
            System.err.println("Error during DAO operations: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the shared Mongo client
            MongoClientProvider.close();
        }
    }
}
