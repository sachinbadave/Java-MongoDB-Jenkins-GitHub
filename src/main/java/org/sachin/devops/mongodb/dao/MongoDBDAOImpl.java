package org.sachin.devops.mongodb.dao;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class MongoDBDAOImpl {
	
	public MongoClient getDBClientConnection(String host, int port) {
		return new MongoClient(Arrays.asList(new ServerAddress(host, port)));
	}
	
	public MongoDatabase getDatabase(MongoClient mongoClient, String dbName) {
		return mongoClient.getDatabase(dbName);
	}
	
	public void insertOneDocument(MongoDatabase db, String collectionName, Document document) {
		db.getCollection(collectionName).insertOne(document);
	}
	
	public FindIterable<Document> getAllDocuments(MongoDatabase db, String collectionName) {
		FindIterable<Document> iterable = db.getCollection(collectionName).find();
		return iterable;
	}

}
