package com.jcg.mongodb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class AddAddress {
	

		// Method to search a user in the mongodb
		public static boolean addAddress(String phone,String add1, String add2, String add3, String add4, String add5 ,Boolean sign,String from_add,String slot) {
			boolean user_found=false;
			String db_name = "demo",
					db_collection_name = "sampleCollection";
			int old_id=0;
			int total=0;
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar cal = Calendar.getInstance();
	        Date date = cal.getTime();
	        cal.setTime(date);
	        cal.add(Calendar.DAY_OF_MONTH,0);
	        date = cal.getTime();
	        String today=dateFormat.format(date);
			
			MongoClient mongoClntObj = new MongoClient("localhost", 27017);
			// Get the mongodb connection
			MongoDatabase db = mongoClntObj.getDatabase(db_name);

			// Get the mongodb collection.
			MongoCollection<Document> col = db.getCollection(db_collection_name);
			MongoCollection<Document> user_col = db.getCollection("sample");
			
			
			
			
			//find unique id
			FindIterable<Document> cur = col.find();
			for(Document d1 : cur) {
				if(d1.getInteger("Unique id")>old_id) {
					old_id=d1.getInteger("Unique id");
				}
			}
			
			// Get the particular record from the mongodb collection		
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(new BasicDBObject("Mobile Number",phone));
								
			// Form a where query
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("$and", obj);
					
			//find the user doc		
			FindIterable<Document> cursor = user_col.find(whereQuery);
			for(Document doc : cursor) {
				user_found=true;
				if(user_found) {
						String old_list=doc.getString("Unique_id_list");
						if(old_list=="" || old_list==null || old_list==" ") {
							old_list=(old_id+1)+"";
						}else{
							old_list=old_list+","+(old_id+1);
							
						}
						
					    Bson query = new Document("Mobile Number",phone);
					    Bson update = new Document("$set", 
					       			new Document("Unique_id_list", old_list));
					    user_col.findOneAndUpdate(query, update);
					    break;
				
					}
			}
			
			if(!add1.isEmpty()) {
				total+=1;
			}if(!add2.isEmpty()) {
				total+=1;
			}if(!add3.isEmpty()) {
				total+=1;
			}if(!add4.isEmpty()) {
				total+=1;
			}if(!add5.isEmpty()) {
				total+=1;
			}
			total=total*15;
			//add the address details to col
			Document d_add=new Document();	
					d_add.append("Unique id", old_id+1);
					d_add.append("Address1", add1);
					d_add.append("Address2",add2);
					d_add.append("Address3",add3);
					d_add.append("Address4",add4);
					d_add.append("Address5",add5);
					d_add.append("Sign Required",sign);
					d_add.append("From_Address", from_add);
					d_add.append("Amount", total);
					d_add.append("Time Slot", slot);
					d_add.append("Order_date", today);
			col.insertOne(d_add);
			
			
			mongoClntObj.close();
			return user_found;
		}
		
		public static boolean addressBook(String phone,String add) {
			boolean user_found=false;
			String db_name = "demo",
					db_collection_name = "addressBook";
			
			
			MongoClient mongoClntObj = new MongoClient("localhost", 27017);
			// Get the mongodb connection
			MongoDatabase db = mongoClntObj.getDatabase(db_name);

			// Get the mongodb collection.
			MongoCollection<Document> col = db.getCollection(db_collection_name);
			
			
			
			
		
			// Get the particular record from the mongodb collection		
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(new BasicDBObject("Mobile Number",phone));
								
			// Form a where query
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("$and", obj);
					
			//find the user doc		
			FindIterable<Document> cursor = col.find(whereQuery);
			for(Document doc : cursor) {
				
						String count=doc.getString("count");
						int c = Integer.parseInt(count);
						boolean flag=true;
						if(c==0) {
							c=c+1;
							count=c+"";
						    Bson query = new Document("Mobile Number",phone);
						    Bson update = new Document("$set", 
						       			new Document(count , add));
						    col.findOneAndUpdate(query, update);
						    update = new Document("$set", 
					       			new Document("count" , count));
						    col.findOneAndUpdate(query, update);
						    break;
						}else {
						
						for(int i=c;i>0;i--) {
							String j=i+"";
							
							String s=doc.get(j).toString();
							if((s.equalsIgnoreCase(add))){
								flag=false;
								break;
							}
							
						}
						if(flag) {
							int c1=c+1;
							count=c1+"";
						    Bson query = new Document("Mobile Number",phone);
						    Bson update = new Document("$set", 
						       			new Document(count , add));
						    col.findOneAndUpdate(query, update);
						    update = new Document("$set", 
					       			new Document("count" , count));
						    col.findOneAndUpdate(query, update);
						    break;
						}
						}
				
					break;
			}
			
			
			
			mongoClntObj.close();
			return user_found;
		}
		
		
		public static String getAddress(String phone) {
			
			String db_name = "demo",
					db_collection_name = "addressBook";
			
			
			MongoClient mongoClntObj = new MongoClient("localhost", 27017);
			// Get the mongodb connection
			MongoDatabase db = mongoClntObj.getDatabase(db_name);

			// Get the mongodb collection.
			MongoCollection<Document> col = db.getCollection(db_collection_name);
			
			
			
			String list="";
		
			// Get the particular record from the mongodb collection		
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(new BasicDBObject("Mobile Number",phone));
								
			// Form a where query
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("$and", obj);
					
			//find the user doc		
			FindIterable<Document> cursor = col.find(whereQuery);
			for(Document doc : cursor) {
				
						String count=doc.getString("count");
						int c = Integer.parseInt(count);
						for(int j=c;j>0;j--) {
							list=list+doc.get(j+"").toString()+",";
							//System.out.println(doc.get(j+"").toString());
							
						}
					
			}
			//System.out.println(list);
			
			
			mongoClntObj.close();
			return list;
		}
		
		
public static String removeAddress(String phone) {
			
			String db_name = "demo",
					db_collection_name = "sample";
			
			
			MongoClient mongoClntObj = new MongoClient("localhost", 27017);
			// Get the mongodb connection
			MongoDatabase db = mongoClntObj.getDatabase(db_name);

			// Get the mongodb collection.
			MongoCollection<Document> col = db.getCollection(db_collection_name);
			MongoCollection<Document> col1 = db.getCollection("sampleCollection");
			
			
			String list="";
			int num=0;
		
			// Get the particular record from the mongodb collection		
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(new BasicDBObject("Mobile Number",phone));
								
			// Form a where query
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("$and", obj);
					
			//find the user doc		
			FindIterable<Document> cursor = col.find(whereQuery);
			for(Document doc : cursor) {
				//System.out.println("i am here");
				String l1="";
				l1=doc.get("Unique_id_list").toString();
				num=l1.lastIndexOf(',');
				list=l1.substring(num+1);
				
				String l="";
				l=l1.substring(0, num);
				Bson query = new Document("Mobile Number",phone);
			    Bson update = new Document("$set", 
		       			new Document("Unique_id_list" , l));
			    col.findOneAndUpdate(query, update);
			}
			//System.out.println(num);
			num=Integer.parseInt(list);
			List<BasicDBObject> obj1 = new ArrayList<BasicDBObject>();
			obj1.add(new BasicDBObject("Unique id",num));
								
			// Form a where query
			BasicDBObject whereQuery1 = new BasicDBObject();
			whereQuery1.put("$and", obj1);
					
			//find the user doc		
			FindIterable<Document> cursor1 = col1.find(whereQuery1);
			for(Document doc : cursor1) {
				
				col1.deleteOne(whereQuery1);
			}
			mongoClntObj.close();
			return list;
		}
}
