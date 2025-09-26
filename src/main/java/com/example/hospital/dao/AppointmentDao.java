package com.example.hospital.dao;

import com.example.hospital.model.Appointment;
import java.util.List;
import java.util.Optional;

public interface AppointmentDao {
    void create(Appointment appointment) throws DaoException;
    Optional<Appointment> findByAppointmentId(int appointmentId) throws DaoException;
    List<Appointment> findAll() throws DaoException;
    void update(Appointment appointment) throws DaoException;
    void delete(int appointmentId) throws DaoException;
}
