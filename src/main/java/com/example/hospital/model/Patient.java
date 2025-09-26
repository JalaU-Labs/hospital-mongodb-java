package com.example.hospital.model;

import org.bson.Document;

public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth; // ISO string for demo; in prod use java.time types
    private String gender;
    private String phoneNumber;
    private String email;

    public Patient() {}

    public Patient(int patientId, String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String email) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // getters & setters omitted for brevity (generate with your IDE)

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Document toDocument() {
        Document doc = new Document();
        doc.append("patient_id", patientId)
                .append("first_name", firstName)
                .append("last_name", lastName)
                .append("date_of_birth", dateOfBirth)
                .append("gender", gender)
                .append("phone_number", phoneNumber)
                .append("email", email);
        return doc;
    }

    public static Patient fromDocument(Document doc) {
        if (doc == null) return null;
        return new Patient(
                doc.getInteger("patient_id", 0),
                doc.getString("first_name"),
                doc.getString("last_name"),
                doc.getString("date_of_birth"),
                doc.getString("gender"),
                doc.getString("phone_number"),
                doc.getString("email")
        );
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + firstName + " " + lastName + '\'' +
                ", dob='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
