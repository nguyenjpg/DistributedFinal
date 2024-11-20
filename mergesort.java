import java.util.Arrays;

public class mergesort {
    

    public static void mergeSort(int[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;

            // Split the array into two halves
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            // Recursively sort each half
            mergeSort(left);
            mergeSort(right);

            // Merge the sorted halves
            merge(array, left, right);
        }
    }

    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge the left and right arrays
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of left[], if any
        while (i < left.length) {
            result[k] = left[i];
            i++;
            k++;
        }

        // Copy the remaining elements of right[], if any
        while (j < right.length) {
            result[k] = right[j];
            j++;
            k++;
        }
    }


}
