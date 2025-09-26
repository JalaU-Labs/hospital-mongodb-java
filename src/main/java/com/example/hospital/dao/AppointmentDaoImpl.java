package com.example.hospital.dao;

import com.example.hospital.model.Appointment;
import com.example.hospital.config.MongoClientProvider;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class AppointmentDaoImpl implements AppointmentDao {
    private final MongoCollection<Document> collection;

    public AppointmentDaoImpl(String dbName) {
        MongoClient client = MongoClientProvider.getClient();
        MongoDatabase db = client.getDatabase(dbName);
        this.collection = db.getCollection("appointments");
    }

    @Override
    public void create(Appointment appointment) throws DaoException {
        try {
            collection.insertOne(appointment.toDocument());
        } catch (Exception e) {
            throw new DaoException("Error creating appointment", e);
        }
    }

    @Override
    public Optional<Appointment> findByAppointmentId(int appointmentId) throws DaoException {
        try {
            Document doc = collection.find(eq("appointment_id", appointmentId)).first();
            return Optional.ofNullable(Appointment.fromDocument(doc));
        } catch (Exception e) {
            throw new DaoException("Error finding appointment", e);
        }
    }

    @Override
    public List<Appointment> findAll() throws DaoException {
        try {
            List<Appointment> list = new ArrayList<>();
            for (Document doc : collection.find()) {
                list.add(Appointment.fromDocument(doc));
            }
            return list;
        } catch (Exception e) {
            throw new DaoException("Error retrieving appointments", e);
        }
    }

    @Override
    public void update(Appointment appointment) throws DaoException {
        try {
            collection.updateOne(eq("appointment_id", appointment.getAppointmentId()),
                    new Document("$set", appointment.toDocument()));
        } catch (Exception e) {
            throw new DaoException("Error updating appointment", e);
        }
    }

    @Override
    public void delete(int appointmentId) throws DaoException {
        try {
            collection.deleteOne(eq("appointment_id", appointmentId));
        } catch (Exception e) {
            throw new DaoException("Error deleting appointment", e);
        }
    }
}
