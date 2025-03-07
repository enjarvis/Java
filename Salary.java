package Assignment2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Salary extends Thread {
    private final String fileName; // stores the name of the file we are processing
    private static final Lock fileLock = new ReentrantLock(); // lock that prevents multiple threads from writing at the same time
    private static final String OUTPUT_FILE = "src/main/java/Assignment2/output";

    // Sets the input file name
    public Salary(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            // Reads all the lines from the input files
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            double maxSalary = Double.MIN_VALUE;
            double minSalary = Double.MAX_VALUE;
            double totalSalary = 0;
            int count = 0;

            // Processes each line
            for (String line : lines) {
                try {
                    double salary = Double.parseDouble(line.trim());
                    maxSalary = Math.max(maxSalary, salary);
                    minSalary = Math.min(minSalary, salary);
                    totalSalary += salary;
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid entry in " + fileName + ": " + line);
                }
            }

            // Calculates the average salary
            double avgSalary = (count == 0) ? 0 : totalSalary / count;
            String result = String.format(
                    "Thread Name: %s%nFile " +
                            "Name: %s%n" +
                            "The Maximum Salary: %.2f%n" +
                            "The Minimum Salary: %.2f%n" +
                            "The Average Salary: %.2f%n" +
                            "The task was concluded: %s%n%n",
                    Thread.currentThread().getName(), fileName, maxSalary, minSalary, avgSalary, LocalDateTime.now()
            );

            // Prints the result
            System.out.println("Result: " + result);

            // Writes the result in the output file
            fileLock.lock();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
                writer.write(result);
                writer.close();
            } finally {
                fileLock.unlock();
            }

        } catch (IOException e) {
            System.out.println("Error while processing " + fileName + ": " + e.getMessage());
        }
    }
}

class Threads {
    public static void main(String[] args) {
        // Lists of input paths
        String[] inputFiles = {"src/main/java/Assignment2/Input1",
                "src/main/java/Assignment2/Input2",
                "src/main/java/Assignment2/Input3"};
        Salary[] threads = new Salary[inputFiles.length];

        // Starts a thread for each input file
        for (int i = 0; i < inputFiles.length; i++) {
            threads[i] = new Salary(inputFiles[i]);
            threads[i].start();
        }

        // Waits for the threads to finish
        for (Salary thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        // Append final summary to the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output", true))) {
            System.out.println("This summary was completed using three parallel threads.\n");
        } catch (IOException e) {
            System.out.println("Final summary error: " + e.getMessage());
        }
    }
}
