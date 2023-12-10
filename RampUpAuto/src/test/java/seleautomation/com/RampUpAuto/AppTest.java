package seleautomation.com.RampUpAuto;
import main.java.seleautomation.com.RampUpAuto.App;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    App obj = new App();

    public int x = 10;
    public int y = 2;
    @BeforeClass
    public static void beforeAllTest(){
        System.out.println("Started testing the great calculator app");
    }

    @AfterClass
    public static void afterAllTest(){
        System.out.println("Finished testing the great calculator app");
    }

    @Before
    public void beforeEachTest(){
        System.out.println("Start a boring test");
    }

    @After
    public void afterEachTest(){
        System.out.println("Finished a boring test");
    }

    @Test
    public void TC1_Add(){
        assertEquals(12, obj.add(x,y));
    }

    @Test
    public void TC2_Subtract(){
        assertEquals(8, obj.subtract(x,y));
    }

    @Test
    public void TC3_Multible(){
        assertEquals(20, obj.multiply(x,y));
    }

    @Test
    public void TC4_Divide(){
        assertEquals(5, obj.divide(x,y));
    }



    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        assertTrue( true );
    }
}
