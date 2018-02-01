package com.code.mongodDemo;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App {

	public void testInsert() {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase db = client.getDatabase("user");
		MongoCollection<Document> collection = db.getCollection("user");
		Map<String, Object> map = new HashMap<>();
		map.put("name", "西门吹雪");
		map.put("age", 32);
		map.put("gender", "男");
		Map<String, Object> hobbyMap = new HashMap<>();
		hobbyMap.put("lang", "Java");
		hobbyMap.put("girl", "美女");
		map.put("hobby", hobbyMap);
		// 插入操作
		org.bson.Document doc = new org.bson.Document(map);
		collection.insertOne(doc);
		// 释放资源
		client.close();
	}
	

	public void testFindAll() {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase db = client.getDatabase("user");
		MongoCollection<Document> collection = db.getCollection("user");
		
		FindIterable<Document> it=collection.find();
		
		MongoCursor<Document> cursor=it.iterator();
		 while(cursor.hasNext()) {
			 Document doc=cursor.next();
			 String name=doc.getString("name");
			 Double age=doc.getDouble("age");
			 System.out.println(name+" , "+age);
		 }
	        client.close();

		
	}
	
	@Test
	public void testFindOne() {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase db = client.getDatabase("user");
		MongoCollection<Document> collection = db.getCollection("user");

		Bson filter =new BasicDBObject("_id",new ObjectId("5a732487e35a31317290ba8d")) ;
		FindIterable<Document> it=collection.find(filter);
		
		MongoCursor<Document> cursor=it.iterator();
		 while(cursor.hasNext()) {
			 Document doc=cursor.next();
			 String name=doc.getString("name");
			 System.out.println(name);
		//	 int age=doc.getInteger("age");
		//	 System.out.println(name+" , "+age);
		 }
		 
	        client.close();

	}

	public void testUpdate() {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase db = client.getDatabase("user");
		MongoCollection<Document> collection = db.getCollection("user");	
		Bson filter =new BasicDBObject("_id",new ObjectId("5a732487e35a31317290ba8d")) ;
        Map<String,Object> map=new HashMap<>();
        map.put("name", "小李");
        map.put("age", "17");
        Bson update=new BasicDBObject(map);
        
        collection.updateOne(filter, new BasicDBObject("$set", update));
         
        client.close();
		
		
	}
	
	public void testRemov() {
		
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase db = client.getDatabase("user");
		MongoCollection<Document> collection = db.getCollection("user");	
		
		collection.deleteOne(new BasicDBObject("_id",new ObjectId("5a732487e35a31317290ba8d")));
		 client.close();
		
	}
	
	
	

	
	
	
	
	
}
