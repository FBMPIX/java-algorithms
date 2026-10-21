package com.interviewprep.dsa.sort;

public final class InsertionSort {
    private InsertionSort() {
    }

    public static void sort(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("values cannot be null");
        }

        for (int i = 1; i < values.length; i++) {
            for (int j = i; j > 0 && values[j] < values[j-1]; j--) {
                int temp = values[j];
                values[j] = values[j-1];
                values[j-1] = temp;
            }
        }
    }
}
