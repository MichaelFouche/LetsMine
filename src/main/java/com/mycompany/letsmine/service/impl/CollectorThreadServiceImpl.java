/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.service.impl;

import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.service.CollectorThreadService;
import com.mycompany.letsmine.service.TweetDataService;
import java.util.List;

/**
 *
 * @author michaelfouche
 */
public class CollectorThreadServiceImpl implements CollectorThreadService, Runnable{

    int myCount = 0;
    
    @Override
    public void retrieveTweets() {
        retrieveTweetsThread();
    }

    public CollectorThreadServiceImpl(){
    //hibernate
    }
    
    private void retrieveTweetsThread(){
    
    }

    @Override
    public void run() {
        while(myCount <= 5){
            try{
                System.out.println("Collector Thread: -->"+(++myCount));
                Thread.sleep(1000);
            } catch (InterruptedException iex) {
                System.out.println("Exception in thread: "+iex.getMessage());
            }
        }
    }
    
}
