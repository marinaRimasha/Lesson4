package com.ctco.testSchool;
import com.ctco.testSchool.FirstTask;
import com.ctco.testSchool.SecondTask;
import com.ctco.testSchool.ThirdTask;
import com.ctco.testSchool.FourthTask;
import com.ctco.testSchool.FifthTask;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseTest {

    @Test
    public void testHappyPath_FirstTask() {
        String exp = FirstTask.concatenate(0, 2, "Test");
        assertEquals(exp, "3Test");
    }

    @Test
    public void testNegativeInt_FirstTask() {
        String exp = FirstTask.concatenate(-1, 2, " ");
        assertEquals(exp, "2 ");
    }

    @Test
    public void testNegativeTest_FirstTask() {
        String exp = FirstTask.concatenate(1, 2, "s tring");
        assertNotEquals(exp, "1s tring");
    }

    @Test
    public void testHappy_SecondTask() {
       assertTrue(new SecondTask().isEven(2));
    }

    @Test
    public void testNegative_SecondTask() {
        assertFalse(new SecondTask().isEven(3));
    }


    @Test
    public void testZero_SecondTask() {
        assertTrue(new SecondTask().isEven(0));
    }

    @Test
    public void test_ThirdTask() {
        String [] arr = {"one", "two", "three"};
        String word = "two";
        assertEquals(1, new ThirdTask().getElementPosition(arr, word), "Incorrect index");
    }

    @Test
    public void testNegative_ThirdTask() {
        String [] arr = {""};
        String word = "two";
        assertEquals(-1, new ThirdTask().getElementPosition(arr, word), "returned an index, should return -1");
    }

    @Test
    public void testNull_ThirdTask() {
        String [] arr = {"one", "two", "three"};
        String word = null;
        assertEquals(-1, new ThirdTask().getElementPosition(arr, word), "returned an index, should return -1");
    }

    @Test
    public void testFourthTask() {
        double actual = new FourthTask(2,3).getPerimeter();
        assertEquals(10.0, actual, "Incorrect value of perimeter");
    }

    @Test
    public void testFourthTask_zero() {
        double actual = new FourthTask(0,3).getPerimeter();
        assertEquals(6, actual, "Incorrect value of perimeter");
    }

    @Test
    public void testFourthTask_negInt() {
        double actual = new FourthTask(-2,3).getPerimeter();
        assertEquals(2, actual, "Incorrect value of perimeter");
    }

    @Test
    public void testFifthTask() {
        FourthTask act = new FourthTask(-2,3);
        String actual = new FifthTask().isRectangle(act);
        assertEquals("true", actual, "Incorrect value of perimeter");
    }

}
