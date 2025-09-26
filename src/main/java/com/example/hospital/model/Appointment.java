package com.example.hospital.model;

import org.bson.Document;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String appointmentDatetime;
    private String reason;

    public Appointment() {}

    public Appointment(int appointmentId, int patientId, int doctorId, String appointmentDatetime, String reason) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDatetime = appointmentDatetime;
        this.reason = reason;
    }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public String getAppointmentDatetime() { return appointmentDatetime; }
    public void setAppointmentDatetime(String appointmentDatetime) { this.appointmentDatetime = appointmentDatetime; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Document toDocument() {
        return new Document("appointment_id", appointmentId)
                .append("patient_id", patientId)
                .append("doctor_id", doctorId)
                .append("appointment_datetime", appointmentDatetime)
                .append("reason", reason);
    }

    public static Appointment fromDocument(Document doc) {
        if (doc == null) return null;
        return new Appointment(
                doc.getInteger("appointment_id", 0),
                doc.getInteger("patient_id", 0),
                doc.getInteger("doctor_id", 0),
                doc.getString("appointment_datetime"),
                doc.getString("reason")
        );
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", datetime='" + appointmentDatetime + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
