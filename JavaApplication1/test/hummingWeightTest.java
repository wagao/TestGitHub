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
import javaapplication1.hummingWeight;
import org.junit.Assert;
import org.junit.Ignore;

/**
 *
 * @author Emma
 */
public class hummingWeightTest {
    static hummingWeight test;
    public hummingWeightTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        test = new hummingWeight();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    @Test
    public void testRob(){
        int[] num = {4,7,9,100,2,4,57,1,89, 3464,33, 678,2,7,9} ;
         assertEquals(test.rob_2(num), test.rob(num));
    }
    
    @Ignore
    @Test
    public void testNoBit() {
//        assertEquals(964176192,test.reverseBit(43261596));
//        int[] A1={1,2};
//        int[] A2={1,2,3,5,4};
//        int[] A3={1,2,3,5,4};
//        int[] A4={1,2,3,5,4};
//        int[] A5={1,2,3,5,4};
//        int[] A6={1,2,3,5,4};
////     test.rotate_2( A3, 3 );
//     test.rotate_3( A3, 1 );
//      test.rotate_3( A6, 22 );
//      test.rotate_3( A2, 3 );
//      test.get( 21 );
//      test.trailingZeroes( 21 );
//       System.out.println(test.reverseUnsignedInt(12462));
        int  a= test.convertToInt("ZAABZ") ;
       System.out.println(a);
       System.out.println( test.convertToTitle(a));
       int b =1231;
       assertEquals( b, test.convertToInt(test.convertToTitle(b)));
       
       
       
        StringBuffer[] res = new StringBuffer[3];
String t="rvqgpkfrpmrypoetwhpcgzumilaxmtydoqjgbrxsfoquewpcdfgmgbao";
         assertEquals(test.convert(t, 49), test.ZigZag(t,49));
//      System.out.println(test.titleToNumber("AA"));
//     test.rotate_4( A4, 3 );
//        Assert.assertArrayEquals(test.rotate_4( A1, 3 ), test.rotate_3( A2, 3 )); 
//        Assert.assertArrayEquals(test.rotate_2( A5, 22 ), test.rotate_3( A6, 22 )); 
//        Assert.assertArrayEquals(test.rotate_1( A4, 3 ), test.rotate_3( A3, 3 )); 
//        assertEquals(2,test.hammingWeight_2(5));
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
