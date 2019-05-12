package com.company.semestr2.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab2 {
    private static final double a = 1;
    private static final double b = 1.8;
    private static final double t0 = Math.PI / 2;
    private static final double u0 = 1;
    private static final int acc = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double eps = scanner.nextDouble();
        int N = acc;
        List<Double> x1 = calcXList(N);
        List<Double> y1 = calcYList(x1, N);
        List<Double> x0, y0;
        do {
            x0 = x1;
            y0 = y1;
            N *= 2;
            x1 = calcXList(N);
            y1 = calcYList(x1, N);
        } while (checkAcuracy(y0, y1, eps));
        System.out.println("xi   yi   y(x)");
        for (int i = 0; i < x0.size(); i++) {
            System.out.println(x0.get(i) + "  " + y0.get(i) + "  " + realF(x0.get(i)));
        }
    }

    private static boolean checkAcuracy(List<Double> y0, List<Double> y1, double eps) {
        for (int i = 0; i < y0.size(); i++) {
            if (Math.abs(y0.get(i) - y1.get(2 * i)) < eps) return false;
        }
        return true;
    }

    private static List<Double> calcXList(double n) {
        List<Double> result = new ArrayList<>();
        double h = calcH(n);
        result.add(t0);
        for (int i = 1; i <= n; i++) {
            result.add(t0 + i * h);
        }
        return result;

    }

    private static List<Double> calcYList(List<Double> x, double n) {
        List<Double> result = new ArrayList<>();
        double h = calcH(n);
        result.add(u0);
        for (int i = 1; i <= n; i++) {
            double x0 = x.get(result.size() - 1);
            double y0 = result.get(result.size() - 1);
            double k1 = F(x0, y0);
            double k2 = F(x0 + h / 2, y0 + h * k1 / 2);
            double k3 = F(x0 + h / 2, y0 + h * k2 / 2);
            double k4 = F(x0 + h, y0 + h * k3);
            result.add(y0 + (h / 6.) * (k1 + 2 * k2 + 2 * k3 + k4));
        }
        return result;
    }

    private static double calcH(double n) {
        return (b - a) / n;
    }

    private static double F(double x, double y) {
        return y * Math.cos(x) / Math.sin(x);
    }

    private static double realF(double x) {
        return Math.sin(x);
    }
}
