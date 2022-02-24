package day2;

import java.util.Scanner;

public class Assignment1 {

    public static void main(String [] args) {
        testInput();
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
}
