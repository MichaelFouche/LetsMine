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
import com.mycompany.letsmine.model.User;
import java.io.IOException;
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

        HelloWorld hwObj = (HelloWorld) context.getBean("helloWorld");
        
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
        
        
        
        //START OF LOCATION
        
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
        
        
        hwObj.getMessage();
      
        String consumerKey = "hAdjsGaCHIxVlxmkh3kwu3v3D"; // The application's consumer key
        String consumerSecret = "vr8RwBl5Ry42gnReysvGYTMJtr2kmMkcMFgype7ih1jAnnCVn0"; // The application's consumer secret
        String accessToken = "53678997-qI0CgAlJNf4vbukF5g7nna9E50EV5BLiEop3iPpZ2"; // The access token granted after OAuth authorization
        String accessTokenSecret = "4uXUK3Lq7X4a02HjuNAiBcO1p4hSJGtDaiwgSG4BkYaAc"; // The access token secret granted after OAuth authorization
        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);

        TwitterProfile profile = twitter.userOperations().getUserProfile();
        String profileId = twitter.userOperations().getScreenName();
        
        System.out.println("Name: " + profile.getName());
        System.out.println("" + profile.getProfileUrl());
        
        //SearchResults results = twitter.searchOperations().search(profileId, 0);
        
        
        /*SearchResults results = twitter.searchOperations().search("#HIMYM", 25);
        List<Tweet> tweetList = results.getTweets();
        for (Tweet element : tweetList) {
            System.out.println("\tRT:"+element.isRetweet()+"|From:"+element.getFromUser()+"|Date:"+element.getCreatedAt()+" |Tweet: "+element.getText());
        }*/
        String tweetID = "760118695037403136";//need to populate data since it's only a week old
        SearchResults resultsAdv = twitter.searchOperations().search(new SearchParameters("#5FM")
        //.geoCode(new GeoCode(Double.parseDouble(lat), Double.parseDouble(lng), 150, GeoCode.Unit.KILOMETER))
        //.lang("af")
        //.resultType(SearchParameters.ResultType.MIXED)//mixed recent popular
        .count(20)//max 100,default 15
        //.until(untilDate)
        .includeEntities(true)//media, urls, user mentions, hashtags, symbols
        //.maxId(Long.parseLong(tweetID))
        //.sinceId(Long.parseLong(tweetID))
        );
        
        System.out.println("\n--------------\n");
        List<Tweet> tweetListAdv = resultsAdv.getTweets();
        for (Tweet element : tweetListAdv) {
            System.out.println("\t"
                    +"|ID:"     +element.getIdStr()
                    +"|"        +element.getId()
                    +"|"        +element.getExtraData()
                    +"|From:"   +element.getFromUser()
                    +"|"        +element.getFromUserId()
                    +"|"        +element.getInReplyToStatusId()
                    +"|"        +element.getInReplyToUserId()
                    +"|"        +element.getUser()
                    +"|Date:"   +element.getCreatedAt()
                    +"|Lang:"   +element.getLanguageCode()
                    +"|Tweet:"  +element.getText()
                    +"|"        +element.getInReplyToScreenName()
                    +"|"        +element.getProfileImageUrl()
                    +"|"        +element.getSource()
                    +"|UnmodifiedText"      +element.getUnmodifiedText()
                    +"|"        +element.getEntities()
                    +"|"        +element.getFavoriteCount()
                    +"|"        +element.hasMedia()
                    +"|"        +element.hasMentions()
                    +"|"        +element.hasTags()
                    +"|"        +element.hasUrls()
                    +"|"        +element.hashCode()
                    +"|"        +element.isFavorited()
                    +"|"        +element.isRetweeted()  
                    +"RT:"      +element.isRetweet()
                    +"|"        +element.getRetweetCount()
            );
            
                    
            
            
        }
        
        
        
        
   
    }
   
}


/* 
http://docs.spring.io/spring-social-twitter/docs/1.0.5.RELEASE/reference/html/apis.html
From a Twitter user's perspective, Twitter organizes tweets into four different timelines:
User - Includes tweets posted by the user.
Friends - Includes tweets from the user's timeline and the timeline of anyone that they follow, with the exception of any retweets.
Home - Includes tweets from the user's timeline and the timeline of anyone that they follow.
Public - Includes tweets from all Twitter users.
To be clear, the only difference between the home timeline and the friends timeline is that the friends timeline excludes retweets.*/
