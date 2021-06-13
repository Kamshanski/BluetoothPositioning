package com.kamshanski.blepositioning.model.methods.filters;

import com.kamshanski.utils.collections.slidingwindowarrays.SlidingWindowDoubleArray;

public class Hampel implements NiceFilter {
    SlidingWindowDoubleArray windowDoubleArray;
    final double n;
    final double k;    // for Gaussian
    final int windowSize;

    public Hampel(double sigmasNumber, double statCoeff, int windowSize) {
        this.n = sigmasNumber;
        this.k = statCoeff;
        this.windowSize = windowSize;
        windowDoubleArray = new SlidingWindowDoubleArray(windowSize);
    }

    public Hampel(double sigmasNumber, double statCoeff) {
        this(sigmasNumber, statCoeff, 10);
    }
    public Hampel(double sigmasNumber) {
        this(sigmasNumber, 1.4826);
    }

    public Hampel() {
        this(3);
    }

    @Override
    public double[] filter(double[] in) {
        // This array is also used to calculate MAD
        double[] temp = new double[windowDoubleArray.getSize()];
        double[] ans = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            windowDoubleArray.put(in[i]);
            double[] window = windowDoubleArray.toArray();

            double median = Median.median(window);
            for (int j = window.length-1; j > -1; j--) {
                temp[j] = Math.abs(median - window[j]);
            }
            double MAD = Median.median(temp);
            double S = n * k * MAD;

            double tempNum = window[window.length - 1];
            ans[i] = Math.abs(tempNum - median) > S ? median : tempNum;
        }
        return ans;

//        double median = Median.median(in);
//        for (int i = in.length-1; i > -1; i--) {
//            ans[i] = Math.abs(median - in[i]);
//        }
//        double MAD = Median.median(ans);
//        double S = n * k * MAD;
//
//        double in_i;
//        for (int i = in.length-1; i > -1; i--) {
//            in_i = in[i];
//            ans[i] = Math.abs(in_i - median) > S ? median : in_i;
//        }

    }

    @Override
    public NiceFilter makeTheSame() {
        return new Hampel(n, k);
    }
}
