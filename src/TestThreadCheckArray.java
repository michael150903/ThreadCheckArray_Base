
import java.util.ArrayList;
import java.util.Scanner;

public class TestThreadCheckArray {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Thread thread1, thread2;

            System.out.println("Enter array size");
            int num = input.nextInt();

            ArrayList<Integer> arrayList = new ArrayList<>(num);

            System.out.println("Enter numbers for array");

            while (arrayList.size() < num) {
                arrayList.add(input.nextInt());
            }

            System.out.println("Enter number");
            num = input.nextInt();

            SharedData sd = new SharedData(arrayList, num);

            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
                return;
            }

            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }

            System.out.println("Solution for b : " + sd.getB()
                    + ",n = " + sd.getArray().size());

            System.out.print("I:    ");
            for (int index = 0; index < sd.getArray().size(); index++) {
                System.out.print(index + "    ");
            }

            System.out.println();
            System.out.print("A:    ");

            for (Integer value : sd.getArray()) {
                System.out.print(value);
                int counter = 5;
                int temp = value;

                while (true) {
                    temp /= 10;
                    counter--;
                    if (temp == 0) break;
                }

                for (int i = 0; i < counter; i++) {
                    System.out.print(" ");
                }
            }

            System.out.println();
            System.out.print("C:    ");

            for (boolean value : sd.getWinArray()) {
                if (value) {
                    System.out.print("1    ");
                } else {
                    System.out.print("0    ");
                }
            }

            System.out.println();
        }
    }
}
