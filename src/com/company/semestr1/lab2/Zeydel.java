package com.company.semestr1.lab2;

public class Zeydel {
    private static final double eps = 0.01;
    public static void main(String[] args) {
        double[][] a = {
                {3.82, 1.02, 0.75, 0.81},
                {1.05, 4.53, 0.98, 1.53},
                {0.73, 0.85, 4.71, 0.81},
                {0.88, 0.81, 1.28, 3.50}
        };
        double[] b = {15.65, 22.70, 23.48, 16.11};
        double[] x = new double[a.length];
        double[] prev = new double[a.length];
        do {
            for (int i = 0; i < x.length; i++) {
                prev[i] = x[i];
            }
            for (int i = 0; i < x.length; i++) {
                double var = 0;
                for (int j = 0; j < i; j++) {
                    var += (a[i][j] * x[j]);
                }
                for (int j = i + 1; j < x.length; j++) {
                    var += a[i][j] * prev[j];
                }
                x[i] = (b[i] - var) / a[i][i];
            }
        } while (!converge(x, prev));
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
    }
    private static boolean converge(double[] x, double[] prev) {
        double norm = 0;
        for (int i = 0; i < x.length; i++) {
            norm += (x[i] - prev[i]) * (x[i] - prev[i]);
        }
        return Math.sqrt(norm) < eps;
    }
}
