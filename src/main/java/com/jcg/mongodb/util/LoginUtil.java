package com.jcg.mongodb.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class LoginUtil {

	

	// Method to search a user in the mongodb
	public static String searchUserInDb(String loginId, String loginPwd) {
		String user_found ="Mobile Number not registered. Please SignUp!";
		String db_name = "demo",
			   db_collection_name = "sample";

		MongoClient mongoClntObj = new MongoClient("localhost", 27017);
		// Get the mongodb connection
		MongoDatabase db = mongoClntObj.getDatabase(db_name);

		// Get the mongodb collection.
		MongoCollection<Document> col = db.getCollection(db_collection_name);

		// Get the particular record from the mongodb collection		
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("Mobile Number", loginId));

		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("$and", obj);
		
		FindIterable<Document> cursor = col.find(whereQuery);
		for(Document doc : cursor) {
			if(doc.getString("Password").equals(loginPwd)) {
				user_found=doc.getString("Name");
			}else {
			user_found="Username or Password is wrong";
			}
		}
		mongoClntObj.close();
		
		return user_found;
	}
}