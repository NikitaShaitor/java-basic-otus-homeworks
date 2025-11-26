package ru.otus.java.basic;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class ExtractAfterLastOneTest {

    @Test
    void testExtractWithSingleElement() {
        int[] result = ArrayProcessor.extractAfterLastOne(new int[]{1, 2});
        assertArrayEquals(new int[]{2}, result);
    }

    @Test
    void testExtractMultipleElements() {
        int[] result = ArrayProcessor.extractAfterLastOne(new int[]{1, 2, 1, 2, 2});
        assertArrayEquals(new int[]{2, 2}, result);
    }

    @Test
    void testNoOnesInArray() {
        Throwable exception = assertThrows(RuntimeException.class,
                () -> ArrayProcessor.extractAfterLastOne(new int[]{2, 2}));
        assertTrue(exception.getMessage().contains("Массив не содержит ни одной единицы"));
    }

    @Test
    void testEmptyArray() {
        Throwable exception = assertThrows(RuntimeException.class,
                () -> ArrayProcessor.extractAfterLastOne(new int[]{}));
        assertTrue(exception.getMessage().contains("Массив не содержит ни одной единицы"));
    }
}
