/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 *
 * @author michaelfouche
 */
@Service
public class StreamService {


    @Autowired
    private Twitter twitter;

    public Model streamApi(Model model, int time) throws InterruptedException{

        List<Tweet> tweets = new ArrayList<>();

        List<StreamListener> listeners = new ArrayList<StreamListener>();
        StreamListener streamListener = new StreamListener() {

            @Override
            public void onWarning(StreamWarningEvent warningEvent) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTweet(Tweet tweet) {
                System.out.println(tweet.getUser().getName() + " : " + tweet.getText());
                
                tweets.add(tweet);
                model.addAttribute("tweets", tweets);
            }

            @Override
            public void onLimit(int numberOfLimitedTweets) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDelete(StreamDeleteEvent deleteEvent) {
                // TODO Auto-generated method stub

            }
        };

        listeners.add(streamListener);
        //This sets the GeoCode (-122.75,36.8,-121.75,37.8) of San Francisco(South-West and North-East) region as given in below twitter docs
        //https://dev.twitter.com/streaming/overview/request-parameters#locations
        Float west=-122.75f;
        Float south=36.8f;
        Float east=-121.75f;
        Float north = 37.8f;

        FilterStreamParameters filterStreamParameters = new FilterStreamParameters();
        filterStreamParameters.addLocation(west, south, east, north);

        Stream userStream = twitter.streamingOperations().filter(filterStreamParameters, listeners);
        Thread.sleep(time);
        userStream.close();
        return model;
    }
}