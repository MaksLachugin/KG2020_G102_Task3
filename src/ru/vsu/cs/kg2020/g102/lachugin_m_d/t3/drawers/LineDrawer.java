package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.drawers;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points.ScreenPoint;

import java.awt.*;

public interface LineDrawer {
    void drawLine(ScreenPoint p1, ScreenPoint p2);
    void setColor(Color c);
}

