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
import javaapplication1.Fpair;

/**
 *
 * @author Emma
 */
public class FpairTest {
    
    public FpairTest() {
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
    
    @Test
    public void testFpair(){
        int n= (int)(Math.random()*10+1);
        Fpair A= new Fpair(n);
        System.out.println(A.fNumber_M_BU());
//        System.out.println(A.fNumber_M_TD(10));
//        System.out.println(A.fNumber_TopDown(10));  //55
//        assertEquals(A.fNumber_M_BU(n), A.fNumber_M_TD(n));
//        assertEquals(A.fNumber_M_BU(n), A.fNumber_TopDown(n));
//        assertEquals(A.fNumber_M_BU(10), A.fNumber_TopDown(10));
        System.out.println(A.Fpair_TD()); 
        System.out.println(A.Fpair_BU()); 
        assertEquals(A.Fpair_TD(),     A.Fpair_BU());
        A.printS();
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
