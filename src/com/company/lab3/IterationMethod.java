package com.company.lab3;

/**
 * Created by rosty on 17.10.2018.
 */
public class IterationMethod {
    private final static double eps = 0.001;
    public static void main(String[] args) {
        double x = 3.6, x0;
        do {
            x0 = x;
            x = (5 - 1.7 * Math.log10(x0)) / 1.3;
        }
        while (Math.abs(x - x0) > eps);
        System.out.println(x);
    }
}
