/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javaapplication1.StringOp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 *
 * @author Emma
 */
public class StringOpTest {
    
    
    static StringOp test;
    public StringOpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        test = new StringOp();
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
    public void fullJustifyTest(){
        String[] A= {"Listen","to","many,","speak","to","a","few."};
        System.out.println(test.fullJustify(A, 6));
//        System.out.println(test.fullJustify(A, 16).toArray()[0]);
//        System.out.println(test.fullJustify(A, 16).toArray()[1]);
//        System.out.println(test.fullJustify(A, 16).toArray()[2]);
    }
    
    @Ignore
    @Test
    public void stringMultiplyTest(){
         System.out.println( test.multiply("0013012003","29" ));
        System.out.println( test.multiply("0013012003",2));
        System.out.println( test.multiply("0013012003",9));
//        System.out.println( test.add("000960000123","43985943").toString());
//        System.out.println(  960000123+43985943);
    }
    
    @Ignore
    @Test
    public void minWTest(){
       
                 System.out.println( test.testminW());
//        System.out.println( test.minWindow("kgfidhktkjhlkbgjkylgdracfzjduycghkomrbfbkoowqwgaurizliesjnveoxmvjdjaepdqftmvsuyoogobrutahogxnvuxyezev", "aaaaaaaaabbbb") );
//        System.out.println( test.minWindow("aab", "aab") );
    }
    @Ignore
    @Test
     public void sortTest(){
         String[] a = {"123","4","01"};
         Integer[] aa = {123,4,01};
         Integer[] aaa = {123,4,1,1,1,1,4,01};
     test.sortString(a);
     for(String i : a){
      System.out.print(i.charAt(0)+" " );
      System.out.println(i.charAt(0)^256 );
     }
     
      System.out.println(test.largestNumber(Arrays.asList(aa)));
      System.out.println(test.largestNumber(Arrays.asList(aaa)));
     
     } 
    @Ignore
    @Test
     public void anagramTest() {
//         String[] allWords={"2e","a234wee","abc", "2e","weeeeeeeee","bca","2e","439we1ae"};
//         System.out.println( test.anagrams(allWords));
         char[][] a = { {'A','B','C','E'}, {'S','F','C','S'},{'A','D','E','E'}         };
         
         char[][] map = {{ 'S', 'N', 'B', 'S', 'N' },
                        { 'B', 'A', 'K', 'E', 'A' }, 
                        { 'B', 'K', 'B', 'B', 'K' },
                        { 'S', 'E', 'B', 'S', 'E' }};
         String b="SEE";
         String c="ASA";
         String d="ABCB";
         System.out.println(test.exist(a , b));
         System.out.println(test.exist(a , c));
         System.out.println(test.exist(a , d));
         System.out.println(test.howmanyexist(map , "SNAKES"));
         
        
//         System.out.println(test.fractionToDecimal(1,7));
//         System.out.println(test.fractionToDecimal(2,8));
//        System.out.println(test.strStr("479587439",""));
     }
     @Ignore
     @Test
     public void numDecodingsTest(){
           System.out.println(test.getPermutation(5,1)); 
           System.out.println(test.getPermutation(5,2)); 
           System.out.println(test.getPermutation(5,3)); 
           System.out.println(test.getPermutation(5,4)); 
           System.out.println(test.getPermutation(5,5)); 
           System.out.println(test.getPermutation(5,6)); 
//           System.out.println(test.numDecodings("1"));
//           System.out.println(test.numDecodings("11"));
//           System.out.println(test.numDecodings("12"));
//         String  s = "101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010" ;
//          System.out.println(test.numDecodings(s));
//                  System.out.println(test.numDecodings("6065812287883668764831544958683283296479682877898293612168136334983851946827579555449329483852397155"));
     }
     @Ignore
    @Test
    public void testNext(){
//     System.out.println(Arrays.toString(test.KMPnext_2("abcdabc")));
//     System.out.println(Arrays.toString(test.KMPnext("abcdabc")));
//     String s="";
//     System.out.println( s.length());
     
     String  a="23";
//     System.out.println(test.letterCombinations(a));
//     System.out.println(test.letterCombinations_2(a));
//     
//      System.out.println(test.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
//     for(List<String>  list : test.partition(a))
//     System.out.println(list);
//     System.out.println( a^b^a);
//     System.out.println( a^a);
//     System.out.println( test.KMP("abc1d123ab3c3","123"));
//     System.out.println( test.KMP("mississippi", "issip"));
//     System.out.println(test.addBinary("1010","1011"));
//     System.out.println(Arrays.toString(test.KMPnext_2("abab")));
//     System.out.println(Arrays.toString(test.KMPnext("abab")));
//     System.out.println(Arrays.toString(test.KMPnext("abab")));
    }
}
