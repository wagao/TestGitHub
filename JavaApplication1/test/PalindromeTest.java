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
import javaapplication1.Palindrome;

/**
 *
 * @author Emma
 */
public class PalindromeTest {
    
    public PalindromeTest() {
    }
    private static Palindrome pal;
    
    @BeforeClass
    public static void setUpClass() {
        pal = new Palindrome();
    }
    @Test
    public void testGetPal(){
        int[] test ={4,1,2,3,4,5,6,5,4,3,4,4,4,4,4,4,4};	
        assertEquals(10, pal.getPalindrome(test));	
//        Assert.assertEquals(10, pal.getPalindrome(test));
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
