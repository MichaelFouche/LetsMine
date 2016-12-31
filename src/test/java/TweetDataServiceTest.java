/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.service.TweetDataService;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.MediaEntity;
import org.springframework.social.twitter.api.MentionEntity;
import org.springframework.social.twitter.api.TickerSymbolEntity;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.UrlEntity;

/**
 *
 * @author michaelfouche
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TweetDataServiceTest {
    
    public TweetDataServiceTest() {
    }
    
    private ApplicationContext context;
    private TweetDataService tweetDataService; 
    String letsMineUser;
    String testSearchQuery;
    
    @BeforeClass
    public static void setUpClass() {
    
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("beans.xml");
        tweetDataService =  (TweetDataService)context.getBean("TweetDataService");
        letsMineUser = "testUser";
        testSearchQuery = "TestSearchQuery";
    }
    
    @After
    public void tearDown() {
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void test0hello() {
         
         
         assertTrue(true);
         //thisUniqueUser.setQueries(bySearchQuery);
     }
     
     @Test
    public void test1WriteToDatabase(){
        //Given
        int[] indices = {1,1};
        Date date = new Date();
        UrlEntity urlEntity = new UrlEntity("", "", "", indices) ;
        List<UrlEntity> urlEntityList = new LinkedList<>();
        urlEntityList.add(urlEntity);
        
        HashTagEntity hashTagEntity = new HashTagEntity("",indices);
        List<HashTagEntity> hashTagEntityList = new LinkedList<>();
        hashTagEntityList.add(hashTagEntity);
        
        MentionEntity mentionEntity = new MentionEntity(0L, "", "", indices);
        List<MentionEntity> mentionEntityList = new LinkedList<>();
        mentionEntityList.add(mentionEntity);
        
        MediaEntity mediaEntity = new MediaEntity(0L, "", "", "", "", "", "", indices);
        List<MediaEntity> mediaEntityList = new LinkedList<>();
        mediaEntityList.add(mediaEntity);
        
        TickerSymbolEntity tickerSymbolEntity = new TickerSymbolEntity("", "", indices);
        List<TickerSymbolEntity> tickerSymbolEntityList = new LinkedList<>();
        tickerSymbolEntityList.add(tickerSymbolEntity);
        
        TweetData tweetData = new TweetData(123L, "testUser", "testUser", 0L, 0L, 0L, new TwitterProfile(0L, "", "", "", "", "", "", date), date, "", "Test2", "Screenname", "ProfileImageUrl", "Source", "UnmodifiedText"/*, Entities entities*/, 0, false, false, true, false, 0, false, false, false, 0,letsMineUser, testSearchQuery, urlEntityList , hashTagEntityList ,mentionEntityList, mediaEntityList, tickerSymbolEntityList);
        
        //When
        boolean result = tweetDataService.saveTweet(tweetData);
        
        //Then
        assertTrue(result);
    }
    
    @Test
    public void test2ReadFromDatabase(){        
        //Given
        DBObject dbObject = new BasicDBObject();
        String queryFieldName = "searchQuery";
        String collection = "tweetdata";
        
        //When
        dbObject.put("letsMineUser",letsMineUser);        
        List bySearchQuery = tweetDataService.findByQuery(queryFieldName,dbObject, collection);
        //Then
        assertTrue(bySearchQuery.contains(testSearchQuery));
        
    }
    
    @Test
    public void test3DeleteFromDatabase(){
        //Given
        Query query = new Query();
        query.addCriteria(Criteria.where("letsMineUser").is(letsMineUser).and("searchQuery").is(testSearchQuery));
        String collectionName = "tweetdata";
        //When
        boolean result = tweetDataService.deleteUserQuery(query, collectionName);
        //Then
        assertTrue(result);
    }
}
