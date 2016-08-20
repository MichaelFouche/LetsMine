/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.letsmine.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mycompany.letsmine.TwitterCollector;
import com.mycompany.letsmine.config.SpringMongoConfig;
import com.mycompany.letsmine.geoCode.AddressConverter;
import com.mycompany.letsmine.geoCode.GoogleResponse;
import com.mycompany.letsmine.geoCode.Result;
import com.mycompany.letsmine.model.QueryValues.QueryValues;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.model.User;
import com.mycompany.letsmine.service.CollectorThreadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.letsmine.service.TweetDataService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author michaelfouche
 */
@Controller 
public class DataController {
    String error = "";
    String headingHTML = 
                " <a href=\"/LetsMine\">Home ||</a>\n" +
"                 <a href=\"/LetsMine/query.html\">Query Builder ||</a>\n" +
"                 <a href=\"/LetsMine/queryManager.html\">Data Manager ||</a>\n" +
"                 <a href=\"/LetsMine/analytics.html\">Analytics ||</a>\n" +
"                 <a href=\"/LetsMine/reporting.html\">Reporting</a>";

    private int myCount = 0;
    private ApplicationContext context;
    private TwitterCollector twitterCollector;
    private User user;
    private TweetDataService tweetData;
    
    public DataController(){ 
         context = new ClassPathXmlApplicationContext("beans.xml");
         twitterCollector =  (TwitterCollector) context.getBean("TwitterCollector");
         user =  (User)context.getBean("User");
         user.setUsername(twitterCollector.retrieveUserProfile());
         tweetData =  (TweetDataService)context.getBean("TweetDataService");
         
         System.out.println("in Constructor*******************");
    }
    
   
    
    @RequestMapping(value = "/query", method = RequestMethod.GET)    
    public String getQueryView(Model model){ 
        model.addAttribute("headingHTML", headingHTML);
        error = "";
        //doTweetCollector("Rio2016");
        String html = ""; 
        
        model.addAttribute("query_response", html);
        model.addAttribute("errors",error);
        model.addAttribute("success",error);
        return "query";
    }
    @RequestMapping(value = "/query_action", method = RequestMethod.GET)    
    public String queryActionView(Model model,@RequestParam("query") String queryValue){ 
        model.addAttribute("headingHTML", headingHTML);
        error = "";
        //doTweetCollector("Rio2016");
        String html = ""; 
        html+="<br>Previous Query:<br> <font color=\"green\">"+queryValue+"</font><br>"; 
        model.addAttribute("query_response", html);
        
        
        if(!queryValue.contains("twitter"))
        {
            error += "The datamining platform is not yet supported\n";
        }
        else{
            QueryValues queryValues = interpretQuery(queryValue);
            if(error.equals(""))
            {
                doTweetCollector(queryValues); 
            }
        }
        
        
        
        model.addAttribute("errors",error);
        String success = "";
        if(error.equals(""))
        {
            success += "Query successful";
        }
        model.addAttribute("success", success);
        return "query";
    }
    
    private List<User> getUniqueUsers(){
        List<String> usersFromDB = tweetData.findByField("letsMineUser");
        System.out.println("usersFromDB"+usersFromDB);
        List<User> listOfUsers = new ArrayList();
        
        for(String thisUser: usersFromDB)
        {
            User user = new User(thisUser);
            listOfUsers.add(user);
        }
        return listOfUsers;
    }
    
    private List<User> getQueriesForUsers(List<User> users){ 
        for(User thisUser: users)
        {            
            DBObject dbObject = new BasicDBObject();
            dbObject.put("letsMineUser",thisUser.getUsername());
            List bySearchQuery = tweetData.findByQuery("searchQuery",dbObject);
            thisUser.setQueries(bySearchQuery);
        }
        return users;
    }
    
    @RequestMapping(value = "/queryManager", method = RequestMethod.GET) 
    public String queryManagerView(Model model){ 
        model.addAttribute("headingHTML", headingHTML);
        
        String loggedInUser = (String)user.getUsername();
        System.out.println("loggedInUser: "+loggedInUser);
        //get unique users
        List uniqueUsers = getUniqueUsers();
        System.out.println("Users: "+uniqueUsers);
        //get their requests
        List uniqueUsersWithQueries = getQueriesForUsers(uniqueUsers);
        System.out.println("Queries by: "+uniqueUsersWithQueries);
        //select user and request to display request data
       
        
      /*  
        CollectorThreadService collectorThread =  (CollectorThreadService)ctx.getBean("CollectorThreadService");
        Thread t = new Thread((Runnable) collectorThread);
        System.out.println("Starting sub thread");
      //  t.start();
        */   
       
        List<TweetData> listDB =  tweetData.getAllTweets();
        model.addAttribute("myQueryList", listDB);  
        model.addAttribute("loggedInUser", loggedInUser);  
        
        return "queryManager"; 
    } 
    
    @RequestMapping(value = "/analytics", method = RequestMethod.GET) 
    public String analyticsView(Model model){ 
        model.addAttribute("headingHTML", headingHTML);
//        ApplicationContext ctx;
//        MongoOperations mongoOperation;
//        
//        ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//        mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
//        
//        List<TweetData> listDB = mongoOperation.findAll(TweetData.class);
//        model.addAttribute("myQueryList", listDB);      
        
        return "analytics"; 
    } 
    
    @RequestMapping(value = "/reporting", method = RequestMethod.GET) 
    public String reportView(Model model){ 
        model.addAttribute("headingHTML", headingHTML);
//        ApplicationContext ctx;
//        MongoOperations mongoOperation;
//        
//        ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//        mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
//        
//        List<TweetData> listDB = mongoOperation.findAll(TweetData.class);
//        model.addAttribute("myQueryList", listDB);      
        
        return "reporting"; 
    } 
    
    
    @RequestMapping(value = "/tagCloud", method = RequestMethod.GET)    
    public String getTagCloudView(Model model){ 
        model.addAttribute("headingHTML", headingHTML);    
        
        int[] scoreList = {5,2,5,7,4,5,2,5,7,4};
        String[] nameList = {"Google","Facebook","Skype","Instagram","LetsMine","Google","Facebook","Skype","Instagram","LetsMine"};
        String[] linkList = {"#","#","#","#","#","#","#","#","#","#"};
        
        String html = ""; 
        int i=1; 
        int tagsPerLine = 4; 
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
    
    public String[] getLocationLatLong(String address){
        String lat = "";
        String lng = "";
        
        String[] latlng = new String[2];
        try
        {
            GoogleResponse res = new AddressConverter().convertToLatLong("Paarl, South Africa");
            if(res.getStatus().equals("OK"))
            {
                 for(Result result : res.getResults())
                 {
                     lat = result.getGeometry().getLocation().getLat();
                     lng = result.getGeometry().getLocation().getLng();
                     latlng[0] = lat;
                     latlng[1] = lng;
         //            System.out.println("Lattitude of address is :"  +lat);
         //            System.out.println("Longitude of address is :" + lng);
         //            System.out.println("Location is " + result.getGeometry().getLocation_type());
                 }
            }
            else
            {
                 System.out.println(res.getStatus());
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Retrieving location servlet: "+e);
        }
        return latlng;
    }
    
    public void doTweetCollector(QueryValues queryValues){ 
        twitterCollector.retrieveTweet(queryValues.getHashtagValue(), queryValues.getLat(), queryValues.getLng(), queryValues.getRadiusValue());         
    }
    
    public QueryValues interpretQuery(String queryValue){
        //keywords: DATAMINE, HASHTAG, FROM, 
        String actionValue = "";
        String hashtagValue = "";
        String fromValue = "";
        int radiusValue = 0;
        
        try{
            int sizeOfAction = 0;
            int sizeOfHashtag = 0;
            int sizeOfFrom = 0;
            int sizeOfRadius = 0;
            
            //DATAMINE
            int locationOfAction = queryValue.indexOf("datamine");
            if(locationOfAction==-1){locationOfAction = queryValue.indexOf("Datamine");}
            if(locationOfAction==-1){locationOfAction = queryValue.indexOf("DATAMINE");}

            //HASHTAG
            int locationOfHashtag = queryValue.indexOf("hashtag");
            if(locationOfHashtag==-1){locationOfHashtag = queryValue.indexOf("Hashtag");}
            if(locationOfHashtag==-1){locationOfHashtag = queryValue.indexOf("HASHTAG");}
          // if(locationOfHashtag==-1){locationOfHashtag = queryValue.indexOf("#");}

            //FROM
            int locationOfFROM = queryValue.indexOf("from");
            if(locationOfFROM==-1){locationOfFROM = queryValue.indexOf("From");}
            if(locationOfFROM==-1){locationOfFROM = queryValue.indexOf("FROM");}

            //RADIUS
            int locationOfRADIUS = queryValue.indexOf("radius");
            if(locationOfRADIUS==-1){locationOfRADIUS = queryValue.indexOf("Radius");}
            if(locationOfRADIUS==-1){locationOfRADIUS = queryValue.indexOf("RADIUS");}
           
            if(locationOfAction!=-1 && locationOfHashtag!=-1 && locationOfFROM!=-1 && locationOfRADIUS!=-1)
            {
                actionValue = (queryValue.substring(locationOfAction+("datamine".length()),locationOfHashtag)).replaceAll("\\s+","");
                hashtagValue = (queryValue.substring(locationOfHashtag+("hashtag".length()),locationOfFROM).replaceAll("\\s+",""));
                fromValue = queryValue.substring(locationOfFROM+("from".length()), locationOfRADIUS);
                radiusValue = Integer.parseInt((queryValue.substring(locationOfRADIUS+("radius".length()))).replaceAll("\\s+",""));
            }
            else{
                System.out.println("Not all Values entered\n");
                error += "Syntax error: Please ensure all query variables are entered\n";
            }

            System.out.println("Location:"+actionValue);
            System.out.println("Hashtag:"+hashtagValue);
            System.out.println("From:"+ fromValue);

            String[] latlng = getLocationLatLong(fromValue);
            String lat = latlng[0];
            String lng = latlng[1];

            System.out.println("Radius:"+ radiusValue);

            QueryValues queryValues = new QueryValues(actionValue, hashtagValue, lat, lng, radiusValue);
            return queryValues;
        }
        catch(Exception e)
        {
            System.out.println("Error conducting query analysis: "+e);
             error += "Error conducting query analysis\n";   
        }
        
        QueryValues queryValues = new QueryValues();
        return queryValues;
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
