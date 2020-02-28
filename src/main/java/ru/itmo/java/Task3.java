package ru.itmo.java;

import java.util.Arrays;
import java.util.HashMap;

class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return new int[]{};
        }
        int lastElement = inputArray[inputArray.length - 1];
        System.arraycopy(inputArray, 0, inputArray, 1, inputArray.length - 1);
        inputArray[0] = lastElement;
        return inputArray;
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     *
     * Если входной массив пуст или равен null - вернуть 0
     *
     * Пример: 2 4 6 -> 24
     */
    int getMaxProduct(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) return 0;
        if (inputArray.length == 1) return inputArray[0];
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE,
                min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int i : inputArray) {
            if (max1 < i) {
                max2 = max1;
                max1 = i;
            } else max2 = Math.max(max2, i);
            if (min1 > i) {
                min2 = min1;
                min1 = i;
            } else min2 = Math.min(min2, i);
        }
        return Math.max(max1 * max2, min1 * min2);
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     *
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {
        if (input == null || input.length() == 0) return 0;
        int cnt = 0;
        for (int i = 0; i < input.length(); i++) {
            char chr = Character.toLowerCase(input.charAt(i));
            if (chr == 'a' || chr == 'b') cnt++;
        }
        return (int) ((100L * cnt) / input.length());
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {
        if (input == null) return false;
        for(int l = 0, r = input.length() - 1; l < r; l++, r--) {
            if(input.charAt(l) != input.charAt(r)) return false;
        }
        return true;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {
        if (input == null || input.length() == 0) return "";
        StringBuilder res = new StringBuilder();
        int cnt = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) != input.charAt(i - 1)) {
                res.append(input.charAt(i - 1)).append(cnt);
                cnt = 0;
            }
            cnt++;
        }
        res.append(input.charAt(input.length() - 1)).append(cnt);
        return res.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String one, String two) {
        if (one == null || two == null
                || one.length() == 0 || two.length() == 0
                || one.length() != two.length()) return false;
        //We can use simple array[256] for ASCII, but String is UTF-16 encoded
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < one.length(); i++) {
            cnt.put(one.codePointAt(i), cnt.getOrDefault(one.codePointAt(i), 0) + 1);
        }
        for (int i = 0; i < two.length(); i++) {
            cnt.put(two.codePointAt(i), cnt.getOrDefault(two.codePointAt(i), 0) - 1);
        }
        for (int i : cnt.values()) if (i != 0) return false;
        return true;
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {
        // O(length*log2(length)), M(1) --> sort
        // O(length), M(length) --> HashMap
        // but, in task said about array, so only ASCII supported
        if (s == null || s.length() == 0) return false;
        int[] cnt = new int[256];
        Arrays.fill(cnt, 0);
        s.chars().forEach(i -> cnt[i]++);
        for (int i = 0; i < 256; i++) if (cnt[i] > 1) return false;
        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     *
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {
        if (m == null || m.length == 0) return new int[][]{{}, {}};
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i && j < m[i].length; j++) {
                int tmp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = tmp;
            }
        }
        return m;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     *
     * Запрещается пользоваться функций join
     *
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {
        if (inputStrings == null) return "";
        if (separator == null) separator = ' ';
        StringBuilder result = new StringBuilder();
        for (String s : inputStrings) result.append(s).append(separator);
        if (result.length() > 0) result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {
        if (inputStrings == null || prefix == null) return 0;
        int result = 0;
        for (String s : inputStrings) if (s.startsWith(prefix)) result++;
        return result;
    }
}
