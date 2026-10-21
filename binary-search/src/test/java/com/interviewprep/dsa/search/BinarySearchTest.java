package com.interviewprep.dsa.search;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BinarySearchTest {
    @Test
    void indexOfRejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> BinarySearch.indexOf(null, 10));
    }

    @Test
    void containsRejectsNullArray() {
        assertThrows(IllegalArgumentException.class, () -> BinarySearch.contains(null, 10));
    }

    @Test
    void emptyArrayDoesNotContainTarget() {
        int[] values = {};

        assertEquals(-1, BinarySearch.indexOf(values, 10));
        assertFalse(BinarySearch.contains(values, 10));
    }

    @Test
    void findsOnlyElement() {
        int[] values = { 7 };

        assertEquals(0, BinarySearch.indexOf(values, 7));
        assertTrue(BinarySearch.contains(values, 7));
    }

    @Test
    void missesSingleElement() {
        int[] values = { 7 };

        assertEquals(-1, BinarySearch.indexOf(values, 6));
        assertFalse(BinarySearch.contains(values, 6));
    }

    @Test
    void findsFirstElement() {
        int[] values = { 2, 5, 8, 12, 16 };

        assertEquals(0, BinarySearch.indexOf(values, 2));
    }

    @Test
    void findsMiddleElement() {
        int[] values = { 2, 5, 8, 12, 16 };

        assertEquals(2, BinarySearch.indexOf(values, 8));
    }

    @Test
    void findsLastElement() {
        int[] values = { 2, 5, 8, 12, 16 };

        assertEquals(4, BinarySearch.indexOf(values, 16));
    }

    @Test
    void returnsNegativeOneForMissingValueBetweenElements() {
        int[] values = { 2, 5, 8, 12, 16 };

        assertEquals(-1, BinarySearch.indexOf(values, 10));
    }

    @Test
    void searchesNegativeAndPositiveValues() {
        int[] values = { -20, -5, -1, 0, 9, 30 };

        assertEquals(1, BinarySearch.indexOf(values, -5));
        assertEquals(4, BinarySearch.indexOf(values, 9));
    }

    @Test
    void duplicateTargetMayReturnAnyMatchingIndex() {
        int[] values = { 1, 3, 3, 3, 8 };

        int index = BinarySearch.indexOf(values, 3);

        assertTrue(index >= 1 && index <= 3);
        assertEquals(3, values[index]);
    }

    @Test
    void searchDoesNotMutateInput() {
        int[] values = { 2, 5, 8, 12, 16 };
        int[] expected = values.clone();

        BinarySearch.indexOf(values, 12);
        BinarySearch.contains(values, 7);

        assertArrayEquals(expected, values);
    }
}
