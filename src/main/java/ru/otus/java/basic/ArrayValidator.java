package ru.otus.java.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayValidator {

    private static final Logger logger = LoggerFactory.getLogger(ArrayValidator.class);

    public static boolean isValidArray(int[] arr) {
        try {

            if (arr == null || arr.length == 0) {
                logger.info("Переданный массив является null или пустым.");
                return false;
            }

            boolean hasOne = false;
            boolean hasTwo = false;

            for (int num : arr) {
                switch (num) {
                    case 1:
                        hasOne = true;
                        break;
                    case 2:
                        hasTwo = true;
                        break;
                    default:
                        logger.warn("Обнаружено недопустимое значение {}", num);
                        return false;
                }

                if (hasOne && hasTwo) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            logger.error("Ошибка при проверке массива", e);
            return false;
        }
    }
}
