package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions;

import java.util.Map;

public class Sin extends IFunction {
    private double A, W, F, C;

    Sin(Map<String, Double> params) {
        super(params);
        A = this.params.getOrDefault("A", 1.);
        W = this.params.getOrDefault("W", 1.);
        F = this.params.getOrDefault("F", 0.);
        C = this.params.getOrDefault("C", 0.);

    }
    //y = A*sin(W*x + F) + C
    @Override
    public double compute(double x) {
        return A * Math.sin(W * x + F) + C;
    }
}
