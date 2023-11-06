package TE_1;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class TE_1 {
    public static void main(String[] args) {
        String filePath = "dataset_100000_reversed.txt";

        try {
            // Create a Scanner to read the file
            Scanner fileScanner = new Scanner(new File(filePath));

            // Create an ArrayList to store the integers
            ArrayList<Integer> integerList = new ArrayList<>();

            // Read the file line by line and parse integers
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                try {
                    int number = Integer.parseInt(line);
                    integerList.add(number);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping non-integer line: " + line);
                }
            }

            // Close the file scanner
            fileScanner.close();

            // Convert the ArrayList to an array
            
            int[] dataset = new int[integerList.size()];
            int[] dataset2 = new int[integerList.size()];
         
            // Print the array of integers
            for (int i = 0; i < dataset.length; i++) {
                dataset[i] = integerList.get(i);
                dataset2[i] = integerList.get(i);
            }
            
            integerList = null;

            System.gc();
            Thread.sleep(1000);

            // For better readibility, I only print the dataset if it is small enough (n < 50)
            System.out.println(filePath + " contains " + dataset.length + " integers.");
            if (dataset.length <= 50) {
                System.out.println("Dataset : "+ Arrays.toString(dataset));
            }

            double startTime = dataset.length <= 1000 ? System.nanoTime() : System.currentTimeMillis();

            long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            RadixSort.radixSort(dataset);
            long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            long memoryUsage = memoryAfter - memoryBefore;
         
            double endTime = dataset.length <= 1000 ? System.nanoTime() : System.currentTimeMillis();

            double elapsedTime = (endTime - startTime) / (dataset.length <= 1000 ? 1_000_000.0 : 1);

            System.out.println("Sorting algorithm: " +  "RadixSort");
            System.out.println("Runtime: " + elapsedTime +  " milliseconds");

            // total merge cost
            //System.out.println("Total merge cost: " + PeekSort.getTotalMergeCosts() + "\n");
    
            System.out.println("Memorybefore : " + memoryBefore + " bytes");
            System.out.println("Memory after : " + memoryAfter + " bytes");
            System.out.println("Used Memory : " + memoryUsage + " bytes");
 
            if (dataset.length <= 50) {
                System.out.println("Result : "+ Arrays.toString(dataset));
            }
            else {
                // print the sorted array into a .txt file if the dataset is too large
                printArray(dataset);
            }
            
            System.err.println("--------------------------------------------------");
            System.gc();
            Thread.sleep(1000);

            // executing peeksort
            if (dataset.length <= 50) {
                System.out.println("Dataset : "+ Arrays.toString(dataset2));
            }

            startTime = dataset.length <= 1000 ? System.nanoTime() : System.currentTimeMillis();

            memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            PeekSort.peeksort(dataset2, 0, dataset.length - 1);
            memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            memoryUsage = memoryAfter - memoryBefore;
         
            endTime = dataset.length <= 1000 ? System.nanoTime() : System.currentTimeMillis();

            elapsedTime = (endTime - startTime) / (dataset.length <= 1000 ? 1_000_000.0 : 1);

            System.out.println("Sorting algorithm: " +  "PeekSort");
            System.out.println("Runtime: " + elapsedTime +  " milliseconds");

            // total merge cost
            //System.out.println("Total merge cost: " + PeekSort.getTotalMergeCosts() + "\n");
    
            System.out.println("Memorybefore : " + memoryBefore + " bytes");
            System.out.println("Memory after : " + memoryAfter + " bytes");
            System.out.println("Used Memory : " + memoryUsage + " bytes");
 
            if (dataset.length <= 50) {
                System.out.print("Result : "+ Arrays.toString(dataset2));
            }
            else {
                // print the sorted array into a .txt file if the dataset is too large
                printArray(dataset);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    // print the sorted array into a .txt file
    public static void printArray(int[] arr) {
        try {
            File file = new File("sorted_dataset.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            java.io.FileWriter fw = new java.io.FileWriter(file);
            for (int i = 0; i < arr.length; i++) {
                fw.write(arr[i] + "\n");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
