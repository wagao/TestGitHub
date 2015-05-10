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
import javaapplication1.WordDistanceFinder;
import java.util.Arrays;
import junit.framework.Assert;

/**
 *
 * @author Emma
 */
public class wordDistangceFinderTest {
    
    public wordDistangceFinderTest() {
    }
    
    
    public static WordDistanceFinder finder;
    
    @BeforeClass
    public static void setUpClass() {
        finder = new WordDistanceFinder(Arrays.asList("the","quick","brown","fox","quick"));
    }
    
    @Test
    public void testDistance(){
        Assert.assertEquals( 3, finder.finder("the", "fox"));
        Assert.assertEquals( 1, finder.finder("quick", "fox"));
        Assert.assertEquals( 1, finder.finder("quick", "the"));
        Assert.assertEquals( -1, finder.finder("hquick", "the"));
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
