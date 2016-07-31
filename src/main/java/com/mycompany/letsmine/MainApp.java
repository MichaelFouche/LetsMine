/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;


/**
 *
 * @author michaelfouche
 */
public class MainApp {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        HelloWorld hwObj = (HelloWorld) context.getBean("helloWorld");
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
        
        SearchResults results = twitter.searchOperations().search("#HIMYM", 200);
        List<Tweet> tweetList = results.getTweets();
        for (Tweet element : tweetList) {
            System.out.println("Tweet: "+element.getText());
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
