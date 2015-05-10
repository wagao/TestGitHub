/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import maze.maze;
import org.junit.Assert;

/**
 *
 * @author Emma
 */
public class mazeTest {
    
    public mazeTest() {
    }
    public static maze mazeTest;
    
    @BeforeClass
    public static void setUpClass() {
        mazeTest = new maze(2,2);
    }
    
    @Test
    public void testPrint(){
//        mazeTest.M.printAdj();
//        mazeTest.printM(4);
        int[][] A={{0,1,1,0},
            {1,0,0,1},
            {1,0,0,1},
            {0,1,1,0}
        };
        for(int i=0;i<A.length;i++){
            assertArrayEquals(A[i], mazeTest.AdjMatrix[i]);
        }
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
