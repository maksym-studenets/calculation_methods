package com.company.semestr1.lab3;

/**
 * Created by rosty on 28.10.2018.
 */
public class IterationMethodSystem {
    private final static double eps = 0.01;

    public static void main(String[] args) {
        double x = 0.3, x0;
        double y = -1.5, y0;
        int i = 0;

        do {
            x0 = x;
            y0 = y;
            x = Math.sin(y0 + 1) + 0.8;
            y =  Math.sin(x0) * (x0 - 1) - 1.3;
            i++;
        }
        while (Math.abs(x - x0) > eps && Math.abs(y - y0) > eps);

        System.out.println(x + " " + y);
        System.out.println(i);
    }
}
