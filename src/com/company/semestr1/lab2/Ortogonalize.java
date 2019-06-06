package com.company.semestr1.lab2;

public class Ortogonalize {

    public static void main(String[] args) {
        double[][] a = {
                {0.2293, -0.1507, -0.2609, 0.3152},
                {-0.1507, 0.181, 0.1595, -0.3722},
                {-0.2609, 0.1595, 0.481, -0.4852},
                {0.3152, -0.3722, -0.4852, 1.2625}
        };
        double[] b = {0.4708, -0.3697, -1.193, 2.0636};
        double[][] c = new double[a.length + 1][a.length+ 1];

        for (int i = 0; i < a.length; i++) {
            if (a[i].length >= 0) {
                System.arraycopy(a[i], 0, c[i], 0, a[i].length);
            }
            c[i][a.length] = -b[i];
        }

        for (int i = 0; i < a.length; i++) {
            c[a.length][i] = 0;
        }
        c[a.length][a.length] = 1;

        double[][] r = new double[a.length + 1][a.length + 1];
        double[][] s = new double[a.length + 1][a.length + 1];
        r[0] = c[0].clone();
        s[0] = divideByScalar(r[0], norm(r[0]));

        for (int i = 1; i < r.length; i++) {
            double[] sum = new double[a.length + 1];
            for (int j = 0; j < i; j++) {
                sum = add(sum, multiplyByScalar(s[j], scalarProduct(c[i], s[j])));
            }
            r[i] = subtract(c[i], sum);
            s[i] = divideByScalar(r[i], norm(r[i]));
        }

        double[] x = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            x[i] = r[a.length][i] / r[a.length][a.length];
        }

        for (double x1 : x) {
            System.out.println(x1);
        }
    }

    private static double norm(double[] r) {
        double result = 0;
        for (double v : r) {
            result += v * v;
        }
        return Math.sqrt(result);
    }

    private static double[] divideByScalar(double[] r, double number) {
        double[] result = new double[r.length];
        for (int i = 0; i < r.length; i++) {
            result[i] = r[i] / number;
        }
        return result;
    }

    private static double[] multiplyByScalar(double[] r, double number) {
        double[] result = new double[r.length];
        for (int i = 0; i < r.length; i++) {
            result[i] = r[i] * number;
        }
        return result;
    }

    private static double scalarProduct(double[] a, double[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    private static double[] add(double[] a, double[] b) {
        System.out.println(a.length + " " + b.length);
        System.out.println("_______________");

        if (a.length != b.length) {
            throw new IllegalArgumentException("Vectors a and b must be of equal size!");
        }

        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

    private static double[] subtract(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Vectors a and b must be of equal size!");
        }

        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }
}
