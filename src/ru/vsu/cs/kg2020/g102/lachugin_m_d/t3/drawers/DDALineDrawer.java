package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.drawers;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points.ScreenPoint;

import java.awt.*;

public class DDALineDrawer implements LineDrawer {
    private PixelDrawer pd;
    private Color color;

    public DDALineDrawer(PixelDrawer pd, Color c) {
        this.pd = pd;
        this.color = c;
    }

    @Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2) {
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            double k = dy / dx;
            if (x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            for (int j = x1; j <= x2; j++) {
                double i = k * (j - x1) + y1;
                pd.drawPixel(j, (int) i, color);
            }

        } else {
            if (y1 > y2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
                temp = y1;
                y1 = y2;
                y2 = temp;
            }
//            анти к, чтобы к != 0
            double k = dx / dy;
            for (int i = y1; i <= y2; i++) {
                double j = (i - y1) * k + x1;
                pd.drawPixel((int) j, i, color);
            }


        }
    }

    public void setColor(Color color) {

    }


}