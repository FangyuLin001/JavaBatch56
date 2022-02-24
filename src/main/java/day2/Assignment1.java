package day2;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class Assignment1 {

    public static void main(String [] args) {
        // test 13
        testInput();

        // test 14
        int [] a = new int[]{-6,-2,-3,-4,-5,-1};
        int [] b = new int[]{1,2,3,4,5,6,7};

        int [] newAB = merge2Arrays(a,b);
        System.out.println("New Merged Array");
        for(int ab : newAB) {
            System.out.print(ab + " ");
        }
        System.out.println();

        // test 15
        System.out.println("Array a: second largest: " + findSecond(a));
        System.out.println("Array b: second largest: " + findSecond(b));
    }

    // 13. write a program for the following activity
    public static void testInput() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter x (number or 'q' to quit): ");
            String x = scanner.nextLine();

            if(x.equals("q")) {
                break;
            } else {
                int n = Integer.parseInt(x);
                if(n > 0) {
                    doSomething(n);
                } else {
                    System.out.println("Please Enter a value x > 0 !!!");
                }
            }
        }
    }

    private static void doSomething(int n) {
        while(n > 0) {
            System.out.println("Displace result " + n);
            n--;
        }
    }

    // question 14 merge 2 arrays
    public static int [] merge2Arrays(int [] a, int [] b) {
        int lenA = a.length;
        int lenB = b.length;
        int [] newAB = new int[lenA+lenB];

        int i = 0;
        for(int valueA : a) {
            newAB[i] = valueA;
            i++;
        }
        for(int valueB : b) {
            newAB[i] = valueB;
            i++;
        }
        return newAB;
    }

    // question 15 find the second large number in array
    public static int findSecond(int [] a) {
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for(int valueA : a) {
            set.add(valueA);
        }

        if(set.size() < 2) {
            return -1;
        }
        set.pollFirst();

        return set.first();
    }
}
