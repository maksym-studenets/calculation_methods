package com.company.semestr2.lab1;

import java.util.Scanner;

public class Lab1 {
    private static final double a = 1;
    private static final double b = 2;
    private static final int acc = 2;

    public static void main(String[] args) {
        double eps = new Scanner(System.in).nextDouble();
        int n = acc;
        double s1, s2;
        s1 = calcS(n);
        do {
            s2 = s1;
            n *= 2;
            s1 = calcS(n);
        } while (Math.abs(s1 - s2) >= eps);
        System.out.println("Integral = " + s1);
    }

    private static double f(double x) {
        return Math.exp(- (x + 1 / x));
    }

    private static double calculateStep(double n) {
        return (b - a) / n;
    }

    private static double calcC(double h) {
        return a + h;
    }

    private static double calcS(double n) {
        double result = 0;
        double h = calculateStep(n);
        double c = calcC(h);
        for (int i = 0; i < n; i++) {
            double x = c + i * h;
            result += f(x) * h;
        }
        return result;
    }
}
