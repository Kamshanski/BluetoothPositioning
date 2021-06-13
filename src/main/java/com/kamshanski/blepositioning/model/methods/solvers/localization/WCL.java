package com.kamshanski.blepositioning.model.methods.solvers.localization;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.model.methods.solvers.Estimator;
import com.kamshanski.utils.structures.wrappers.Mutable;

import java.util.HashMap;

public class WCL implements Estimator {
    // Коэффициент затухания
    final double a;

    public WCL(double a) {
        this.a = -a;    // a is always negative
    }

    @Override
    public Dimensions estimate(HashMap<Beacon, Double> dataset) {
        // Числитель для вычисления координаты Х
        Mutable.Double numeratorX = new Mutable.Double(0);
        // Числитель для вычисления координаты Y
        Mutable.Double numeratorY = new Mutable.Double(0);
        // Знаменатель
        Mutable.Double denominator = new Mutable.Double(0);

        dataset.forEach((beacon, rho) -> {
            // Вычисление весов
            double rho_a = Math.pow(rho, a);
            // Суммирование взвешенных координат и знаменателя
            numeratorX.add(rho_a * beacon.x);
            numeratorY.add(rho_a * beacon.y);
            denominator.add(rho_a);
        });

        // Вычисление координат искомой станции (X, Y, Z=0)
        return new Dimensions(numeratorX.v/denominator.v, numeratorY.v/denominator.v, 0);
    }
}
