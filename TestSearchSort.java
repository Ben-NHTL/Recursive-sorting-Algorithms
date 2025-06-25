/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Hoang Thien Loc Nguyen
 * Student Number: 041165148
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 */

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

/**
 * TestSearchSort is a driver program that allows users to generate random integer arrays,
 * perform recursive binary and linear searches, and sort the arrays using various sorting algorithms.
 * It includes exception handling to manage user input errors and runtime exceptions gracefully.
 */
public class TestSearchSort {
    // Original random array and separate copies for each sorting algorithm
    private static int[] randomArr;
    private static int[] bubbleArray, insertArray, selectArray, mergeArray, quickArray;
    
    // Scanner object for user input
    static Scanner scanner = new Scanner(System.in);

    /**
     * Generates an array of 1000 random integers between 120 and 999.
     * 
     * @return a new int array populated with random values
     */
    public static int[] genRandomInts() {
        SecureRandom rand = new SecureRandom();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(880) + 120; // Values range from 120 to 999
        }
        return arr;
    }

    /**
     * Main menu loop allowing user to select different operations:
     * generating arrays, searching, sorting, or quitting the program.
     * Exception handling is included to manage invalid inputs and runtime errors.
     */
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect your option in the menu below:");
            System.out.println("1: Initialize and populate an array of 1000 random integers.");
            System.out.println("2: Perform recursive binary search.");
            System.out.println("3: Perform recursive linear search.");
            System.out.println("4: Sort the array");
            System.out.println("5: Quit");
            System.out.print(">");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    try {
                        // Generate and store a new random array
                        randomArr = genRandomInts();

                        // Create copies of the array for each sorting algorithm
                        bubbleArray = Arrays.copyOf(randomArr, randomArr.length);
                        insertArray = Arrays.copyOf(randomArr, randomArr.length);
                        selectArray = Arrays.copyOf(randomArr, randomArr.length);
                        mergeArray = Arrays.copyOf(randomArr, randomArr.length);
                        quickArray = Arrays.copyOf(randomArr, randomArr.length);

                        System.out.println("Array of randomly generated integers:");
                        System.out.println(Arrays.toString(randomArr));
                    } catch (Exception e) {
                        // Catch-all for unexpected errors during array generation
                        System.out.println("Error generating random array: " + e.getMessage());
                    }
                    break;

                case "2":
                    if (randomArr == null) {
                        System.out.println("Please initialize the array first.");
                        break;
                    }
                    try {
                        System.out.print("Please enter an integer value to search: ");
                        int targetB = Integer.parseInt(scanner.nextLine());

                        // Prepare a sorted copy for binary search (binary search requires sorted array)
                        int[] sortedForBinary = Arrays.copyOf(randomArr, randomArr.length);
                        Arrays.sort(sortedForBinary);

                        System.out.println(Arrays.toString(randomArr));

                        long startB = System.nanoTime();
                        int indexB = Searching.binarySearch(sortedForBinary, targetB, 0, sortedForBinary.length - 1);
                        long endB = System.nanoTime();

                        if (indexB != -1)
                            System.out.println("\n" + targetB + " was found at index position " + indexB + " : Recursive Binary Search.");
                        else
                            System.out.println("\n" + targetB + " was not found.");

                        System.out.println("\nTime taken in nanoseconds: " + (endB - startB));
                        System.out.println("Time taken in milliseconds: " + ((endB - startB) / 1000000));
                    } catch (NumberFormatException e) {
                        // Handle case when input is not a valid integer
                        System.out.println("Invalid input... Please enter a valid integer.");
                    } catch (Exception e) {
                        // Catch-all for other runtime exceptions in binary search
                        System.out.println("An error occurred during binary search: " + e.getMessage());
                    }
                    break;

                case "3":
                    if (randomArr == null) {
                        System.out.println("Please initialize the array first.");
                        break;
                    }
                    try {
                        System.out.print("Please enter an integer value to search: ");
                        int targetL = Integer.parseInt(scanner.nextLine());

                        System.out.println(Arrays.toString(randomArr));

                        long startL = System.nanoTime();
                        int indexL = Searching.linearSearch(randomArr, targetL, 0);
                        long endL = System.nanoTime();

                        if (indexL != -1)
                            System.out.println("\n" + targetL + " was found at index position " + indexL + " : recursive linear search.");
                        else
                            System.out.println("\n" + targetL + " was not found.");

                        System.out.println("\nTime taken in nanoseconds: " + (endL - startL));
                        System.out.println("Time taken in milliseconds: " + ((endL - startL) / 1000000));
                    } catch (NumberFormatException e) {
                        // Handle invalid integer input
                        System.out.println("Invalid input... Please enter a valid integer.");
                    } catch (Exception e) {
                        // Catch-all for unexpected runtime exceptions in linear search
                        System.out.println("An error occurred during linear search: " + e.getMessage());
                    }
                    break;

                case "4":
                    if (randomArr == null) {
                        System.out.println("Please initialize the array first.");
                        break;
                    }
                    // Launch the sort submenu to select sorting algorithm
                    sortSubMenu();
                    break;

                case "5":
                    // Exit program
                    System.out.println("Exiting...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option... Please try again.");
            }
        }
    }

    /**
     * Displays the sorting submenu and allows the user to select a sorting algorithm.
     * Executes the chosen algorithm on the respective copy of the original array.
     * Exceptions during sorting are caught and reported.
     */
    public static void sortSubMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\nSelect a sorting algorithm to sort the data array\n");
            System.out.println("    B. Bubble Sort");
            System.out.println("    I. Insertion Sort");
            System.out.println("    S. Selection Sort");
            System.out.println("    M. Merge Sort");
            System.out.println("    Q. Quick Sort");
            System.out.println("    R. Return to Main Menu");
            System.out.print("\n>");

            String choice = scanner.nextLine().toUpperCase();
            try {
                switch (choice) {
                    case "B":
                        System.out.println("\n" + Arrays.toString(bubbleArray));
                        SortingAlgorithms.bubbleSort(bubbleArray);
                        break;
                    case "I":
                        System.out.println("\n" + Arrays.toString(insertArray));
                        SortingAlgorithms.insertionSort(insertArray);
                        break;
                    case "S":
                        System.out.println("\n" + Arrays.toString(selectArray));
                        SortingAlgorithms.selectionSort(selectArray);
                        break;
                    case "M":
                        System.out.println("\n" + Arrays.toString(mergeArray));
                        SortingAlgorithms.mergeSort(mergeArray);
                        break;
                    case "Q":
                        System.out.println("\n" + Arrays.toString(quickArray));
                        SortingAlgorithms.quickSort(quickArray);
                        break;
                    case "R":
                        // Return to main menu
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid selection.");
                }
            } catch (Exception e) {
                // Catch and report any errors during sorting
                System.out.println("An error occurred during sorting: " + e.getMessage());
            }
        }
    }
}
