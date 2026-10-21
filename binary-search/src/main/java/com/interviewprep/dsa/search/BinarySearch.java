package com.interviewprep.dsa.search;

public final class BinarySearch {
    private BinarySearch() {
    }

    public static int indexOf(int[] sortedValues, int target) {
        if (sortedValues == null) {
            throw new IllegalArgumentException();
        }

        int high = sortedValues.length - 1;
        int low = 0;

        while (low <= high) {
            int middle = low + (high - low) / 2;

            if (target > sortedValues[middle]) {
                low = middle + 1;
            } else if (target < sortedValues[middle]) {
                high = middle - 1;
            } else {
                return middle;
            }
        }

        return -1;
    }

    public static boolean contains(int[] sortedValues, int target) {
        if (sortedValues == null) {
            throw new IllegalArgumentException();
        }
        int high = sortedValues.length - 1;
        int low = 0;

        while (low <= high) {
            int middle = low + (high - low) / 2;

            if (target > sortedValues[middle]) {
                low = middle + 1;
            } else if (target < sortedValues[middle]) {
                high = middle - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
