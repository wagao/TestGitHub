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
import javaapplication1.permutation;
import org.junit.Ignore;

/**
 *
 * @author Emma
 */
public class perTest {
    
    public perTest() {
    }
    public static permutation test;
    @BeforeClass
    public static void setUpClass() {
        test = new permutation();
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
     public void subsetTest() {
     int[] num={1,2,2,3,4,55,6,7,8,9};
     System.out.println( test.subsetsWithDup(num));
//     Arrays.sort(num);
//     assertEquals
       }
}
