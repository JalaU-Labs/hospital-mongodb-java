package com.example.hospital.dao;

import com.example.hospital.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDao {
    void create(Patient patient) throws DaoException;
    Optional<Patient> findByPatientId(int patientId) throws DaoException;
    List<Patient> findAll() throws DaoException;
    void update(Patient patient) throws DaoException;
    void delete(int patientId) throws DaoException;
}
