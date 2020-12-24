package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.ScreenConverter;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.drawers.LineDrawer;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.elements.Line;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points.RealPoint;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points.ScreenPoint;

import java.util.*;

public abstract class IFunction {
    List<List<ScreenPoint>> points = new ArrayList<>();
    protected Map<String, Double> params = new TreeMap<>();

    double step = 0.1;


    public IFunction(Map<String, Double> params) {
        this.params = params;
    }

    public void draw(ScreenConverter sc, LineDrawer ld) {
        generationPoints(sc);
        if (!isEmpty()) {
            generationPoints(sc);
            for (List<ScreenPoint> list :
                    points) {
                for (int i = 0; i < list.size() - 1; i++) {
                    ld.drawLine(list.get(i), list.get(i + 1));
                }
            }

        }
    }

    public abstract double compute(double x);

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public void generationPoints(ScreenConverter sc) {
        points.clear();
        RealPoint[] rPointsRect = new RealPoint[]{
                sc.s2r(new ScreenPoint(0, 0)),
                sc.s2r(new ScreenPoint(sc.getsW(), sc.getsH()))
        };

        Line UpLine = new Line(rPointsRect[0], new RealPoint(rPointsRect[1].getX(), rPointsRect[0].getY()));
        Line DownLine = new Line(rPointsRect[1], new RealPoint(rPointsRect[0].getX(), rPointsRect[1].getY()));
        Line LeftLine = new Line(rPointsRect[0], new RealPoint(rPointsRect[0].getX(), rPointsRect[1].getY()));
        Line RightLine = new Line(rPointsRect[1], new RealPoint(rPointsRect[1].getX(), rPointsRect[0].getY()));
        // набор точке от левой границы - 1 шаг до правой границы + 1 шаг
        List<RealPoint> realPoints = new ArrayList<>();
        realPoints.add(new RealPoint(rPointsRect[0].getX(), compute(rPointsRect[0].getX())));
        for (double x = (int) (rPointsRect[0].getX() / step) * step; x < (int) (rPointsRect[1].getX() / step + 1) * step; x += step) {
            realPoints.add(new RealPoint(x, compute(x)));
        }
        realPoints.add(new RealPoint(rPointsRect[1].getX(), compute(rPointsRect[1].getX())));

        //распределение на промежутки зон видимости
        boolean inScreen = true;
        List<RealPoint> realPointList = new ArrayList<>();
        List<List<RealPoint>> realPointListWave = new ArrayList<>();
        for (RealPoint realPoint : realPoints) {
            if (LeftLine.betweenY(realPoint) == inScreen) {
                realPointList.add(realPoint);
            } else {
                realPointListWave.add(realPointList);
                realPointList = new ArrayList<>();
                inScreen = !inScreen;
                realPointList.add(realPoint);
            }
        }
        realPointListWave.add(realPointList);
        deleteZeroArr(realPointListWave);
        int i = 0;
        while (i < realPointListWave.size()) {
            if (realPointListWave.get(i).size() >= 2) {
                int j = 0;
                while (j < realPointListWave.get(i).size() - 1) {
                    List<RealPoint> realPointListT = new ArrayList<>();
                    RealPoint p1 = realPointListWave.get(i).get(j);
                    RealPoint p2 = realPointListWave.get(i).get(j + 1);
                    Line line = new Line(p1, p2);
                    if (line.isInUp() && p1.getY() < rPointsRect[1].getY() && p2.getY() > rPointsRect[0].getY()) {
                        p1 = line.crossingWithLine(DownLine);
                        p2 = line.crossingWithLine(UpLine);
                        realPointListT.add(p1);
                        realPointListT.add(p2);
                    } else if (line.isInDown() && p1.getY() > rPointsRect[0].getY() && p2.getY() < rPointsRect[1].getY()) {
                        p1 = line.crossingWithLine(UpLine);
                        p2 = line.crossingWithLine(DownLine);
                        realPointListT.add(p1);
                        realPointListT.add(p2);
                    }
                    if (realPointListT.size() >= 2) {
                        List<RealPoint> realPointList1 = realPointListWave.get(i).subList(0, j + 1);
                        List<RealPoint> realPointList2 = realPointListWave.get(i).subList(j + 1, realPointListWave.get(i).size());
                        realPointListWave.set(i, realPointList1);
                        realPointListWave.add(i + 1, realPointListT);
                        realPointListWave.add(i + 2, realPointList2);
                    }
                    j++;
                }
            }
            i++;
        }
        deleteZeroArr(realPointListWave);


        // очистка пустых промежутков, массив проверки нахождения отрезка на экране
        List<Boolean> listFlags = new ArrayList<>();

        for (List<RealPoint> t : realPointListWave) listFlags.add(LeftLine.betweenY(t.get(0)));

        //преобразование в набор видимых отрезков
        try {


            for (i = 0; i < realPointListWave.size(); i++) {
                // если промежуток на экране
                if (listFlags.get(i)) {
                    // перед ним есть промежуток и этот промежуток вне экрана
                    if (i >= 1 && !listFlags.get(i - 1)) {
                        RealPoint t = realPointListWave.get(i - 1).get(realPointListWave.get(i - 1).size() - 1);
                        Line tLine = new Line(t, realPointListWave.get(i).get(0));
                        RealPoint newRealPoint;
                        // если линия часть возрастающей функции, то она начинается пересекает экран снизу, иначе пересекает экран сверху
                        if (tLine.isInUp()) newRealPoint = tLine.crossingWithLine(DownLine);
                        else newRealPoint = tLine.crossingWithLine(UpLine);
                        realPointListWave.get(i).add(0, newRealPoint);
                    }
                    if (i < listFlags.size() - 1 && !listFlags.get(i + 1)) {
                        RealPoint t = realPointListWave.get(i + 1).get(0);
                        Line tLine = new Line(realPointListWave.get(i).get(realPointListWave.get(i).size() - 1), t);
                        RealPoint newRealPoint;
                        // если линия часть возрастающей функции, то она начинается пересекает экран cверху, иначе пересекает экран снизу
                        if (tLine.isInUp()) newRealPoint = tLine.crossingWithLine(UpLine);
                        else newRealPoint = tLine.crossingWithLine(DownLine);
                        realPointListWave.get(i).add(newRealPoint);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println();

        }
        System.out.println();
        // заполнение данных
        for (i = 0; i < listFlags.size(); i++) {
            if (listFlags.get(i)) {
                List<ScreenPoint> screenPointList = new ArrayList<>();
                for (RealPoint p : realPointListWave.get(i)
                ) {

                    screenPointList.add(sc.r2s(p));
                }
                points.add(screenPointList);
            }
        }
        System.out.println();


    }

    private void deleteZeroArr(List<List<RealPoint>> realPointListWave) {
        int i = 0;
        while (realPointListWave.size() > i) {
            if (realPointListWave.get(i).size() == 0) {
                realPointListWave.remove(i);
            } else {
                i++;
            }
        }
    }
}
