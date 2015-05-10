/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javaapplication1.WaterFlow;

/**
 *
 * @author Emma
 */
public class WaterFlowTest {
    
    public WaterFlowTest() {
    }
    
    public static  WaterFlow testO;
    public static  WaterFlow testB;
    
    @BeforeClass
    public static void setUpClass() {
        testO = new WaterFlow();
        testB = new WaterFlow();
    }
    
    @Test
    public  void testFlow(){
        testO.printM(testO.fMatrix);
//        testO.printM(testO.sLeftTop);
        testO.findAllLefTop();
        testO.printM(testO.sLeftTop);
        testO.findAllRightBottom();
        testO.printM(testO.sRightBottom);     
        
        testB.findS();
        testB.printM(testB.sRightBottom);
        
        testO.getToOceanMap();
        testB.getToOceanMap();
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
    // @Test
    // public void hello() {}
}
