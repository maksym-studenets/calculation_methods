package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    int m = Integer.parseInt(reader.readLine());
	    int sign = 1;
	    double sum = 0;
	    for (int n = 1; n <= m; n++) {
	        sum += sign / Math.pow(2 * n - 1, 2);
	        sign = -sign;
        }
        System.out.println("from 1 to m");
        System.out.println(sum);
        if ((m - 1) % 2 == 0) {
            sign = 1;
        }
        else {
            sign = -1;
        }
        sum = 0;
        for (int n = m; n >= 1; n--) {
            sum += sign / Math.pow(2 * n - 1, 2);
            sign = -sign;
        }
        System.out.println("from m to 1");
        System.out.println(sum);
    }
}
