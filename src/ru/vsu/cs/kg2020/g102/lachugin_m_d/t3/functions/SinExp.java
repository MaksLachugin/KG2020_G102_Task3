package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions;

import java.awt.event.MouseAdapter;
import java.util.Map;

public class SinExp extends IFunction {
    //y = A*sin(W*x + F)*(e^(-x/T))+C
    private double A, W, F, T, C;

    public SinExp(Map<String, Double> params) {
        super(params);
        A = this.params.getOrDefault("A", 1.);
        W = this.params.getOrDefault("W", 1.);
        F = this.params.getOrDefault("F", 0.);
        T = this.params.getOrDefault("T", 1.);
        C = this.params.getOrDefault("C", 0.);
    }

    @Override
    public double compute(double x) {
        return A * Math.sin(W * x + F) * (Math.pow(Math.E, -x / T)) + C;
    }
}
