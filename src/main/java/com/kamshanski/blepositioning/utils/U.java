package com.kamshanski.blepositioning.utils;

import java.util.List;
import java.util.Random;

public class U {
    private static Random r;
    static {
        r = new Random();
    }
    public static void nout(String s) {
        System.out.println(s);
    }
    public static void out(String s) {
        System.out.print(s);
    }

    public static int randInt(int max, int min) {
        return r.nextInt(max - min) + min;
    }

    public static int[] invertArray(int[] a) {
        if (a != null && a.length > 0) {
            int size = a.length;
            int halfSize = size / 2;
            int temp;
            for (int i = 0; i < halfSize; i++) {
                temp = a[i];
                a[i] = a[size - i - 1];
                a[size - i - 1] = temp;
            }
        }
        return a;
    }

    public static double[] invertArray(double[] a) {
        if (a != null && a.length > 0) {
            int size = a.length;
            int halfSize = size / 2;
            double temp;
            for (int i = 0; i < halfSize; i++) {
                temp = a[i];
                a[i] = a[size - i - 1];
                a[size - i - 1] = temp;
            }
        }
        return a;
    }

    public static <T> T[] invertArray(T[] a) {
        if (a != null && a.length > 0) {
            int size = a.length;
            int halfSize = size / 2;
            T temp;
            for (int i = 0; i < halfSize; i++) {
                temp = a[i];
                a[i] = a[size - i - 1];
                a[size - i - 1] = temp;
            }
        }
        return a;
    }

    public static <V, L extends List<V>> L invertArray(L a) {
        if (a != null && a.size() > 0) {
            int size = a.size();
            int halfSize = size / 2;
            V temp;
            for (int i = 0; i < halfSize; i++) {
                temp = a.get(i);
                a.set(i, a.get(size - i - 1));
                a.set(size - i - 1, temp);
            }
        }
        return a;
    }
}
