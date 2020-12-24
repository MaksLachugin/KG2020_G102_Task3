package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.utils;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points.RealPoint;

public class matrix2 {

    public static double det(double[] arr) {
        return arr[0] * arr[3] - arr[1] * arr[2];
    }
}
