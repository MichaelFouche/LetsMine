/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.letsmine.TwitterCollector;
import com.mycompany.letsmine.model.AnalyticsData;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.service.AnalyticsTagCloudService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.social.twitter.api.HashTagEntity;

/**
 *
 * @author michaelfouche
 */
public class AnalyticsTagCloudServiceTest {
    
    public AnalyticsTagCloudServiceTest() {
    }
    private AnalyticsTagCloudService analyticsTagCloudService;
    private ApplicationContext context;
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("beans.xml");
        // twitterCollector =  (TwitterCollector) context.getBean("TwitterCollector");
    }
    
    @After
    public void tearDown() {
    }
     
    @Test
    public void testAnalyticsAlgorithm(){
        //Given
        Integer hashtag1Count = 1;  
        Integer hashtag2Count = 2;  
        String aMentionText1 = "Hashtag1";
        String aMentionText2 = "Hashtag2";  
        String aMentionText3 = "Hashtag2"; 
        String user = ("TestUser");
        String query = ("TestQuery");

        AnalyticsData analyticsDataExpected = new AnalyticsData();
        analyticsDataExpected.setTagCloudHashMap(new HashMap<String, Integer>());
        AnalyticsData analyticsDataActual = new AnalyticsData();
        analyticsDataActual.setTagCloudHashMap(new HashMap<String, Integer>());

        int[] indeces = {24,39};
        HashTagEntity hashTagEntity1 = new HashTagEntity(aMentionText1, indeces);
        HashTagEntity hashTagEntity2 = new HashTagEntity(aMentionText2, indeces);         
        HashTagEntity hashTagEntity3 = new HashTagEntity(aMentionText3, indeces);
        List<HashTagEntity> theHashtagList = new LinkedList<>(); 
        theHashtagList.add(hashTagEntity1);
        theHashtagList.add(hashTagEntity2);
        theHashtagList.add(hashTagEntity3);
        TweetData tweetData = new TweetData();
        tweetData.setTags(theHashtagList);
        tweetData.setSearchQuery(query);
        tweetData.setLetsMineUser(user);
        List<TweetData> tweetDataList = new LinkedList<TweetData>();
        tweetDataList.add(tweetData);

        analyticsDataExpected.getTagCloudHashMap().put(aMentionText1, hashtag1Count);
        analyticsDataExpected.getTagCloudHashMap().put(aMentionText2, hashtag2Count);

        //When
        analyticsTagCloudService =  (AnalyticsTagCloudService)context.getBean("AnalyticsTagCloudService");
        analyticsDataActual = analyticsTagCloudService.doTagCloudAnalytics(tweetDataList, query, user);
        //Then
        assertEquals(analyticsDataExpected.getTagCloudHashMap(), analyticsDataActual.getTagCloudHashMap());
    }
}
