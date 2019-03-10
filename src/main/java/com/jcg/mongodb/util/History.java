package com.jcg.mongodb.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class History {

	

	// Method to search a user in the mongodb
	public static String searchHistInDb(String loginId) {
		String user_found ="No History of Addresses";
		String db_name = "demo",
			   db_collection_name = "sample";
		String u="";
		
		MongoClient mongoClntObj = new MongoClient("localhost", 27017);
		// Get the mongodb connection
		MongoDatabase db = mongoClntObj.getDatabase(db_name);
		
		// Get the mongodb collection.
		MongoCollection<Document> col = db.getCollection(db_collection_name);
		MongoCollection<Document> d_col = db.getCollection("sampleCollection");

		// Get the particular record from the mongodb collection		
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("Mobile Number", loginId));
		

		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("$and", obj);
		
		FindIterable<Document> cursor = col.find(whereQuery);
		for(Document doc : cursor) {
			u=doc.getString("Unique_id_list");
			if(u==null || u.isEmpty()) {
				user_found="No History of Addresses";
			}else
			{
				String[] st1;
				st1=u.split(",");
				user_found="";	
				int[] result= new int[st1.length];
				for(int j=0;j<st1.length;j++){
					result[j] = Integer.parseInt(st1[j]);		
					List<BasicDBObject> obj1 = new ArrayList<BasicDBObject>();
					obj1.add(new BasicDBObject("Unique id", result[j]));
					
				// 	Form a where query
					BasicDBObject whereQuery1 = new BasicDBObject();
					whereQuery1.put("$and", obj1);
							
					FindIterable<Document> cursor1 = d_col.find(whereQuery1);
					for(Document doc1 : cursor1) {
						user_found=user_found+doc1.getInteger("Unique id").toString()+",";
						user_found=user_found+doc1.getInteger("Amount").toString()+",";
						user_found=user_found+doc1.getString("Order_date")+",";
						user_found=user_found+doc1.getString("Address1")+",";
						user_found=user_found+doc1.getString("Address2")+",";
						user_found=user_found+doc1.getString("Address3")+",";
						user_found=user_found+doc1.getString("Address4")+",";
						user_found=user_found+doc1.getString("Address5");
						user_found=user_found+" END ";
						
					}
				}			
			}
			}
		
		
		mongoClntObj.close();
		return user_found;
	}
}