/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine;

import com.mycompany.letsmine.config.SpringMongoConfig;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.social.twitter.api.GeoCode;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author michaelfouche
 */
@Service
public class TwitterCollector {
    private String consumerKey;
    private String consumerSecret ;
    private String accessToken;
    private String accessTokenSecret ;
    private Twitter twitter;
    ApplicationContext mongoContext;
    MongoOperations mongoOperation;
    

    public TwitterCollector() {
        //Hibernate
    }
    
    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public void setKeys(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        /*
        this.consumerKey = "hAdjsGaCHIxVlxmkh3kwu3v3D";// The application's consumer key
        this.consumerSecret = "vr8RwBl5Ry42gnReysvGYTMJtr2kmMkcMFgype7ih1jAnnCVn0";// The application's consumer secret
        this.accessToken = "53678997-qI0CgAlJNf4vbukF5g7nna9E50EV5BLiEop3iPpZ2";// The access token granted after OAuth authorization
        this.accessTokenSecret = "4uXUK3Lq7X4a02HjuNAiBcO1p4hSJGtDaiwgSG4BkYaAc";// The access token secret granted after OAuth authorization*/
    }
    
    public String retrieveUserProfile(){
        try{
            Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
            return twitter.userOperations().getUserProfile().getScreenName();
        }
        catch(Exception e){
            System.out.println("ERROR "+e);
        }
       return null;
    }
     public void retrieveTweet(String hashtag, String lat, String lng, int radius, String query){
         //get tweet list from below
         ArrayList<TweetData> tweetDataList = new ArrayList();
         tweetDataList = retrieveTweetFromTwitter(hashtag, lat, lng, radius, query);
         for(TweetData item: tweetDataList)
         {
             saveTweet(item);
         }
     }
    
    public ArrayList<TweetData> retrieveTweetFromTwitter(String hashtag, String lat, String lng, int radius, String query){
        ArrayList<TweetData> tweetDataList = new ArrayList();
        try{
            mongoContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
            mongoOperation = (MongoOperations)mongoContext.getBean("mongoTemplate");
            Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);

            TwitterProfile profile = twitter.userOperations().getUserProfile();

            String profileId = twitter.userOperations().getScreenName();

            System.out.println("Name: " + profile.getName()+ "Screen Name: "+profile.getScreenName());

            System.out.println("" + profile.getProfileUrl());

            //SearchResults results = twitter.searchOperations().search(profileId, 0);


            /*SearchResults results = twitter.searchOperations().search("#HIMYM", 25);
            List<Tweet> tweetList = results.getTweets();
            for (Tweet element : tweetList) {
                System.out.println("\tRT:"+element.isRetweet()+"|From:"+element.getFromUser()+"|Date:"+element.getCreatedAt()+" |Tweet: "+element.getText());
            }*/
            String tweetID = "760118695037403136";//need to populate data since it's only a week old
            SearchResults resultsAdv = twitter.searchOperations().search(new SearchParameters(hashtag)
            .geoCode(new GeoCode(Double.parseDouble(lat), Double.parseDouble(lng), radius, GeoCode.Unit.KILOMETER))
            //.lang("af")
            //.resultType(SearchParameters.ResultType.MIXED)//mixed recent popular
            .count(35)//max 100,default 15
            //.until(untilDate)
            .includeEntities(true)//media, urls, user mentions, hashtags, symbols
            //.maxId(Long.parseLong(tweetID))
            //.sinceId(Long.parseLong(tweetID))
            );

            System.out.println("\n--------------\n");
            List<Tweet> tweetListAdv = resultsAdv.getTweets();
            for (Tweet element : tweetListAdv) {

                TweetData tweetData = new TweetData(
                        element.getId(),
                        profile.getName(),
                        element.getFromUser(),
                        element.getFromUserId(),
                        element.getInReplyToStatusId(),
                        element.getInReplyToUserId(),
                        element.getUser(),
                        element.getCreatedAt(),
                        element.getLanguageCode(),
                        element.getText(),
                        element.getInReplyToScreenName(),
                        element.getProfileImageUrl(),
                        element.getSource(),
                        element.getUnmodifiedText(),
                      //  element.getEntities(),
                        element.getFavoriteCount(),
                        element.hasMedia(),
                        element.hasMentions(),
                        element.hasTags(),
                        element.hasUrls(),
                        element.hashCode(),
                        element.isFavorited(),
                        element.isRetweeted(),
                        element.isRetweet(),
                        element.getRetweetCount(),
                        profile.getScreenName(),//.getName(), //
                        query,
                        element.getEntities().getUrls(),
                        element.getEntities().getHashTags(),
                        element.getEntities().getMentions(),
                        element.getEntities().getMedia(),
                        element.getEntities().getTickerSymbols()
                );
                tweetDataList.add(tweetData); 
            }    
        }   
        catch(Exception e){
            System.out.println("TwitterCollector: "+e);
            return null;
        }
        return tweetDataList;
    }
    
   private void saveTweet(TweetData tweetData){
        try{    
            mongoOperation.save(tweetData);    
        }
        catch(Exception e){
            System.out.println("TwitterCollector: "+e);
        }
   }
}
