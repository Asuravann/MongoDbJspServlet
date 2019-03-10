package com.jcg.mongodb.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AddUser{

	// Method to search a user in the mongodb
	public static boolean searchUserInDb(String loginId, String loginPwd, String number) {
		boolean user_found = true;
		String  db_name = "demo",
			    db_collection_name = "sample";

		MongoClient mongo = new MongoClient("localhost" , 27017);
		
		// Get the mongodb connection
		MongoDatabase db = mongo.getDatabase(db_name);

		// Get the mongodb collection.
		MongoCollection<Document> col = db.getCollection(db_collection_name);
		MongoCollection<Document> col1 = db.getCollection("addressBook");
		
		
		// Get the particular record from the mongodb collection		
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("Mobile Number",number));
		
		
		
		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("$and", obj);
			
		FindIterable<Document> cursor = col.find(whereQuery);
		for(Document doc : cursor) {
			
				user_found = false;
		
		}
		
		//insert the user details into database
		if(user_found) {	
			Document d=new Document();
			d.append("Name", loginId);
			d.append("Mobile Number", number);
			d.append("Password",loginPwd);
			d.append("Unique_id_list", null);
			col.insertOne(d);
			
			Document d1=new Document();
			d1.append("Mobile Number", number);
			d1.append("count", "0");
			
			col1.insertOne(d1);
		}
		
		//close MongoDB connection
		mongo.close();
		
		return user_found;
	}
}