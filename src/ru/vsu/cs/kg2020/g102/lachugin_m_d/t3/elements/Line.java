package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.elements;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points.RealPoint;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.utils.matrix2;

import java.util.Map;

public class Line {
    private RealPoint p1, p2;

    public Line(RealPoint p1, RealPoint p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new RealPoint(x1, y1);
        this.p2 = new RealPoint(x2, y2);
    }

    public RealPoint getP1() {
        return p1;
    }

    public void setP1(RealPoint p1) {
        this.p1 = p1;
    }

    public RealPoint getP2() {
        return p2;
    }

    public void setP2(RealPoint p2) {
        this.p2 = p2;
    }

    public RealPoint crossingWithStraightLine(Line line) {
        // проверка пересечения прямых, чайстью которых является эта линии
        double x1 = p1.getX(), x2 = p2.getX(),
                x3 = line.p1.getX(), x4 = line.p2.getX(),
                y1 = p1.getY(), y2 = p2.getY(),
                y3 = line.p1.getY(), y4 = line.p2.getY();
        double t = matrix2.det(new double[]
                {
                        matrix2.det(new double[]{x1, 1, x2, 1}),
                        matrix2.det(new double[]{y1, 1, y2, 1}),
                        matrix2.det(new double[]{x3, 1, x4, 1}),
                        matrix2.det(new double[]{y3, 1, y4, 1})
                }
        );
        double x = matrix2.det(new double[]
                {
                        matrix2.det(new double[]{x1, y1, x2, y2}),
                        matrix2.det(new double[]{x1, 1, x2, 1}),
                        matrix2.det(new double[]{x3, y3, x4, y4}),
                        matrix2.det(new double[]{x3, 1, x4, 1})
                }
        ) / t;

        double y = matrix2.det(new double[]
                {
                        matrix2.det(new double[]{x1, y1, x2, y2}),
                        matrix2.det(new double[]{y1, 1, y2, 1}),
                        matrix2.det(new double[]{x3, y3, x4, y4}),
                        matrix2.det(new double[]{y3, 1, y4, 1})
                }
        ) / t;

        return new RealPoint(x, y);
    }

    public boolean betweenY(RealPoint point) {
        return (point.getY() - p1.getY() >= -Math.pow(10, -9) && (p2.getY() - point.getY()) >= -Math.pow(10, -9)) ||
                (p1.getY() - point.getY() >= -Math.pow(10, -9) && point.getY() - p2.getY() >= -Math.pow(10, -9));
    }

    public boolean betweenX(RealPoint point) {
        return (point.getX() - p1.getX() > Math.pow(10, -8) && p2.getX() - point.getX() > Math.pow(10, -8)) ||
                (p1.getX() - point.getX() > Math.pow(10, -8) && point.getX() - p2.getX() > Math.pow(10, -8));
    }

    public boolean isInUp() {
        balance();
        return p1.getY() < p2.getY();
    }

    public boolean isInDown() {
        balance();
        return p1.getY() > p2.getY();
    }

    private void balance() {
        if (p1.getX() > p2.getX()) {
            RealPoint t = p1;
            p1 = p2;
            p2 = t;
        }
    }

    public boolean contains(RealPoint p) {
        double t = matrix2.det(new double[]{
                p2.getX() - p1.getX(), p2.getY() - p1.getY(),
                p.getX() - p1.getX(), p.getY() - p1.getY()});
        return t < Math.pow(10, -6);
    }

    public RealPoint crossingWithLine(Line line) {
        RealPoint t = crossingWithStraightLine(line);
        return (contains(t) && line.contains(t)) ? t : null;
    }

}