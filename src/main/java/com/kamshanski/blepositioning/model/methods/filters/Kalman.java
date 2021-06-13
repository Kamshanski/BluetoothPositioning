package com.kamshanski.blepositioning.model.methods.filters;

import com.kamshanski.utils.structures.wrappers.Mutable;

public class Kalman implements NiceFilter {
    // As in my diploma work
    final double Q;
    final double R;
    double K = 1.0, P;
    Mutable.Double Z = null;

    public Kalman(double q, double r, double p) {
        Q = q;
        R = r;
        P = p;
    }
    public Kalman(double q, double r) {
        Q = q;
        R = r;
        P = 0;
    }

    public double calc(double rssi) {
        if (Z == null) {
            Z = new Mutable.Double(rssi);
        } else {
            P = (1-K)*P + Q;
            K = P/(P+R);
            Z.v = K*rssi + (1-K)*Z.v;
        }
        return Z.v;
    }

    @Override
    public double[] filter(double[] in) {
        double[] ans = new double[in.length];
        for (int i = in.length-1; i > -1; i--) {
            ans[i] = calc(in[i]);
        }
        return ans;
    }

    @Override
    public NiceFilter makeTheSame() {
        return new Kalman(Q, R, P);
    }
}
