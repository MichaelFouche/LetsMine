/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.service.impl;

import com.mongodb.DBObject;
import com.mycompany.letsmine.config.SpringMongoConfig;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.model.User;
import com.mycompany.letsmine.service.TweetDataService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 *
 * @author michaelfouche
 */

public class TweetDataServiceImpl implements TweetDataService{

//    private ApplicationContext mongoContext;
//    private MongoOperations mongoOperation;
//    private User mongoUser;    
//    private ApplicationContext context;
     
    private static final String COLLECTION = "tweetdata";      
    
    @Override
    public List<TweetData> getAllTweets() {
        return getTweetData();
    }

    public TweetDataServiceImpl() {        
        //Hibernate
    }
        
    private List<TweetData> getTweetData(){
        ApplicationContext mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");
        
        List<TweetData> tweetData = new ArrayList<TweetData>();
        tweetData = mongoOperation.findAll(TweetData.class);
        return tweetData;
    }
    
    public boolean saveTweet(TweetData tweetData){
        try{
            ApplicationContext mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
            MongoOperations mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");
            mongoOperation.save(tweetData);  
            
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean deleteUserQuery(String user, String query){
        
        return false;
    }

    
    @Override
    public List findByField(String field) {
        ApplicationContext mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");
        
        return mongoOperation.getCollection(COLLECTION).distinct(field);
    }

    @Override
    public List findByQuery(String field, DBObject dbObject, String collection) {
        ApplicationContext mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");
        
        return mongoOperation.getCollection(collection)
            .distinct(field, dbObject);
    }

    
}
