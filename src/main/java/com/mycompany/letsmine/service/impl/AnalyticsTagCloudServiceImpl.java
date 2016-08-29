/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.service.impl;

import com.mycompany.letsmine.config.SpringMongoConfig;
import com.mycompany.letsmine.model.AnalyticsData;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.model.User;
import com.mycompany.letsmine.service.AnalyticsTagCloudService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.social.twitter.api.HashTagEntity;

/**
 *
 * @author michaelfouche
 */
public class AnalyticsTagCloudServiceImpl implements AnalyticsTagCloudService{

    private ApplicationContext mongoContext;
    private MongoOperations mongoOperation;
    private User mongoUser;
    private HashMap<String, Integer> tagCloudHashMap;
    private AnalyticsData analyticsData;    
    private ApplicationContext context;
//    analyticsData.
  //  private static final String COLLECTION = "tweetdata"; 
    
     public AnalyticsTagCloudServiceImpl() {         
         //hibernate        
    }
     /*
        context = new ClassPathXmlApplicationContext("beans.xml");       
        mongoUser = (User)context.getBean("MongoUser");
       */ 
        
        
        //analyticsData.setTagCloudHashMap(new HashMap<String, Integer>());
    
    @Override
    public String conductTagCloudAnalytics(String query) {
        String returnMessage = "";
        Integer value;
        mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");  
        ApplicationContext contextApp = new ClassPathXmlApplicationContext("beans.xml");
        analyticsData =  (AnalyticsData)contextApp.getBean("AnalyticsData");
//        try{
           //retrieve relevant data/tweetData objects
            String field = "tags";
            // DBObject dbObject = new BasicDBObject();
            // dbObject.put("searchQuery",query);
            // DBCursor dBCursor = findByQuery(dbObject);
            //  List<TweetData> tweetData =  dBCursor;
            List<TweetData> tweetData = findByQuery(query);
            //loop through them and do calculations            
            for(TweetData item:tweetData){
                System.out.println(item.getText());
                List<HashTagEntity> theHashtagList = item.getTags();                        
                for(HashTagEntity i:theHashtagList)
                {
                    String hashTagValue = i.getText();
                    
                    
                    if(analyticsData.getTagCloudHashMap().values().contains(hashTagValue)){//containskey
                    value = analyticsData.getTagCloudHashMap().get(hashTagValue);
                    }
                    else{
                        value = 0;
                    }
                    value ++;
                    analyticsData.getTagCloudHashMap().put(i.getText(), value);
                }
                
            }
            
            
            System.out.println("Loop over hashmap");
            Iterator<String> keySetIterator = analyticsData.getTagCloudHashMap().keySet().iterator(); 
            while(keySetIterator.hasNext()){ 
                String key = keySetIterator.next(); 
                System.out.println("key:" + key + "|:" + analyticsData.getTagCloudHashMap().get(key)); 
            }

            
            //store into new collection (mongo)
            mongoOperation.save(analyticsData);
            

            //report take it from there.
            returnMessage = "true";
 //       }
//        catch(Exception e){
//            returnMessage = ""+e;
//        }            
        return returnMessage;
    }
    
    @Override
    public List<TweetData> findByQuery(String query) {
        Query createQuery = new Query();
        createQuery.addCriteria(Criteria.where("searchQuery").in(query));
       List<TweetData> tweetData = mongoOperation.find(createQuery, TweetData.class);
        //System.out.println("findByQuery return size"+tweetData.size());
        return tweetData;
    }

    @Override
    public List findByField(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
