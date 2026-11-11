import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry point for the parallel subset-sum check.
 * <p>
 * Reads a list of numbers and a target sum from standard input, then starts
 * two {@link ThreadCheckArray} workers that search for a subset of the numbers
 * whose sum equals the target. Prints the solution as a table of indexes,
 * values and selection markers, or {@code "Sorry"} if no solution exists.
 */
public class TestThreadCheckArray {
    /**
     * Reads the input, runs the two worker threads, waits for them to finish
     * and prints the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Thread thread1, thread2;
            System.out.println("Enter array size");
            int num  = input.nextInt();
            ArrayList<Integer> array = new ArrayList<>(num);
            System.out.println("Enter numbers for array");

            for (int index = 0; index < num; index++)
                array.add(input.nextInt());

            System.out.println("Enter number");
            num = input.nextInt();

            SharedData sd = new SharedData(array, num);

            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
            thread1.start();
            thread2.start();
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }
            System.out.println("Solution for b : " + sd.getB() + ",n = " + sd.getArray().size());
            System.out.print("I:    ");
            for(int index = 0; index < sd.getArray().size() ; index++)
                System.out.print(index + "    ");
            System.out.println();
            System.out.print("A:    ");
            for (int index : sd.getArray()) {
                System.out.print(index);
                int counter = 5;
                while (true) {
                    index = index / 10;
                    counter--;
                    if (index == 0) break;
                }
                for (int i = 0; i < counter; i++) System.out.print(" ");
            }
            System.out.println();
            System.out.print("C:    ");
            for (boolean index : sd.getWinArray()) {
                if (index) System.out.print("1    ");
                else System.out.print("0    ");
            }
        }
    }
}