package day3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Assignment3 {

    public static void main(String [] args) {
        // checking keywords
        byte b = 1;
        short s = 2;
        int i = 3;
        long l = 4L;
        float f = 5.6f;
        double d = 7.8;
        final char c = 'c';
        boolean bool = true;

        if(bool) {
            i = 30;
        } else {
            l = 40L;
            System.out.println(l);
        }

        switch (c) {
            case 'd':
                bool = false;
                System.out.println(bool);
                break;
            case 'c':
                s -= 1;
                System.out.println(s);
                break;
            default:
                f *= 2;
        }

        for(int j=0; j<i; j++, i--) {
            if(j % 2 == 0) {
                System.out.println(b += 1);
                continue;
            }
            f += 2.1f;
            d -= 1.2d;
        }

        do{
            f -= 4.1f;
        } while(f > 0);

        int p = test1(2, 4);
        int q = test1(6, 8);
        assert p < q;

        try {
            test2(c);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new NullPointerException();
        } finally {
            System.out.println("Finally!!");
        }

        Dog myDog = new Dog();
        myDog.animalSound();
        myDog.sleep();

        List<Rectangle> list = new ArrayList<>();
        Rectangle reg1 = new Rectangle(1.14,3.59, 2.6);
        Rectangle reg2 = new Rectangle(2.14,2.59, 2.6);
        Rectangle reg3 = new Rectangle(3.14,1.59, 2.6);
        list.add(reg1);
        list.add(reg2);
        list.add(reg3);
        reg1.incrementLength();
        reg1.incrementWidth();
        double area1 = reg1.area();
        double perimeter = reg1.perimeter();
        double hashV = reg1.hashValue();
        double ratioR = reg1.ratio();
        System.out.println("Area: " + area1 + " and ratio:  " + ratioR);
        System.out.println("Perimeter: " + perimeter + " and hashValue: " + hashV);
        assert list.get(new Random().nextInt(2)) != null && list.get(new Random().nextInt(2)) instanceof Shape;
    }

    private static int test1(int a, int b) throws NullPointerException {
        return a + b;
    }

    protected static void test2(char c) {
        boolean b = (c - 'a' > 0);
        assert b;
    }

    abstract static class Shape implements Serializable {
        static volatile double width, length, radius; // changes immediately reflect in other thread
        transient double depth; // this variable won't be serialized, it will change auto

        Shape() {
            width = 0.0;
            length = 0.0;
            radius = 0.0;
        }
        abstract double area();
        abstract double perimeter();

        /**
         * The native keyword transforms our method into a sort of abstract method.
         * Instead of being implemented by another Java class,
         * it will be implemented in a separated native shared library.
         * Declare a native method hashValue() that receives no arguments.
         * @return double
         */
        private native double hashValue();

        public abstract boolean equals(Rectangle reg);
    }

    static class Rectangle extends Shape {
        private double width, length;

        Rectangle(double width, double length, double optional) {
            super();
            this.width = width;
            this.length = length;
        }

        protected double area(){
            return width * length;
        }

        protected double perimeter() {
            return 2 * (width + length);
        }

        /**
         * First, it is not possible for two invocations of synchronized methods on the same object to interleave.
         * When one thread is executing a synchronized method for an object, all other threads that invoke synchronized
         * methods for the same object block (suspend execution) until the first thread is done with the object.
         *
         * Second, when a synchronized method exits, it automatically establishes a happens-before relationship with
         * any subsequent invocation of a synchronized method for the same object. This guarantees that change to the
         * state of the object are visible to all threads.
         */
        protected synchronized void incrementWidth() {
            width++;
        }

        protected synchronized void incrementLength() {
            length++;
        }

        public synchronized double hashValue() {
            return Math.sqrt(width * length);
        }

        /**
         * When a class or an interface is declared with strictfp modifier,
         * then all methods declared in the class/interface, and all nested types declared in the class,
         * are implicitly strictfp.
         *
         * Strictfp cannot be used with abstract methods.
         * However, it can be used with abstract classes/interfaces.
         * Since methods of an interface are implicitly abstract, strictfp cannot be used with any method inside an interface.
         * @return double
         */
        public strictfp double ratio() {
            double num1 = 10e+10;
            double num2 = 6e+08;
            return (width + num1) / (length + num2);
        }

        @Override
        public boolean equals(Rectangle reg) {
            return reg != null && this.ratio() == reg.ratio();
        }
    }

    // The Interface example
    interface Animal {
        void animalSound();
        void sleep();
    }

    static class Dog implements Animal {
        public void animalSound() {
            System.out.println("Woo...woo...");
        }
        public void sleep() {
            System.out.println("Zzz...");
        }
    }
}
