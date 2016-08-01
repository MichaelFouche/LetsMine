/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine;

import com.mycompany.letsmine.geoCode.AddressConverter;
import com.mycompany.letsmine.geoCode.GoogleResponse;
import com.mycompany.letsmine.geoCode.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.GeoCode;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author michaelfouche
 */
public class MainApp {
    
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        HelloWorld hwObj = (HelloWorld) context.getBean("helloWorld");
        
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
        
        SearchResults resultsAdv = twitter.searchOperations().search(new SearchParameters("#5FM")
        .geoCode(new GeoCode(Double.parseDouble(lat), Double.parseDouble(lng), 1300, GeoCode.Unit.KILOMETER))
        //.lang("af")
        //.resultType(SearchParameters.ResultType.RECENT)
        .count(100)
        //.includeEntities(false)
        );
        
        System.out.println("\n--------------\n");
        List<Tweet> tweetListAdv = resultsAdv.getTweets();
        for (Tweet element : tweetListAdv) {
            System.out.println("\t"
                    +"RT:"      +element.isRetweet()
                    +"|"        +element.getRetweetCount()
                    +"|"        +element.getExtraData()
                    +"|From:"   +element.getFromUser()
                    +"|Date:"   +element.getCreatedAt()
                    +"|Lang:"   +element.getLanguageCode()
                    +"|Tweet:"  +element.getText());
            
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
