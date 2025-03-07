package HW1;

import java.util.Random;
public class MaxFinder {

    public static int findMax(int[] arr) {
        // Say this if nothing prints
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int max = arr[0];

        // Goes through the array to find the maximum value
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // Updates the maximum if a larger number is found
            }
        }
        return max;
    }

    public static void main (String[] args) {
        // Randomizes the values
        Random r = new Random();
        int[] data = new int[1000]; // Array that stores 1000 random values

        // Prints out all the elements in the array
        System.out.println("All the elements in my randomly generated array:");
        for (int i = 0; i < data.length; i++) {
            data[i] = r.nextInt(1001); // Generates the numbers
            System.out.print(data[i] + "\t");

            // Wraps the output to another line every 10 numbers
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }

        }

        // Identifies the maximum value
        int maxValue = findMax(data);

        System.out.println();
        // Prints the result
        System.out.println("The maximum value in the array is: " + maxValue);
    }
}
