package ru.otus.java.basic;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ArrayProcessor.class);

    public static int[] extractAfterLastOne(int[] arr) throws RuntimeException {
        try {

            int lastIndex = -1;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 1) {
                    lastIndex = i;
                }
            }

            if (lastIndex == -1) {
                String message = "Массив не содержит ни одной единицы";
                logger.error(message);
                throw new RuntimeException(message);
            }

            return Arrays.copyOfRange(arr, lastIndex + 1, arr.length);
        } catch (Exception e) {
            logger.error("Возникла ошибка при обработке массива", e);
            throw e;
        }
    }
}
