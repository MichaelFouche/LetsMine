/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine;

import com.mycompany.letsmine.config.SpringMongoConfig;
import com.mycompany.letsmine.geoCode.AddressConverter;
import com.mycompany.letsmine.geoCode.GoogleResponse;
import com.mycompany.letsmine.geoCode.Result;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
/**
 *
 * @author michaelfouche
 */
public class MainApp {
    
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        TwitterCollector tc = (TwitterCollector) context.getBean("TwitterCollector");
        tc.retrieveTweet("Takealot");
        
        
        
        
            
            //ANALYTICS
            //read the db collection
            //select the tweets related to the analytics query     
            //manipulate
            //store in collection
            
            //CLOUD TAG
            //Read from analytics collection
            //generate the cloudtag
            
            
        }
        
        
        
        
   
    
   
}

/*//START OF LOCATION
        //enter location in text and receive lat & long
        
        String lat = "";
        String lng = "";

       GoogleResponse res = new AddressConverter().convertToLatLong("Paarl, South Africa");
       if(res.getStatus().equals("OK"))
       {
        for(Result result : res.getResults())
        {
            lat = result.getGeometry().getLocation().getLat();
            lng = result.getGeometry().getLocation().getLng();
         System.out.println("Lattitude of address is :"  +lat);
         System.out.println("Longitude of address is :" + lng);
         System.out.println("Location is " + result.getGeometry().getLocation_type());

        }
       }
       else
       {
        System.out.println(res.getStatus());
       }

       System.out.println("\n");
       GoogleResponse res1 = new AddressConverter().convertFromLatLong(lat+","+lng);
       if(res1.getStatus().equals("OK"))
       {
        for(Result result : res1.getResults())
        {
         System.out.println("address is :"  +result.getFormatted_address());
        }
       }
       else
       {
        System.out.println(res1.getStatus());
       }
        //END OF LOCATION
        */




/*//MONGODB
        //Connect to database and test CRUD
        ApplicationContext ctx = 
             new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        
        MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
        User user = new User("mkyong", "password123");
        
        // save
	mongoOperation.save(user);
        
        // now user object got the created id.
	System.out.println("1. user : " + user);
        
        // query to search user
	Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));
        
        // find the saved user again.
	User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
	System.out.println("2. find - savedUser : " + savedUser);
        
        // update password
	mongoOperation.updateFirst(searchUserQuery, 
                         Update.update("password", "new password"),User.class);
        
        // find the updated user object
	User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);
        System.out.println("3. updatedUser : " + updatedUser);
        
        // delete
	mongoOperation.remove(searchUserQuery, User.class);

	// List, it should be empty now.
	List<User> listUser = mongoOperation.findAll(User.class);
	System.out.println("4. Number of user = " + listUser.size());
        */

/* 
http://docs.spring.io/spring-social-twitter/docs/1.0.5.RELEASE/reference/html/apis.html
From a Twitter user's perspective, Twitter organizes tweets into four different timelines:
User - Includes tweets posted by the user.
Friends - Includes tweets from the user's timeline and the timeline of anyone that they follow, with the exception of any retweets.
Home - Includes tweets from the user's timeline and the timeline of anyone that they follow.
Public - Includes tweets from all Twitter users.
To be clear, the only difference between the home timeline and the friends timeline is that the friends timeline excludes retweets.*/
