package com.kamshanski.blepositioning.model.methods.filters;

import com.kamshanski.utils.collections.slidingwindowarrays.SlidingWindowDoubleArray;

import java.util.Arrays;

public class Median implements NiceFilter {
    SlidingWindowDoubleArray windowDoubleArray;
    final int n;

    public Median(int windowSize) {
        this.n = windowSize;
        windowDoubleArray = new SlidingWindowDoubleArray(windowSize);
    }

    @Override
    public double[] filter(double[] in) {
        double[] ans = new double[in.length];
        for (int i = in.length-1; i > -1; i--) {
            windowDoubleArray.put(in[i]);
            double[] window = windowDoubleArray.toArray();
            ans[i] = median(window);
        }
        return ans;
    }

    @Override
    public NiceFilter makeTheSame() {
        return new Median(n);
    }


    public static double median(int[] array) {
        Arrays.sort(array);
        int L_2 = array.length / 2;
        if ((array.length & 1) == 0) {  // even len
            return (array[L_2] + array[L_2 - 1]) / 2.0;
        } else {                        // odd len
            return array[L_2];
        }
    }
    public static double median(double[] array) {
        Arrays.sort(array);
        int L_2 = array.length / 2;
        if ((array.length & 1) == 0) {  // even len
            return (array[L_2] + array[L_2 - 1]) / 2.0;
        } else {                        // odd len
            return array[L_2];
        }
    }
}
