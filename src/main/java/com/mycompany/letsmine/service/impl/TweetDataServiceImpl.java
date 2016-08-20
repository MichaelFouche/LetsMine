/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.service.impl;

import com.mycompany.letsmine.config.SpringMongoConfig;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.model.User;
import com.mycompany.letsmine.service.TweetDataService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 *
 * @author michaelfouche
 */

public class TweetDataServiceImpl implements TweetDataService{

    @Override
    public List<TweetData> getAllTweets() {
        return getTweetData();
    }
    
    private List<TweetData> getTweetData(){
        List<TweetData> tweetData = new ArrayList<TweetData>();
        
        ApplicationContext ctx;
        MongoOperations mongoOperation;
        User user;
        ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
        user = new User("mkyong", "password123");
        
        tweetData = mongoOperation.findAll(TweetData.class);
        return tweetData;
    }
    
}
