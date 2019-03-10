package com.jcg.mongodb.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class PaymentSuccess {

	// Method to make a connection to the mongodb server listening on a default port
	private static MongoClient getConnection() {
		int port_no = 27017;
		String url = "localhost";

		MongoClient mongoClntObj = new MongoClient(url, port_no);
		return mongoClntObj;
	}

	// Method to search a user in the mongodb
	public static String searchUserInDb(String phone) {
		String user_found ="Not_Found";
		String db_name = "demo",
				db_collection_name = "sample";

		// Get the mongodb connection
		MongoDatabase db = getConnection().getDatabase(db_name);

		// Get the mongodb collection.
		MongoCollection<Document> col = db.getCollection(db_collection_name);

		// Get the particular record from the mongodb collection		
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("Mobile Number", phone));
	

		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("$and", obj);
		
		FindIterable<Document> cursor = col.find(whereQuery);
		for(Document doc : cursor) {
			String s="";
			s=doc.getString("Unique_id_list").toString();
			String[] s1=s.split(",");
			user_found=s1[s1.length-1];
		}
		
		return user_found;
	}
	
	
	public static boolean addCardDetails(String phone,String card_name,String card_num,String exp_date) {
		String user_found ="Not_Found";
		String db_name = "demo",
				db_collection_name = "sample";

		// Get the mongodb connection
		MongoDatabase db = getConnection().getDatabase(db_name);

		// Get the mongodb collection.
		MongoCollection<Document> col = db.getCollection(db_collection_name);

		// Get the particular record from the mongodb collection		
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("Mobile Number", phone));
	

		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("$and", obj);
		
		
		List<BasicDBObject> obj1 = new ArrayList<BasicDBObject>();
		obj1.add(new BasicDBObject("Card Number", card_num));
		obj1.add(new BasicDBObject("Card Name", card_name));
		obj1.add(new BasicDBObject("Expiry Date", exp_date));
		
		
		FindIterable<Document> cursor = col.find(whereQuery);
		for(Document doc : cursor) {
			//col.updateOne(new BasicDBObject("Mobile Number", phone),
                   // new BasicDBObject("$set", obj1));
			//Bson query = new Document("Mobile Number",phone);
		   // Bson update = new Document("$set", obj1);
		   // col.findOneAndUpdate(query, update);
			//System.out.println(doc.getString("Name"));
			user_found=doc.getString("Unique_id_list");
			user_found=user_found+"";
			
		}
		
		return true;
	}
}