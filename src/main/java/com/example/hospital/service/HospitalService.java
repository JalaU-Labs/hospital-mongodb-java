package com.example.hospital.service;

import com.example.hospital.dao.DaoException;
import com.example.hospital.dao.PatientDao;
import com.example.hospital.model.Patient;

import java.util.List;
import java.util.Optional;

public class HospitalService {

    private final PatientDao patientDao;

    public HospitalService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public void addPatient(Patient patient) throws DaoException {
        // Basic validation
        if (patient.getPatientId() <= 0) throw new DaoException("Invalid patient id");
        if (patient.getEmail() == null || patient.getEmail().isBlank()) throw new DaoException("Patient email is required");
        patientDao.create(patient);
    }

    public Optional<Patient> getPatient(int id) throws DaoException {
        return patientDao.findByPatientId(id);
    }

    public List<Patient> listPatients() throws DaoException {
        return patientDao.findAll();
    }

    public void updatePatient(Patient patient) throws DaoException {
        patientDao.update(patient);
    }

    public void deletePatient(int id) throws DaoException {
        patientDao.delete(id);
    }
}
