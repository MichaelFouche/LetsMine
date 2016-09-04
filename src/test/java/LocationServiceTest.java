/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.letsmine.service.LocationService;
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
public class LocationServiceTest {
    private ApplicationContext context;
    public LocationServiceTest() {
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
     public void locationReturnSomething() {
     context = new ClassPathXmlApplicationContext("beans.xml");
        LocationService locationService = (LocationService)context.getBean("LocationService");
        String address = "Cape Town";
        assertNotNull( locationService.getLocationLatLong(address));
     }
     
     @Test 
     public void locationReturnCorrectLatCoordinates() {
     context = new ClassPathXmlApplicationContext("beans.xml");
        LocationService locationService = (LocationService)context.getBean("LocationService");
        String address = "Cape Town";
        
        String[] latlngResult = new String[2];
        latlngResult = locationService.getLocationLatLong(address);   
        
        String latExpected = "-33.7342304";
        
        assertEquals(latExpected, latlngResult[0]);
     }
     @Test 
     public void locationReturnCorrectLngCoordinates() {
     context = new ClassPathXmlApplicationContext("beans.xml");
        LocationService locationService = (LocationService)context.getBean("LocationService");
        String address = "Cape Town";
        
        String[] latlngResult = new String[2];
        latlngResult = locationService.getLocationLatLong(address);   
        
        String lngExpected = "18.9621091";
        
         assertEquals(lngExpected, latlngResult[1]);
     }
     
     
     
}
