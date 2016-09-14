/**
 * 
 */
package org.sachin.devops.mongodb.trials;

import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;
import org.sachin.devops.mongodb.dao.MongoDBDAOImpl;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

/**
 * @author Sachin Badave
 *
 */
public class Trial {

	private static String HOST="192.168.30.15";//"127.0.0.1";
	private static int PORT=27017;
	
	private MongoDBDAOImpl dbDAOImpl;
	private Document document;
	private MongoClient mongoClient;
	private MongoDatabase mongoDB;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Trial trial = new Trial();
		trial.getDBDAO();
		trial.getMongoClient();
		trial.getDatabase();
		trial.prepareDocument();
		//trial.insertDocument();
		trial.printAllDocuments();		
	}
	
	public void getDBDAO() {
		dbDAOImpl = new MongoDBDAOImpl();
		//return dbDAOImpl;
	}
	
	public void getMongoClient() {
		mongoClient=dbDAOImpl.getDBClientConnection(HOST, PORT);
		//return mongoClient;
	}
	
	public void getDatabase() {
		mongoDB = dbDAOImpl.getDatabase(mongoClient, "test");
	}
	
	public void prepareDocument() {
		try	{
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
			document = new Document("address",
	                new Document()
	                        .append("street", "2 Avenue")
	                        .append("zipcode", "10075")
	                        .append("building", "1480")
	                        .append("coord", asList(-73.9557413, 40.7720266)))
	                .append("borough", "Manhattan")
	                .append("cuisine", "Italian")
	                .append("grades", asList(
	                        new Document()
	                                .append("date", format.parse("2014-10-01T00:00:00Z"))
	                                .append("grade", "A")
	                                .append("score", 11),
	                        new Document()
	                                .append("date", format.parse("2014-01-16T00:00:00Z"))
	                                .append("grade", "B")
	                                .append("score", 17)))
	                .append("name", "Vella")
	                .append("restaurant_id", "41704620");
		}catch(Exception e)	{
			e.printStackTrace();
		}
	}
	
	public void insertDocument() {
		dbDAOImpl.insertOneDocument(mongoDB, "restaurants", document);
	}
	
	public void printAllDocuments() {
		FindIterable<Document> iterable = dbDAOImpl.getAllDocuments(mongoDB, "restaurants");
		iterable.forEach(new Block<Document>() {
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}

}
