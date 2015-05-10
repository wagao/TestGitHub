/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import myGraph.myGraph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 *Test for myGraph
 * @author Emma
 */
public class myGraphTest {
    
    public myGraphTest() {
    }
    public static myGraph testG;
            
    @BeforeClass
    public static void setUpClass() {
        testG=new myGraph();
    }
    
    @Test
    public void testBFS_S(){
        String A_BFS = " 3 2 4 0 1";
        Assert.assertEquals(  A_BFS, testG.traverseBFS_S(3) );
    }
    
    @Ignore
    @Test
    public void testDFS_S(){
        String A_DFS = "3 3 2 2 4 0 1 1 0 4 3";
        Assert.assertEquals( A_DFS, testG.traverseDFS_S(3) );
    }
    
    @Test
    public void testBFS(){
        int[] A_BFS = {3,2,4,0,1};
        Assert.assertArrayEquals(A_BFS, testG.traverseBFS(3) );
    }
    
    @Test
    public void testDFS(){
        int[] A_DFS = {3, 3, 2 ,2 ,4 ,0, 1, 1 ,0, 4, 3};
        Assert.assertArrayEquals( A_DFS, testG.traverseDFS(3) );
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
