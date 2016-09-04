/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.letsmine.TwitterCollector;
import com.mycompany.letsmine.controller.DataController;
import com.mycompany.letsmine.model.TweetData;
import com.mycompany.letsmine.model.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author michaelfouche
 */
public class TwitterCollectorTest {
    private ApplicationContext context;
    private TwitterCollector twitterCollector;
    private User user;
    
        
    public TwitterCollectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void getConnection(){
        
    }
    
    @Test
    public void userProfile() {
        context = new ClassPathXmlApplicationContext("beans.xml");
        twitterCollector =  (TwitterCollector) context.getBean("TwitterCollector");
        String userName = twitterCollector.retrieveUserProfile();
        System.out.println("userName "+userName);
        assertNotNull(userName);
    }
    
    @Test
    public void testRetrieveTweet(){
        
        context = new ClassPathXmlApplicationContext("beans.xml");
        twitterCollector =  (TwitterCollector) context.getBean("TwitterCollector");
        String hashtag = "Takealot";
        String lat = "-33.7342304";
        String lng = "18.9621091";
        int radius = 5000;
        String query = "Test";
        List<TweetData> tweetDataList = new ArrayList();
        tweetDataList = twitterCollector.retrieveTweetFromTwitter(hashtag, lat, lng, radius, query);
        assertTrue(tweetDataList.size()>0);
        System.out.println("tweetDataList.size() "+ tweetDataList.size());
    }

    
    
}
