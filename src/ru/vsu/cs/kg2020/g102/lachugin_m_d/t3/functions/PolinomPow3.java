package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions;

import java.util.Map;

public class PolinomPow3 extends IFunction {
    //y = A*x^3 + B*x^2 + C*x + D
    private double A, B, C, D;

    public PolinomPow3(Map<String, Double> params) {
        super(params);
        A = this.params.getOrDefault("A", 1.);
        B = this.params.getOrDefault("B", 1.);
        C = this.params.getOrDefault("C", 1.);
        D = this.params.getOrDefault("D", 0.);

    }

    @Override
    public double compute(double x) {
        return A * Math.pow(x, 3) + B * Math.pow(x, 2) + C * x + D;
    }
}
