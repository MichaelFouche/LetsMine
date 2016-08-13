/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.letsmine.service.TweetDataService;
import javax.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author michaelfouche
 */
@Controller 
@RequestMapping("/purchase-request") 
public class TweetDataController {
    private static final String MY_REQUESTS_VIEW="myRequests"; 
    private static final String MY_REQUESTS_MODEL_ATTRIBUTE="myRequestList"; 

    @Resource 
    private TweetDataService tweetDataService; 

    @RequestMapping(value = "/myRequests", method = RequestMethod.GET) 
    public String getMyRequests(Model model){ 
        model.addAttribute(MY_REQUESTS_MODEL_ATTRIBUTE, tweetDataService.getAllTweets()); 
        return MY_REQUESTS_VIEW; 
    } 
    
    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {

            String message = "<br><div style='text-align:center;'>"
                            + "<h3>********** Hello World, Spring MVC</h3>This message is coming from TweetDataController.java **********</div><br><br>";
            return new ModelAndView("welcome", "message", message);
    }
}