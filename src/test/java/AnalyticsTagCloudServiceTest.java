/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.letsmine.TwitterCollector;
import com.mycompany.letsmine.service.AnalyticsTagCloudService;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testConductAnalyticsAndSave() {
         analyticsTagCloudService =  (AnalyticsTagCloudService)context.getBean("AnalyticsTagCloudService");
         String resultMessage = "DATAMINE twitter HASHTAG takealot FROM Cape Town RADIUS 2000";
         String loggedInUser = "mfouche91";
          String returnedMessage = analyticsTagCloudService.conductTagCloudAnalytics(resultMessage, loggedInUser);
          if(returnedMessage.equals("true"))
          {
              assertTrue(true);
          }
          else
          {
              assertTrue(false);
          }
      assertTrue(true);
     }
}
