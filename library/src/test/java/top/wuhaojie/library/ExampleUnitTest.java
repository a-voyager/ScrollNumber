package top.wuhaojie.library;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getBit() {
        int num = 4321;

        while (num > 0) {
            int i = num % 10;
            System.out.println(i);
            num /= 10;
        }

    }

}