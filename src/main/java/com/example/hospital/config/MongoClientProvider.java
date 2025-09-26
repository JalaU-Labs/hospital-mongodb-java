package com.example.hospital.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.Objects;

/**
 * Singleton provider for MongoClient.
 * Reads MONGO_URI env var or builds default with admin credentials.
 */
public final class MongoClientProvider {

    private static volatile MongoClient instance;

    private MongoClientProvider() {}

    public static MongoClient getClient() {
        if (instance == null) {
            synchronized (MongoClientProvider.class) {
                if (instance == null) {
                    String envUri = System.getenv("MONGO_URI");
                    String uri;
                    if (envUri != null && !envUri.isBlank()) {
                        uri = envUri;
                    } else {
                        // default connection uses admin:admin123 on localhost
                        uri = "mongodb://admin:admin123@localhost:27017/?authSource=admin";
                    }
                    ConnectionString cs = new ConnectionString(uri);
                    MongoClientSettings settings = MongoClientSettings.builder()
                            .applyConnectionString(cs)
                            .build();
                    instance = MongoClients.create(settings);
                }
            }
        }
        return instance;
    }

    public static void close() {
        if (instance != null) {
            instance.close();
        }
    }
}
