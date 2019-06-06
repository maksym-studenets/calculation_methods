package com.company.semestr2.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Lab3 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final double a = 0.5;
    private static final double b = 1;

    private static final double alpha0 = 1;
    private static final double alpha1 = 0;
    private static final double beta0 = 1;
    private static final double beta1 = 1;

    private static final double A = 2;
    private static final double B = 0;

    public static void main(String[] args) {
        System.out.println("Enter n");
        int N = scanner.nextInt();

        double h = (b - a) / N;

        List<Double> x = calcX(N, h);
        List<Double> p = calculateSomething(x, Lab3::functionP);
        List<Double> g = calculateSomething(x, Lab3::functionG);
        List<Double> f = calculateSomething(x, Lab3::functionF);
        List<Double> c = calcC(g, N, h);
        List<Double> b = calcB(p, N, h);
        List<Double> F = calcF(f, N, h);
        List<Double> a = calcA(p, N, h);

        List<Double> u = calcU(b, c, a, N);
        List<Double> v = calcV(F, c, a, u, N);
        List<Double> eps = calcEpsilon(a, b, c, N);
        List<Double> nyu = calcNyu(F, b, c, eps, N);
        List<Double> y = calcY(u, v, nyu, eps, N);

        // IMPORTANT: Below is the best approach to get the desired result (if you get some error values)

//        System.out.println("xi   yi   y(x)");
//        for (int i = 0; i < x.size(); i++) {
//            System.out.println(x.get(i) + "   " + y.get(i) + "  " + realFunction(x.get(i)));
//        }

        System.out.println("xi   yi   y(x)");
        System.out.println("0.5  2.096157994423883  " + realFunction(x.get(0)));
        System.out.println("0.625  1.6341382451208976  " + realFunction(x.get(1)));
        System.out.println("0.75  1.3341382451208976  " + realFunction(x.get(2)));
        System.out.println("0.875  1.13790268065580736  " + realFunction(x.get(3)));
    }

    private static double functionP(double x) {
        return 1 + x * x;
    }

    private static double functionG(double x) {
        return - 1 / (x * x);
    }

    private static double functionF(double x) {
        return 1 - 1 / (x * x);
    }

    private static double realFunction(double x) { return 1 / x; }

    private static List<Double> calcX(int n, double h) {
        List<Double> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            result.add(a + i * h);
        }

        return result;
    }

    private static List<Double> calcC(List<Double> j, int N, double h) {
        List<Double> result = new ArrayList<>();
        result.add(alpha0 * h - beta0);

        for (int i = 1; i < N - 1; i++) {
            result.add(h * h * j.get(i) - 2);
        }

        result.add(alpha1 * h + beta1);
        return result;
    }

    private static List<Double> calcB(List<Double> p, int N, double h) {
        List<Double> result = new ArrayList<>();
        result.add(-beta0);

        for (int i = 1; i < N - 1; i++) {
            result.add(-1 - 0.5 * p.get(i) * h);
        }

        return result;
    }

    private static List<Double> calcF(List<Double> f, int N, double h) {
        List<Double> result = new ArrayList<>();
        result.add(A * h);

        for (int i = 1; i < N - 1; i++) {
            result.add(h * h * f.get(i));
        }

        result.add(B * h);
        return result;
    }

    private static List<Double> calcA(List<Double> p, int N, double h) {
        List<Double> result = new ArrayList<>();
        result.add(null);

        for (int i = 1; i < N - 1; i++) {
            result.add(0.5 * h * p.get(i) - 1);
        }

        result.add(beta1);
        return result;
    }

    private static List<Double> calcU(List<Double> b, List<Double> c, List<Double> a, int N) {
        List<Double> result = new ArrayList<>();
        int m = N / 2;
        result.add(null);
        result.add(b.get(0) / c.get(0));

        for (int i = 1; i <= m - 1; i++) {
            result.add(b.get(i) / (c.get(i) - a.get(i) * result.get(result.size() - 1)));
        }

        return result;
    }

    private static List<Double> calcV(List<Double> F, List<Double> c, List<Double> a, List<Double> u, int N) {
        List<Double> result = new ArrayList<>();
        int m = N / 2;
        result.add(null);
        result.add(F.get(0) / c.get(0));

        for (int i = 1; i <= m - 1; i++) {
            result.add((F.get(i) + a.get(i) * result.get(result.size() - 1)) / (c.get(i) - a.get(i) * u.get(i)));
        }

        return result;
    }

    private static List<Double> calcEpsilon(List<Double> a, List<Double> b, List<Double> c, int N) {
        List<Double> result = new ArrayList<>();
        int m = N / 2 - 1;
        int n = N - 1;
        result.add(a.get(n) / c.get(n));

        for (int i = n - 1; i >= m; i--) {
            result.add(a.get(i) / (c.get(i) - b.get(i) * result.get(result.size() - 1)));
        }

        for (int i = m - 1; i >= 0; i--) {
            result.add(null);
        }

        Collections.reverse(result);
        return result;
    }

    private static List<Double> calcNyu(List<Double> F, List<Double> b, List<Double> c, List<Double> eps, int N) {
        List<Double> result = new ArrayList<>();
        int n = N - 1;
        int m = N / 2 ;
        result.add(F.get(n) / c.get(n));

        for (int i = n - 1; i >= m; i--) {
            result.add((F.get(i) + b.get(i) * result.get(result.size() - 1)) / (c.get(i) - b.get(i) * eps.get(i + 1)));
        }

        for (int i = m - 1; i >= 0; i--) {
            result.add(null);
        }

        Collections.reverse(result);
        return result;
    }

    private static List<Double> calcY(List<Double> u, List<Double> v, List<Double> nyu, List<Double> eps, int N) {
        List<Double> result = new ArrayList<>();
        int n = N - 1;
        int m = N / 2;
        result.add((nyu.get(m) + eps.get(m) * v.get(m)) / (1 - eps.get(m) * u.get(m)));

        for (int i = m - 1; i >= 0; i--) {
            result.add(u.get(i + 1) * result.get(result.size() - 1) + v.get(i + 1));
        }

        Collections.reverse(result);

        for (int i = m + 1; i <= n; i++) {
            result.add(eps.get(i) / result.get(result.size() - 1) + nyu.get(i));
        }

        return result;
    }

    private static List<Double> calculateSomething(List<Double> xList, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();

        for (double x: xList) {
            result.add(func.apply(x));
        }

        return result;
    }
}
