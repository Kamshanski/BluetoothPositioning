package com.kamshanski.blepositioning.model.methods.filters;

import com.kamshanski.utils.collections.slidingwindowarrays.SlidingWindowDoubleArray;
import com.kamshanski.utils.structures.wrappers.Mutable;

public class KaufmansAdaptiveMovingAverage implements NiceFilter {
    final int size, f, s, initValue;
    final double F, S, F_S;

    double ER, alpha, direction, volatility, Z=0.0;
    SlidingWindowDoubleArray windows;
    final Mutable.Double v = new Mutable.Double(0);

    public KaufmansAdaptiveMovingAverage(int initValue) {
        this(10, 2, 30, initValue);
    }

    public KaufmansAdaptiveMovingAverage() {
        this(10, 2, 30, -60);
    }

    public KaufmansAdaptiveMovingAverage(int n, int f, int s, int initValue) {
        this.size = n;
        this.f = f;
        this.s = s;
        this.initValue = initValue;
        F = 2/((double)f+1);
        S = 2/((double)s+1);
        F_S = F-S;
        windows = new SlidingWindowDoubleArray(size, initValue);
    }


    @Override
    public double[] filter(double[] in) {
        double[] ans = new double[in.length];

        for (int i = in.length-1; i > -1; i--) {
            windows.put(in[i]);
            direction = Math.abs(windows.get(0) - windows.get(-1));
            volatility = getVolatility();
            if (Math.abs(volatility) < 0.0001) {
                Z = in[i];
            } else {
                ER = direction / volatility;
                alpha = Math.pow(ER*F_S + S, 2);
                Z = alpha * in[i] + (1- alpha)*Z;
            }
            ans[i] = Z;
        }

        return ans;
    }

    double getVolatility() {
        v.v = 0.0d;
        for (int i = 0; i < windows.getSize() - 1; i++) {
            double p = windows.get(i);
            double n = windows.get(i+1);
            v.v += Math.abs(p - n);
        }

//        int rssiPrev = windows.get(0);
//        int rssiCur = 0;
//        for (int i = 1; i < size ; i++) {
//            rssiCur = windows.get(i);
//            v += Math.abs(rssiCur - rssiPrev);
//            rssiPrev = rssiCur;
//        }
        return v.v;
    }

    @Override
    public NiceFilter makeTheSame() {
        return new KaufmansAdaptiveMovingAverage(size, f, s, initValue);
    }



    public static class LinkedDoubleArray {
        private DoubleNode first = null, last = null;
        final int maxSize;
        int size = 0;
        double sum;

        public LinkedDoubleArray(int size, double initVal) {
            this.maxSize = size;
//            for (int i = 0; i < size-1; i++) {
//                addFirstNew(initVal);
//            }
        }


        private void addFirstNew(double num) {
            DoubleNode node = new DoubleNode(num, first, null);
            if (last == null) {
                last = node;
            } else {
                first.prev = node;
            }
            first = node;
            size++;
            sum += num;
        }

        public void addFirst(double num) {
            if (size < maxSize) {
                addFirstNew(num);
            } else {
                sum += num - last.val;
                last.val = num;

                DoubleNode temp = last;

                last = temp.prev;

                temp.prev = null;
                temp.next = first;
                last.next = null;
                first.prev = temp;

                first = temp;

            }
        }

        public void forEach(DoubleConsumer dc) {
            forEach((DoubleNodeConsumer) num -> dc.apply(num.val));
        }

        private void forEach(DoubleNodeConsumer dc) {
            if (dc != null && first != null) {
                DoubleNode i = first;
                do {
                    dc.apply(i);
                    i = i.next;
                } while (i != null);
            }
        }

        public double first() {
            return first != null ? first.val : 0;
        }

        public double last() {
            return last != null ? last.val : 0;
        }

        public double getSum() {
            return sum;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(
                    "size: " + size + " "
                    + "maxSize" + maxSize + " "
                    + "sum" + sum + " "
                    + "list: ");
            forEach(((DoubleConsumer) num -> builder.append(num).append(", ")));
            builder.append("\n");
            return builder.toString();
        }

        public static class DoubleNode {
            DoubleNode next, prev;
            double val;

            public DoubleNode(double val, DoubleNode next, DoubleNode prev) {
                this.next = next;
                this.prev = prev;
                this.val = val;
            }

            @Override
            public String toString() {
                return String.valueOf(val);
            }
        }

        public static interface DoubleConsumer {
            void apply(double num);
        }
        public static interface DoubleNodeConsumer {
            void apply(DoubleNode num);
        }
    }
}
