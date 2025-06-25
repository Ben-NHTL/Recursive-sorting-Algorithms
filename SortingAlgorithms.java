/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Hoang Thien Loc Nguyen
 * Student Number: 041165148
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 */

import java.util.Arrays;

/**
 * SortingAlgorithms class provides implementations of various sorting algorithms
 * with performance measurement. Each sorting method copies the input array,
 * sorts the copy, and displays the time taken and sorted results.
 * 
 * Sorting algorithms implemented:
 * - Bubble Sort
 * - Insertion Sort
 * - Selection Sort
 * - Merge Sort (recursive, divide and conquer)
 * - Quick Sort (recursive, divide and conquer)
 * 
 * Each method uses System.nanoTime() to measure the execution time.
 */
public class SortingAlgorithms {

    /**
     * Performs bubble sort on a copy of the input array.
     * Bubble sort repeatedly swaps adjacent elements if they are in wrong order.
     * Time complexity: O(n^2)
     * 
     * @param array input integer array to sort
     */
    public static void bubbleSort(int[] arr) {
        long startTime = System.nanoTime();

        int n = arr.length;

        // Perform bubble sort algorithm
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        long endTime = System.nanoTime();

        // Display the sorting results and timing
        displayResults("Bubble Sort", "Simple sorting algorithm   O(n2) Complexity", true, arr, startTime, endTime);
    }

    /**
     * Performs insertion sort on a copy of the input array.
     * Insertion sort builds the sorted array one item at a time.
     * Time complexity: O(n^2)
     * 
     * @param array input integer array to sort
     */
    public static void insertionSort(int[] arr) {
        long startTime = System.nanoTime();

        // Iterate over each element starting from second
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements greater than key to one position right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert key at the correct position
            arr[j + 1] = key;
        }

        long endTime = System.nanoTime();
        displayResults("Insertion Sort", "Simple sorting algorithm   O(n2) Complexity", true, arr, startTime, endTime);
    }

    /**
     * Performs selection sort on a copy of the input array.
     * Selection sort repeatedly selects the minimum element from unsorted
     * portion and places it at the beginning.
     * Time complexity: O(n^2)
     * 
     * @param array input integer array to sort
     */
    public static void selectionSort(int[] arr) {
        long startTime = System.nanoTime();

        // Iterate through all array elements
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;

            // Find minimum element index in remaining unsorted array
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // Swap found minimum element with first element
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }

        long endTime = System.nanoTime();
        displayResults("Selection Sort", "Simple sorting algorithm   O(n2) Complexity", true, arr, startTime, endTime);
    }

    /**
     * Performs merge sort on a copy of the input array.
     * Merge sort uses recursive divide and conquer approach to sort.
     * Time complexity: O(n log n)
     * 
     * @param array input integer array to sort
     */
    public static void mergeSort(int[] arr) {
        long startTime = System.nanoTime();

        mergeSortHelper(arr, 0, arr.length - 1);

        long endTime = System.nanoTime();
        displayResults("Merge Sort", "Recursive Divide-And-Conquer   O(n log n) Complexity", false, arr, startTime, endTime);
    }

    /**
     * Recursive helper method for merge sort to divide the array into halves
     * and merge sorted halves.
     * 
     * @param arr array to sort
     * @param left left index of the current subarray
     * @param right right index of the current subarray
     */
    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Sort left half recursively
            mergeSortHelper(arr, left, mid);

            // Sort right half recursively
            mergeSortHelper(arr, mid + 1, right);

            // Merge the two sorted halves
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     * 
     * @param arr array containing the two subarrays
     * @param left left index of the first subarray
     * @param mid ending index of the first subarray
     * @param right ending index of the second subarray
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // Calculate lengths of left and right subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays for left and right subarrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        // Merge the temporary arrays back into original array in sorted order
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        // Copy remaining elements of L[] if any
        while (i < n1)
            arr[k++] = L[i++];

        // Copy remaining elements of R[] if any
        while (j < n2)
            arr[k++] = R[j++];
    }

    /**
     * Performs quick sort on a copy of the input array.
     * Quick sort uses recursive divide and conquer approach using a pivot.
     * Time complexity: Average O(n log n), worst O(n^2)
     * 
     * @param array input integer array to sort
     */
    public static void quickSort(int[] arr) {
        long startTime = System.nanoTime();

        quickSortHelper(arr, 0, arr.length - 1);

        long endTime = System.nanoTime();
        displayResults("Quick Sort", "Recursive Divide-And-Conquer   O(n log n) Complexity", true, arr, startTime, endTime);
    }

    /**
     * Recursive helper method for quick sort.
     * 
     * @param arr array to sort
     * @param low lower index of subarray
     * @param high higher index of subarray
     */
    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int pi = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSortHelper(arr, low, pi - 1);
            quickSortHelper(arr, pi + 1, high);
        }
    }

    /**
     * Partitions the array around the pivot element.
     * 
     * @param arr array to partition
     * @param low lower index
     * @param high higher index
     * @return pivot index after partitioning
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // choose last element as pivot
        int i = low - 1;

        // Rearrange elements smaller than pivot to the left
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot at the correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * Utility method to display the sorting results, including algorithm name,
     * description, whether sorting is in-place, the sorted array,
     * and timing information in nanoseconds and milliseconds.
     * 
     * @param name algorithm name
     * @param description brief description of algorithm and complexity
     * @param inPlace true if sorting is done in-place (modifies original array)
     * @param sortedArray the sorted array after sorting
     * @param startTime starting time in nanoseconds
     * @param endTime ending time in nanoseconds
     */
    private static void displayResults(String name, String description, boolean inPlace,
                                       int[] sortedArray, long startTime, long endTime) {
        System.out.println("\n" + name + ": " + description + " - " + (inPlace ? "In-place" : "Not in-place"));
        System.out.println("\nSorted: " + Arrays.toString(sortedArray));
        System.out.println("\nTime taken in nanoseconds: " + (endTime - startTime));
        System.out.println("Time taken in milliseconds: " + ((endTime - startTime) / 1_000_000));
    }
}
