package com.example.hospital.dao;

import com.example.hospital.model.Doctor;
import com.example.hospital.config.MongoClientProvider;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class DoctorDaoImpl implements DoctorDao {
    private final MongoCollection<Document> collection;

    public DoctorDaoImpl(String dbName) {
        MongoClient client = MongoClientProvider.getClient();
        MongoDatabase db = client.getDatabase(dbName);
        this.collection = db.getCollection("doctors");
    }

    @Override
    public void create(Doctor doctor) throws DaoException {
        try {
            collection.insertOne(doctor.toDocument());
        } catch (Exception e) {
            throw new DaoException("Error creating doctor", e);
        }
    }

    @Override
    public Optional<Doctor> findByDoctorId(int doctorId) throws DaoException {
        try {
            Document doc = collection.find(eq("doctor_id", doctorId)).first();
            return Optional.ofNullable(Doctor.fromDocument(doc));
        } catch (Exception e) {
            throw new DaoException("Error finding doctor", e);
        }
    }

    @Override
    public List<Doctor> findAll() throws DaoException {
        try {
            List<Doctor> list = new ArrayList<>();
            for (Document doc : collection.find()) {
                list.add(Doctor.fromDocument(doc));
            }
            return list;
        } catch (Exception e) {
            throw new DaoException("Error retrieving doctors", e);
        }
    }

    @Override
    public void update(Doctor doctor) throws DaoException {
        try {
            collection.updateOne(eq("doctor_id", doctor.getDoctorId()),
                    new Document("$set", doctor.toDocument()));
        } catch (Exception e) {
            throw new DaoException("Error updating doctor", e);
        }
    }

    @Override
    public void delete(int doctorId) throws DaoException {
        try {
            collection.deleteOne(eq("doctor_id", doctorId));
        } catch (Exception e) {
            throw new DaoException("Error deleting doctor", e);
        }
    }
}
