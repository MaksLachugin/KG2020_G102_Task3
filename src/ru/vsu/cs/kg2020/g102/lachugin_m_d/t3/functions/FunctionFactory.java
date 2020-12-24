package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.utils.nameF;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class FunctionFactory {
    Map<String, Double> paramF = new TreeMap<>();

    public IFunction getF(nameF name) {
        switch (name) {
            case Hyperbole -> {
                IFunction f = new Hyperbole(paramF);
                f.setColor(Color.red);
                return f;

            }
            case InvertExponent -> {
                IFunction f = new InvertExponent(paramF);
                f.setColor(Color.YELLOW);
                return f;
            }
            case PolinomPow3 -> {
                IFunction f = new PolinomPow3(paramF);
                f.setColor(Color.GREEN);
                return f;
            }
            case Sin -> {
                IFunction f = new Sin(paramF);
                f.setColor(Color.blue);
                return f;
            }
            case SinCos -> {
                IFunction f = new SinCos(paramF);
                f.setColor(Color.ORANGE);
                return f;
            }
            case SinExp -> {
                IFunction f = new SinExp(paramF);
                f.setColor(Color.PINK);
                return f;
            }
        }
        return null;
    }
}
