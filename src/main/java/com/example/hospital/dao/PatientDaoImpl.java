package com.example.hospital.dao;

import com.example.hospital.model.Patient;
import com.example.hospital.config.MongoClientProvider;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class PatientDaoImpl implements PatientDao {

    private final MongoCollection<Document> collection;

    public PatientDaoImpl(String dbName) {
        MongoClient client = MongoClientProvider.getClient();
        MongoDatabase db = client.getDatabase(dbName);
        this.collection = db.getCollection("patients");
    }

    @Override
    public void create(Patient patient) throws DaoException {
        try {
            Document doc = patient.toDocument();
            collection.insertOne(doc);
        } catch (MongoWriteException mwe) {
            throw new DaoException("Write error while creating patient: " + mwe.getMessage(), mwe);
        } catch (Exception e) {
            throw new DaoException("Unexpected error while creating patient", e);
        }
    }

    @Override
    public Optional<Patient> findByPatientId(int patientId) throws DaoException {
        try {
            Document doc = collection.find(eq("patient_id", patientId)).first();
            return Optional.ofNullable(Patient.fromDocument(doc));
        } catch (Exception e) {
            throw new DaoException("Error finding patient by id", e);
        }
    }

    @Override
    public List<Patient> findAll() throws DaoException {
        try {
            List<Patient> list = new ArrayList<>();
            for (Document doc : collection.find()) {
                list.add(Patient.fromDocument(doc));
            }
            return list;
        } catch (Exception e) {
            throw new DaoException("Error retrieving all patients", e);
        }
    }

    @Override
    public void update(Patient patient) throws DaoException {
        try {
            Document update = new Document("$set", patient.toDocument());
            collection.updateOne(eq("patient_id", patient.getPatientId()), update);
        } catch (Exception e) {
            throw new DaoException("Error updating patient", e);
        }
    }

    @Override
    public void delete(int patientId) throws DaoException {
        try {
            collection.deleteOne(eq("patient_id", patientId));
        } catch (Exception e) {
            throw new DaoException("Error deleting patient", e);
        }
    }
}
