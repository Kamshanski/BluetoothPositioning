package com.kamshanski.blepositioning.model.methods.solvers.particleswarm;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Locale;


public class LeastSquaresFunction {
    public static final String X = "x";
    public static final String Y = "y";

    private final Expression exception;

    private LeastSquaresFunction(Expression expression) {
        this.exception = expression;
    }

    public double of(double x, double y) {
        exception.setVariable(X, x);
        exception.setVariable(Y, y);
        return exception.evaluate();

    }

    public static class Builder {

        public static final String CANVAS = "(sqrt((x-(%f))^2+(y-(%f))^2)-(%f))^2";
        public static final String PLUS = "+";

        public StringBuilder parts = new StringBuilder();

        public Builder addPart(double x, double y, double distance) {
            if (parts.length() != 0) {
                parts.append(PLUS);
            }
            parts.append(String.format(Locale.US, CANVAS, x, y, distance));
            return this;
        }

        public LeastSquaresFunction build() {
            return new LeastSquaresFunction(
                    new ExpressionBuilder(parts.toString())
                            .variables(X, Y)
                            .build());
        }

        public static LeastSquaresFunction getZero() {
            return new LeastSquaresFunction(
                    new ExpressionBuilder("(x+y)*0")
                            .variables(X, Y)
                            .build());
        }
    }
}
