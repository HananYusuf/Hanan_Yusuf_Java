package com.challenge1;

public class Calculator {

    public int add(int a, int b){
        System.out.println(a + " + " + b + " = " + (a + b));
        return a + b;
    }
    public double add(double a, double b){
        System.out.println(a + " + " + b + " = " + (a + b));
        return a + b;
    }

    public int subtract(int a, int b){
        System.out.println(a + "-" + b + "=" + (a - b));
        return a - b;
    }
    public double subtract(double a, double b){
        System.out.println(a + "-" + b + "=" + (a - b));
        return a - b;
    }

    public int multiply(int a, int b){
        System.out.println(a + "*" + b + "=" + (a * b));
        return a * b;
    }
    public double multiply(double a, double b){
        System.out.println(a + "*" + b + "=" + (a * b));
        return a * b;
    }

    public int divide(int a, int b){
        System.out.println(a + "/" + b + "=" + (a / b));
        return a/b;
    }
    public double divide(double a, double b){
        System.out.println(a + "/" + b + "=" + (a / b));
        return a/b;
    }
}
