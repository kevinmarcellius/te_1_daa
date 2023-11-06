package TE_1;


public class RadixSort {
    
    // A utility function to get the maximum value in an array
    // time complexity: O(n)
    private static long memoryUsage;

    
    // Run your algorithm here



    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // A function to do counting sort of arr[] based on the digit represented by exp
    // time complexity: O(n)
    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Initialize count array
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // Store count of occurrences in count[]
        for (int value : arr) {
            count[(value / exp) % 10]++;
        }

        // Change count[i] so that count[i] contains the actual
        // position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to the current digit
        // System.arraycopy(output, 0, arr, 0, n);

        // Copy the output array to arr[], so that arr[] now contains sorted numbers according to the current digit, using for loop
        for (int i = 0; i < n; i++) {
             arr[i] = output[i];
        }
    }

    // The main function to implement Radix Sort
    // time complexity: O(d*(n+b))
    // d = number of digits
    // n = number of elements
    // b = base for representing numbers
    public static void radixSort(int[] arr) {
        int max = getMax(arr);

        // Do counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    public static long getMemoryUsage() {
		return memoryUsage;
	}


}
