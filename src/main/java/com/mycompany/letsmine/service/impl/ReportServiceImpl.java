/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mycompany.letsmine.config.SpringMongoConfig;
import com.mycompany.letsmine.service.ReportService;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 *
 * @author michaelfouche
 */
public class ReportServiceImpl implements ReportService{
    
    
    @Override
    public HashMap<String, Integer> getTagCloud(String query, String user){
        ApplicationContext mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");
        
        DBObject dbObject = new BasicDBObject();
            dbObject.put("query",query);
        //loggedinuser and query
            
        List<HashMap<String, Integer>>  analyticsData = (mongoOperation.getCollection("AnalyticsData").distinct("tagCloudHashMap", dbObject));
        HashMap<String, Integer> tagCloudHashMap = analyticsData.get(0);
        return tagCloudHashMap;
    }
    
    @Override
    public List findByQuery(String field, DBObject dbObject, String collection) {
        ApplicationContext mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");
        
        return mongoOperation.getCollection(collection)
            .distinct(field, dbObject);
    }
    
}
