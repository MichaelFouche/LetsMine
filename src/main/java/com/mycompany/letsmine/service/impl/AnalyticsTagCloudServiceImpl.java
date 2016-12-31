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
    //private AnalyticsData analyticsData;    
    
     public AnalyticsTagCloudServiceImpl() {         
         //hibernate        
    }
     
    public List<TweetData> getQueryTweets(String query){
        mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");  
        
        return findByQuery(query); 
    }    
     
    public AnalyticsData doTagCloudAnalytics(List<TweetData> tweetData, String query, String user){
        ApplicationContext contextApp = new ClassPathXmlApplicationContext("beans.xml");
        AnalyticsData analyticsData =  (AnalyticsData)contextApp.getBean("AnalyticsData");
        analyticsData.setTagCloudHashMap(new HashMap<String, Integer>());
        Integer aMentionCount;  
        for(TweetData item:tweetData){
            if(item.getSearchQuery().equals(query)&& item.getLetsMineUser().equals(user))
            {//We have an item from this query which should be analysed                                      
                //System.out.println(item.getLetsMineUser()+"QUERY "+item.getSearchQuery()+ "TEXT"+item.getText());
                List<HashTagEntity> theHashtagList = item.getTags();                     
                for(HashTagEntity aMention:theHashtagList)
                {//loop through all the hashtags for that tweet
                    String aMentionText = aMention.getText();

                    aMentionCount = 0;

                    if(!analyticsData.getTagCloudHashMap().isEmpty())
                    {//If there is data in the list already
                        System.out.println("InFOR"+aMentionText);
                        if(analyticsData.getTagCloudHashMap().containsKey(aMentionText))
                        {//containskey... aka we need to +1 instead of just put in map
                            aMentionCount = analyticsData.getTagCloudHashMap().get(aMentionText);
                            System.out.println("value:"+aMentionCount);
                        }
                    }
                    aMentionCount +=1;
                    analyticsData.getTagCloudHashMap().put(aMentionText, aMentionCount);
                }
            }
        }
        return analyticsData;
    }
    public void displayResultsInConsole(AnalyticsData analyticsData){
        System.out.println("Loop over hashmap");
        Iterator<String> keySetIterator = analyticsData.getTagCloudHashMap().keySet().iterator(); 
        while(keySetIterator.hasNext()){ 
            String key = keySetIterator.next(); 
            System.out.println("key:" + key + "|:" + analyticsData.getTagCloudHashMap().get(key)); 
        }
    }
    
    public void saveResult(AnalyticsData analyticsData){
        mongoOperation.save(analyticsData);
    }
        
    
    public String conductTagCloudAnalytics(String query, String user) {
        try{   
            AnalyticsData analyticsData;
            List<TweetData> tweetData = getQueryTweets(query);
            analyticsData = doTagCloudAnalytics(tweetData, query, user); 
            displayResultsInConsole(analyticsData); 
            //store into new collection (mongo)
            analyticsData.setUser(user);
            analyticsData.setQuery(query);
            saveResult(analyticsData);
            return "true";
        }
        catch(Exception e)
        {
            return "Analytics failed: "+e;
        }
        

       
            
    }
    
    @Override
    public List<TweetData> findByQuery(String query) {
        Query createQuery = new Query();
        createQuery.addCriteria(Criteria.where("searchQuery").in(query));
        List<TweetData> tweetData = mongoOperation.find(createQuery, TweetData.class);
        return tweetData;
    }

    @Override
    public List findByField(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
