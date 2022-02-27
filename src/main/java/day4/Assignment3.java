package day4;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Assignment3 {
    public static void main(String [] args) {
        // Question 15
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,7,9,10,11));
        Map<String, String> map = new HashMap<>();
        for(int i=1; i< 21; i += 2) {
            map.put(i+"", i+"");
        }
        List<Integer> res = testListandMap(list, map);
        System.out.println(res.toString());

        // Question 16
        Random random = new Random();
        Rectangle reg1 = new Rectangle(random.nextDouble(), random.nextDouble());
        Rectangle reg2 = new Rectangle(random.nextDouble(), random.nextDouble());
        System.out.println("Comparing area 2 rectangles: " + reg1.compareTo(reg2));

        Circle circle1 = new Circle(random.nextDouble());
        Circle circle2 = new Circle(random.nextDouble());
        System.out.println("Comparing 2 circles: " + circle1.compareTo(circle2));

        Square square1 = new Square(random.nextDouble());
        Square square2 = new Square(random.nextDouble());
        System.out.println("Comparing 2 squares: " + square1.compareTo(square2));

        System.out.println("Comparing rectangles1 with circle1 : " + circle1.compareTo(reg1));
        System.out.println("Comparing rectangles2 with circle2 : " + reg2.compareTo(circle2));
        System.out.println("Comparing square1 with circle1 : " + square1.compareTo(circle1));
        System.out.println("Comparing square2 with rectangle2 : " + square2.compareTo(reg2));
    }

    /**
     * Question 15.
     * @param list ArrayList<Integer>
     * @param map HashMap<String, String>
     * @return List<Integer>
     */
    private static List<Integer> testListandMap(List<Integer> list, Map<String, String> map) {
        List<String> mapValues = new ArrayList<>(map.values());
        List<Integer> res = list.stream()
                .filter(l -> !mapValues.contains(l+""))
                .collect(Collectors.toList());
        return res;
    }

    // Question 16.
    abstract static class Shape implements Serializable {
        Shape() {}
        abstract double area();
        public abstract int compareTo(Object o);
    }

    static class Rectangle extends Shape{
        double width;
        double length;
        Rectangle(double width, double length) {
            this.width = width;
            this.length = length;
        }

        public double area() {
            return width * length;
        }

        @Override
        public int compareTo(Object reg) {
            double t = this.area() - ((Shape)reg).area();
            if(t > 0.0) {
                return 1;
            } else if(t == 0.00000000) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    static class Circle extends Shape {
        double radius;

        Circle(double radius){
            this.radius = radius;
        }

        public double area() {
            return Math.PI * Math.pow(radius, 2.0);
        }

        @Override
        public int compareTo(Object circle) {
            double t = this.area() - ((Shape)circle).area();
            if(t > 0.0) {
                return 1;
            } else if(t == 0.00000000) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    static class Square extends Shape {
        double length;

        Square(double length){
            this.length = length;
        }

        public double area() {
            return Math.pow(length, 2.0);
        }

        @Override
        public int compareTo(Object square) {
            double t = this.area() - ((Shape)square).area();
            if(t > 0.0) {
                return 1;
            } else if(t == 0.00000000) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
