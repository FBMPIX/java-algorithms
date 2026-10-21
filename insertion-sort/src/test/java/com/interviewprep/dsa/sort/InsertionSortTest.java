package com.interviewprep.dsa.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class InsertionSortTest {
    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> InsertionSort.sort(null));
    }

    @Test
    void handlesEmptyArray() {
        int[] values = {};

        InsertionSort.sort(values);

        assertArrayEquals(new int[] {}, values);
    }

    @Test
    void handlesSingleValue() {
        int[] values = { 7 };

        InsertionSort.sort(values);

        assertArrayEquals(new int[] { 7 }, values);
    }

    @Test
    void sortsTwoValues() {
        int[] values = { 9, 2 };

        InsertionSort.sort(values);

        assertArrayEquals(new int[] { 2, 9 }, values);
    }

    @Test
    void sortsGeneralInput() {
        int[] values = { 7, 3, 5, 2, 9, 1 };

        InsertionSort.sort(values);

        assertArrayEquals(new int[] { 1, 2, 3, 5, 7, 9 }, values);
    }

    @Test
    void keepsSortedInputSorted() {
        int[] values = { 1, 2, 3, 4, 5 };

        InsertionSort.sort(values);

        assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, values);
    }

    @Test
    void sortsReverseInput() {
        int[] values = { 5, 4, 3, 2, 1 };

        InsertionSort.sort(values);

        assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, values);
    }

    @Test
    void preservesDuplicateValues() {
        int[] values = { 4, 2, 4, 1, 2 };

        InsertionSort.sort(values);

        assertArrayEquals(new int[] { 1, 2, 2, 4, 4 }, values);
    }

    @Test
    void sortsNegativeValues() {
        int[] values = { -3, -8, -1, -5 };

        InsertionSort.sort(values);

        assertArrayEquals(new int[] { -8, -5, -3, -1 }, values);
    }

    @Test
    void sortsMixedValuesAndExtremes() {
        int[] values = { 0, Integer.MAX_VALUE, -1, Integer.MIN_VALUE, 8 };

        InsertionSort.sort(values);

        assertArrayEquals(
                new int[] { Integer.MIN_VALUE, -1, 0, 8, Integer.MAX_VALUE },
                values);
    }
}
