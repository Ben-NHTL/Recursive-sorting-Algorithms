/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Hoang Thien Loc Nguyen
 * Student Number: 041165148
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 */

/**
 * Searching class provides implementations of recursive search algorithms:
 * - Recursive Binary Search on a sorted array
 * - Recursive Linear Search on an unsorted array
 * 
 * Each method returns the index of the target if found, or -1 if not found.
 */
public class Searching {

    /**
     * Performs a recursive binary search on a sorted array to find the target value.
     * Binary search repeatedly divides the search interval in half.
     * 
     * @param arr the sorted array of integers to search
     * @param target the integer value to search for
     * @param low the lower bound index of the current search interval
     * @param high the upper bound index of the current search interval
     * @return the index of target if found; otherwise, -1 if target is not present
     */
    public static int binarySearch(int[] arr, int target, int low, int high) {
        // Base case: if low index passes high, target not found
        if (low > high) return -1;

        // Calculate middle index of current search interval
        int mid = low + (high - low) / 2;

        // Check if middle element is the target
        if (arr[mid] == target) return mid;

        // If middle element is greater than target, search left half
        if (arr[mid] > target) return binarySearch(arr, target, low, mid - 1);

        // Otherwise, search right half
        return binarySearch(arr, target, mid + 1, high);
    }

    /**
     * Performs a recursive linear search on the array to find the target value.
     * Linear search checks each element sequentially.
     * 
     * @param arr the array of integers to search
     * @param target the integer value to search for
     * @param index the current index being checked in the array
     * @return the index of target if found; otherwise, -1 if target is not present
     */
    public static int linearSearch(int[] arr, int target, int index) {
        // Base case: if index exceeds array length, target not found
        if (index >= arr.length) return -1;

        // If current element matches target, return index
        if (arr[index] == target) return index;

        // Recursively check next element
        return linearSearch(arr, target, index + 1);
    }
}
