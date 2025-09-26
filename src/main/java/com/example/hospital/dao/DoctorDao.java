package com.example.hospital.dao;

import com.example.hospital.model.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorDao {
    void create(Doctor doctor) throws DaoException;
    Optional<Doctor> findByDoctorId(int doctorId) throws DaoException;
    List<Doctor> findAll() throws DaoException;
    void update(Doctor doctor) throws DaoException;
    void delete(int doctorId) throws DaoException;
}
