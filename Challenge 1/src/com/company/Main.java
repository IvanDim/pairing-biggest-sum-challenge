package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[] intList = new int[0];

        // Input + validation that all the elements are integers
        System.out.println("Input the list of integers divided by commas. ex: \"0,1,2,3,4,5\"");
        try (Scanner scanner = new Scanner(System.in)) {
            String[] inputList = scanner.nextLine().split(",");
            intList = new int[inputList.length];

            for (int i = 0; i < inputList.length; i++) {
                intList[i] = Integer.parseInt(inputList[i]);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid list format.");
            return;
        }

        // First of all I will sort the array.
        // The given examples in the problem are all sorted, but I suppose they are not intended to be.
        // I'll use Quick sort. The time complexity is O(n log n). It's an in-place algorithm.
        quickSort(intList, 0, intList.length);

        // Pairing
        // We can categorize the integers by "negatives", "zeros", "ones" and "positives".
        // We will combine zeros with negatives, because they are useful only paired with them.
        // Ones are not useful pairing with any other integer so we will leave them as a single element every time.
        // And finally we want to pair the biggest positives together and the biggest negatives together
        // so after multiplying them the result will be the biggest.

        // The sum of all the multiplied pairs (I'll add them when I create the pairs so I'll iterate one time less)
        long sum = 0;

        // The list of all the pairs
        List<Pair> pairedList = new ArrayList<>();

        int pointerTop = intList.length - 1;
        int x1;
        int x2;


        // Starting from the biggest positive integers (iteration from top to bottom)
        while (true) {
            x1 = intList[pointerTop];

            // If pointerTop + 1 is index out of bounds, assign 1 to x2
            if (!(pointerTop - 1 == -1))
                x2 = intList[pointerTop - 1];
            else
                x2 = 1;

            if (x1 > 1) {
                if (x2 > 1) {
                    // If both the biggest integer and the second biggest are >1 create a Pair
                    pairedList.add(new Pair(x1, x2));

                    // Multiply the pair and add them to the sum
                    sum = sum + (x1 * x2);

                    // Decrement the pointer by 2 to the next integer
                    pointerTop = pointerTop - 2;
                } else {
                    // If only the biggest integer is >1 create a Pair with one element and stop the iteration
                    // x2 will be taken caren in the second iteration
                    pairedList.add(new Pair(x1));

                    // Add the single element to the sum
                    sum = sum + x1;

                    // The second element is not >1 so I will start an iteration from the bottom
                    break;
                }
            } else {
                // Put the pointer back to the last integer >1
                // or if there are no positive integers it will go out of boundary
                pointerTop++;
                break;
            }
        }

        int pointerBottom = 0;

        // Starting an iteration from the bottom to pointerTop
        while (pointerBottom < pointerTop) {
            x1 = intList[pointerBottom];

            // If pointerBottom + 1 refers to the element with index pointerTop assign a number <1
            if (!(pointerTop == pointerBottom + 1)) {
                x2 = intList[pointerBottom + 1];
            } else {
                x2 = 2;
            }

            if (x1 <= 0) {
                if (x2 <= 0) {
                    //If both the smallest integer and the second smallest are <=0 create a Pair
                    pairedList.add(new Pair(x1, x2));

                    // Multiply the pair and add them to the sum
                    sum = sum + (x1 * x2);

                    // Increment the pointer by 2 to the next integer
                    pointerBottom = pointerBottom + 2;
                } else if (x2 == 1) {
                    // If only one of the integers are <= 0 and the other is ==1 create 2 Pairs
                    pairedList.add(new Pair(x1));
                    pairedList.add(new Pair(x2));

                    // Add the single elements to the sum
                    sum = sum + x1 + x2;

                    // Increment the pointer by 2 to the next integer
                    pointerBottom = pointerBottom + 2;
                } else {
                    // x2 is part of the positive integers or it doesnt exist
                    // Add x1 as as single element
                    pairedList.add(new Pair(x1));

                    // Add the single element to the sum
                    sum = sum + x1;

                    // finish the iteration because next integers are part of the positives or thats the end of the list
                    break;
                }
            } else if (x1 == 1) {
                // Add the one in the list as a single element
                pairedList.add(new Pair(x1));

                // Add the one to the sum
                sum = sum + x1;

                // Increment the pointer by 1 to the next integer
                pointerBottom = pointerBottom + 1;
            } else {
                break;
            }
        }

        System.out.println("Sum: " + sum);

        // To see all the pairs another iteration is required. Uncomment the following to print them.

//        for (Pair pair : pairedList) {
//            System.out.print(pair);
//        }
    }

    private static void quickSort(int[] input, int start, int end) {
        if (end - start < 2) {
            // It's an one element array
            return;
        }

        int pivotIndex = partition(input, start, end);

        // Sorting everything to the left of the pivot
        quickSort(input, start, pivotIndex);

        // Sorting everything to the right of the pivot
        quickSort(input, pivotIndex + 1, end);
    }

    /**
     * This method gives the position of the pivot element in a sorted array (correct sorted position)
     *
     * @param input - the array to be sorted
     * @param start - the index of the first element
     * @param end   - the index of the last element
     * @return the correct position of the pivot
     */
    private static int partition(int[] input, int start, int end) {
        // Using the first element as the pivot
        int pivot = input[start];
        int i = start;
        int j = end;

        // Traverse
        while (i < j) {

            // empty loop
            // we are searching for an element which is less than the pivot or j crosses i
            while (i < j && input[--j] >= pivot) ;
            if (i < j) {
                // we found that element at index j is less than the pivot and we want to move it towards the front of the array
                input[i] = input[j];
            }

            // empty loop
            // we seach for an element greater than the pivot
            while (i < j && input[++i] <= pivot) ;
            if (i < j) {
                // we found an element greater than the pivot and we move it to the end of the array
                input[j] = input[i];
            }
        }

        input[j] = pivot;
        return j;
    }
}
