package com.ctco.testSchool;
import com.ctco.testSchool.*;
import com.ctco.testSchool.FifthTask;
import com.ctco.testSchool.FirstTask;
import com.ctco.testSchool.FourthTask;
import com.ctco.testSchool.SecondTask;
import com.ctco.testSchool.ThirdTask;
import com.ctco.testSchool.SeventhTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    public void testFirstTask() {
        assertEquals("3Test", FirstTask.concatenate(0, 2, "Test"), "Check smth");
        // if first task wouldnt be static ==> create object of FirstTask firstTask = new FirstTask();
        // firstTask. will work then
    }

    @Test
    @DisplayName("This test is cool , do not remove it")
    public void testSecondTask() {
        assertEquals(true, new SecondTask().isEven(2), "didnt work");
    }

    @Test
    public void test_ThirdTask() {
        ThirdTask thirdTask = new ThirdTask();
        String [] arr = {"one", "two", "three"};
        String word = "two";
        assertEquals(1, thirdTask.getElementPosition(arr, word), "Incorrect index");
    }

    @Test
    public void testFourthTask() {
        FourthTask fourthTask = new FourthTask(0, 0);
//        double actual = new FourthTask(2,3).getPerimeter();
        assertEquals(5.0, fourthTask.getPerimeter(), "Incorrect value of perimeter");
    }

    @Test
    public void testFifthTask() {
        FourthTask fourthTask = new FourthTask(-2,3);
        String act = new FifthTask().isRectangle(fourthTask);
        assertEquals("true", act, "Incorrect value of perimeter");
    }
//
//    @Test
//    public void testSixthTask() {
//
//        assertEquals("positive", com.ctco.testSchool.SixthTask, "Incorrect value of perimeter");
//    }


    @Test
    public void testSevenTask() {
    SeventhTask.Dog dog = new SeventhTask.Dog("Bublik");
        assertEquals(null, SeventhTask.getDogBreed(null), "Incorrect value of perimeter"); //give dog in dogBreed
    }

//    @Test
//    public void testEighthTask() {
//        SeventhTask.Dog dog = new EightTask().Dog("Bublik");
//        assertEquals(null, SeventhTask.getDogBreed(null), "Incorrect value of perimeter"); //give dog in dogBreed
//    }


}