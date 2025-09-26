package com.example.hospital.model;

import org.bson.Document;

public class Doctor {
    private int doctorId;
    private String firstName;
    private String lastName;
    private String specialty;

    public Doctor() {}

    public Doctor(int doctorId, String firstName, String lastName, String specialty) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
    }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public Document toDocument() {
        return new Document("doctor_id", doctorId)
                .append("first_name", firstName)
                .append("last_name", lastName)
                .append("specialty", specialty);
    }

    public static Doctor fromDocument(Document doc) {
        if (doc == null) return null;
        return new Doctor(
                doc.getInteger("doctor_id", 0),
                doc.getString("first_name"),
                doc.getString("last_name"),
                doc.getString("specialty")
        );
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + firstName + " " + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
