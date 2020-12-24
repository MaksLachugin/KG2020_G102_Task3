package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions;

import java.util.Map;

public class Hyperbole extends IFunction {
    //y = A / (B*x+D) + C
    private double A, B, C, D;


    Hyperbole(Map<String, Double> params) {
        super(params);
        A = this.params.getOrDefault("A", 1.);
        B = this.params.getOrDefault("B", 1.);
        C = this.params.getOrDefault("C", 0.);
        D = this.params.getOrDefault("D", 0.);
    }


    @Override
    public double compute(double x) {
        return A / (B * x + D) + C;
    }
}
