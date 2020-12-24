package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions;

import java.util.Map;

public class SinCos extends IFunction {
    //y = A1*sin(W1*x + F1)*(A2*cos(W2*x + F2)+C2) + C1
    private double A1, W1, F1, A2, W2, F2, C2, C1;

    public SinCos(Map<String, Double> params) {
        super(params);
        A1 = this.params.getOrDefault("A1", 1.);
        A2 = this.params.getOrDefault("A2", 1.);
        W1 = this.params.getOrDefault("W1", 1.);
        W2 = this.params.getOrDefault("W2", 1.);
        F1 = this.params.getOrDefault("F1", 0.);
        F2 = this.params.getOrDefault("F2", 0.);
        C1 = this.params.getOrDefault("C1", 0.);
        C2 = this.params.getOrDefault("C2", 0.);

    }

    @Override
    public double compute(double x) {
        return A1 * Math.sin(W1 * x + F1) * (A2 * Math.cos(W2 * x + F2) + C2) + C1;
    }
}
