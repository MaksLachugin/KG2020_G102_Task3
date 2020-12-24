package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions;

import java.util.Map;

public class InvertExponent extends IFunction {
    //A* 1/(1+e^(-x)) + C
    private double A, C;

    InvertExponent(Map<String, Double> params) {
        super(params);
        A = this.params.getOrDefault("A", 1.);
        C = this.params.getOrDefault("A", 0.);
    }

    @Override
    public double compute(double x) {
        return A * 1 / (1 + Math.pow(Math.E, -x)) + C;
    }
}
