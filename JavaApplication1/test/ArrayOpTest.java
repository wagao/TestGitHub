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
import org.junit.Ignore;
import static org.junit.Assert.*;
import javaapplication1.ArrayOp;
import java.util.Arrays;

/**
 *
 * @author Emma
 */
public class ArrayOpTest {
    
    static ArrayOp test;
    public ArrayOpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
          test = new ArrayOp();
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
    
    @Test
    public void sqrtTest(){
     System.out.println( "result:"+test.mySqrt(4));
     System.out.println( "result:"+test.mySqrt(110));
     System.out.println( "result:"+test.mySqrt(2147483647));
    }
    @Ignore
    @Test
    public void countPrimesTest(){
         System.out.println( "result:"+test.countPrimes(1));
         System.out.println( "result:"+test.countPrimes(2));
    }
    @Ignore
    @Test
    public void getPermutationTest(){
    
         System.out.println( "result:"+test.getPermutation(3,4));
    }
    
    @Ignore
    @Test
    public void  NqueenTest(){
         System.out.println( test.solveNQueens(4));
    }
    @Ignore
    @Test
    public void rotateSearchTest(){
        int[] num = test.generateRotateArray(20);
        int target = test.generateTarget(20); //{7,8,9,89457943,1,2,3,4};
    assertEquals(test.searchRotate( num , target), test.rotatesearch( num , target));
    assertEquals(test.searchRotate( num , target), test.rotatesearch( num , target));
//int[] a={0};
//     System.out.println( Arrays.binarySearch( a, 1) );
//     System.out.println( test.binnarySearchIter(a, 1) );
//     System.out.println( test.rotatesearch(a,2 ) );
//     Arrays.sort(num);
//     assertEquals(test.binnarySearchIter(num, 1), Arrays.binarySearch( num, 1));
    }
    
    
    @Ignore
    @Test
    public void removeDTest(){
         int[] num={33,2,5,2,6,-58, 4,0,591,0,59,-34};  
         int[] num0={ 0,0,591,0,11,11,11,11,-11,11};  
          Arrays.sort(num);
          Arrays.sort(num0);
          
          
        System.out.println(test.removeDuplicates(num)+"  : "+Arrays.toString(num));
        System.out.println(test.removeDuplicates_3(num0)+"  : "+Arrays.toString(num0));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    // 
     @Ignore
    @Test
    public void climbTst(){
     System.out.println( test.climbStairs(44));
         int[] num={9,9};   
      System.out.println(test.plusOne(num));
      
      
     
    }
    @Ignore
    @Test
    public void divideTest(){   
        int[] s = {1,2,3,4,5,8,45435,6456,543,52};
//        for(int i: s){
//        assertEquals( i/45, test.divide(i,45));
//        assertEquals(test.divide(5546,i),5546/i);
//        }
//      System.out.println( test.divide( -2147483648, 2));
//      System.out.println( test.divide_2( -2147483648, 2));
      System.out.println( test.divide_2( -2147483648, 1));
      System.out.println( test.divide_2( -2147483648, -1));
//      System.out.println( Integer.MAX_VALUE);
//      System.out.println( Integer.MIN_VALUE);
        
//   System.out.println( test.longestValidParentheses("))))())(())()))(()()(())(())()))(((()()))()()))(())(())()())()(()())((()(((())()())(()())(())((()))))())()))()(())))())()))(()))((()())((()(())))(()))))))))((())(()()((())()()(()))))(((()(())))())))()())))())()()())()(())()(((())()))()()())))()())))()((((((())((())))((())())(((()())())()((((((()())((()()(())(()))(()())()))()(()(()())(()))((())((())))))()()))))()())()))))((((())(())))((()))(()()()()()((())((((())())()())()())(()(()()))())(((()())(()))()))(())()((()(())))))()())())()()(())))((())()()()))(())((()())))))((()((((()(((())()))))(()))()()))(()(())(()((()()()))))()))()()(((()()(())())()(())(()()()))()(()())))()((()((()))))())()(())()(()") );
//   System.out.println( test.longestValidParentheses_2("))))())(())()))(()()(())(())()))(((()()))()()))(())(())()())()(()())((()(((())()())(()())(())((()))))())()))()(())))())()))(()))((()())((()(())))(()))))))))((())(()()((())()()(()))))(((()(())))())))()())))())()()())()(())()(((())()))()()())))()())))()((((((())((())))((())())(((()())())()((((((()())((()()(())(()))(()())()))()(()(()())(()))((())((())))))()()))))()())()))))((((())(())))((()))(()()()()()((())((((())())()())()())(()(()()))())(((()())(()))()))(())()((()(())))))()())())()()(())))((())()()()))(())((()())))))((()((((()(((())()))))(()))()()))(()(())(()((()()()))))()))()()(((()()(())())()(())(()()()))()(()())))()((()((()))))())()(())()(()") );

    }
    @Ignore
    @Test
    public void validPTest(){   
        String[] s = {"()(())", "", "(((()","())))()())" ,"))))())(())()))(()()(())(())())", "))))())(())()))(()()(())(())()))(((()()))()()))(())(())()())()(()())((()(((())()())(()())(())((()))))())()))()(())))())()))(()))((()())((()(())))(()))))))))((())(()()((())()()(()))))(((()(())))())))()())))())()()())()(())()(((())()))()()())))()())))()((((((())((())))((())())(((()())())()((((((()())((()()(())(()))(()())()))()(()(()())(()))((())((())))))()()))))()())()))))((((())(())))((()))(()()()()()((())((((())())()())()())(()(()()))())(((()())(()))()))(())()((()(())))))()())())()()(())))((())()()()))(())((()())))))((()((((()(((())()))))(()))()()))(()(())(()((()()()))))()))()()(((()()(())())()(())(()()()))()(()())))()((()((()))))())()(())()(()"} ;
        
        for(String i: s){
        assertEquals(test.longestValidParentheses(i), test.longestValidParentheses_2(i));
        assertEquals(test.longestValidParentheses_3(i), test.longestValidParentheses_2(i));
        }
//   System.out.println( test.longestValidParentheses("))))())(())()))(()()(())(())()))(((()()))()()))(())(())()())()(()())((()(((())()())(()())(())((()))))())()))()(())))())()))(()))((()())((()(())))(()))))))))((())(()()((())()()(()))))(((()(())))())))()())))())()()())()(())()(((())()))()()())))()())))()((((((())((())))((())())(((()())())()((((((()())((()()(())(()))(()())()))()(()(()())(()))((())((())))))()()))))()())()))))((((())(())))((()))(()()()()()((())((((())())()())()())(()(()()))())(((()())(()))()))(())()((()(())))))()())())()()(())))((())()()()))(())((()())))))((()((((()(((())()))))(()))()()))(()(())(()((()()()))))()))()()(((()()(())())()(())(()()()))()(()())))()((()((()))))())()(())()(()") );
//   System.out.println( test.longestValidParentheses_2("))))())(())()))(()()(())(())()))(((()()))()()))(())(())()())()(()())((()(((())()())(()())(())((()))))())()))()(())))())()))(()))((()())((()(())))(()))))))))((())(()()((())()()(()))))(((()(())))())))()())))())()()())()(())()(((())()))()()())))()())))()((((((())((())))((())())(((()())())()((((((()())((()()(())(()))(()())()))()(()(()())(()))((())((())))))()()))))()())()))))((((())(())))((()))(()()()()()((())((((())())()())()())(()(()()))())(((()())(()))()))(())()((()(())))))()())())()()(())))((())()()()))(())((()())))))((()((((()(((())()))))(()))()()))(()(())(()((()()()))))()))()()(((()()(())())()(())(()()()))()(()())))()((()((()))))())()(())()(()") );

    }
    @Ignore
    @Test
    public void minimumTotalTest(){
        int[][]  num = {{1,2},{4,3}};
        int[][]  num2 = {{1,2,3},{14,1,13},{994,993,999}};
        
//    test.rotate(num);
//    test.rotate(num2);
//    int[] A= {0,1,2,2,2,1,1,0,0,0};
//    test.sortColors(A);
//    System.out.println( Arrays.toString(A) ) ;
   //   
//     System.out.println( test.longestValidParentheses("()(())(()()(()()") );
//    for(int[] a: num2)
//        System.out.println( Arrays.toString(a) ) ;
    }
    @Ignore
    @Test
    public void single3Test(){
       int[] num={9,9,3,9,19,3,99,99,99,19,3,225,19};  
        System.out.println( test.pow(5 ,-3));
        System.out.println( test.pow(5 ,0));
        System.out.println( test.pow(0 ,-3));
  //     System.out.println( test.singleNumber_2(num));
 //     System.out.println( test.rangeBitwiseAnd(0,1));
    }
     @Ignore
     @Test
     public void maxQTest() {
         int[] num={33,25,26,-58, 41,0,59,-34};     
         int[] num0={33,25,26,-58,41,0,59,-34};     
         int[] num1={33,25,26,-58,41,0,59,-34};     
         int[] num2={4, 33 , 25 , 26 , 58 ,1, 41 , 59 };
         int[] num3={-4, 33 , 25 , -26 , 58 ,25,25, 41 , 59 };
////         test.mergeSort(num, 0, num.length-1);
////         Arrays.sort(num0);
//         
////         assertArrayEquals( num0, num1 );
////         int a = test.maxQRun(num);
////         int b = test.maxQRun_2(num);
////         assertEquals(a,b);
//         assertEquals(test.maxQRun_2(num), test.maxQms(num) );
//         assertEquals(test.maxQRun_2(num), test.maxQms(num1) );
         assertEquals(test.maxQRun_2(num2),test.maxQms(num2) );
         assertEquals(test.maxQRun_2(num3),test.maxQms(num3) );
//         
//         assertEquals(test.maxQRun(num2),test.maxQRun_2(num2));
//         assertEquals(test.maxQRun(num2),test.maxQRun_3(num2));
//         
//         assertEquals(test.maxQRun(num3),test.maxQRun_2(num3));
//         assertEquals(test.maxQRun(num3),test.maxQRun_3(num3));
//         assertEquals(test.maxQRun_2(num3),test.maxQRun_3(num3));
     }
}
