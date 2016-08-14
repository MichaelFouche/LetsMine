/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.controller;

import com.mycompany.letsmine.TwitterCollector;
import com.mycompany.letsmine.config.SpringMongoConfig;
import com.mycompany.letsmine.model.TweetData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.letsmine.service.TweetDataService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author michaelfouche
 */
@Controller 
public class TweetDataController {
    
    private static final String MY_REQUESTS_VIEW="myRequests"; 
    private static final String MY_REQUESTS_MODEL_ATTRIBUTE="myRequestList"; 
 

    @RequestMapping(value = "/myRequests", method = RequestMethod.GET) 
    public String getMyRequests(Model model){ 
        ApplicationContext ctx;
        MongoOperations mongoOperation;
        
        ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
        
        List<TweetData> listDB = mongoOperation.findAll(TweetData.class);
        model.addAttribute(MY_REQUESTS_MODEL_ATTRIBUTE, listDB); 
        return MY_REQUESTS_VIEW; 
    } 
   
    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {
       /* */

            String message = "<br><div style='text-align:center;'>"
                            + "<h3>Lets Mine</h3>"
                          //  + "<p>Put Twitter data here <<Size: "+listDB.size()+">></p>"
                            + "</div><br><br>";
            return new ModelAndView("welcome", "message", message);
    }
    
    @RequestMapping(value = "/tweetCollect", method = RequestMethod.GET)    
    public String getTweetCollectView(Model model){ 
        doTweetCollector("Rio2016");
        return "tweetCollect";
    }
    
    @RequestMapping(value = "/tagCloud", method = RequestMethod.GET)    
    public String getTagCloudView(Model model){ 
                
        
        int[] scoreList = {5,2,5,7,4};
        String[] nameList = {"Google","Facebook","Skype","Instagram","LetsMine"};
        String[] linkList = {"#","#","#","#","#"};
        
        String html = ""; 
        int i=0; 
        int tagsPerLine = 30; 
        for(int x=0;x<scoreList.length;x++) 
        { 
          int score = scoreList[x]; 
          html+="<a href="+linkList[x]+" style=font-size:"+(score*7)+"px;>"+nameList[x]+"</a> "; 
          if(i%tagsPerLine ==0) 
          { 
           html+="<br>"; 
          } 
          i++; 
        } 
        model.addAttribute("TagCloudHtml", html);
        
        return "tagCloud";
    }
    
    
    public void doTweetCollector(String query){ 
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TwitterCollector tc = (TwitterCollector) context.getBean("TwitterCollector");
        tc.retrieveTweet(query);
    }
    
    /*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }*/
    
    /*@Controller("HelloController")
public class HelloController {
    ConnectionRepository connectionRepository;
    StreamService streamService;
    
    @RequestMapping("/stream/{time}")
    public String streamTweet(@PathVariable int time, Model model) throws InterruptedException{
    if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
        return "redirect:/connect/twitter";
    }
    Model returnedmodel = streamService.streamApi(model, time);
    model = returnedmodel;

    return "stream";
}
}*/
}
