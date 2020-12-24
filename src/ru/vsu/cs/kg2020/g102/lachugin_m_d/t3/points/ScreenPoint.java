package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.ScreenConverter;

public class ScreenPoint {
    private int x,y;

    public ScreenPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean inScreen(ScreenConverter sc) {
        return false;
    }
}
