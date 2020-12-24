package ru.vsu.cs.kg2020.g102.lachugin_m_d.t3;

import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.drawers.*;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.elements.Line;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions.IFunction;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions.PolinomPow3;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions.SinCos;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.functions.SinExp;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.panel.Settings;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points.RealPoint;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.points.ScreenPoint;
import ru.vsu.cs.kg2020.g102.lachugin_m_d.t3.utils.matrix2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;

public class DrawPanel extends JPanel implements MouseListener, MouseWheelListener, MouseMotionListener, KeyListener {
    private Line xAxis = new Line(-4, 0, 4, 0);
    private Line yAxis = new Line(0, -4, 0, 4);
    ScreenConverter sc = new ScreenConverter(-4, 4, 8, 8, getWidth(), getHeight());
    Map<String, Double> paramF = new TreeMap<>();

    private void setParamF(Map<String, Double> paramF) {
        paramF.put("A", 1.);
        paramF.put("B", 1.);
        paramF.put("C", 1.);
        paramF.put("D", 1.);


    }


    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;

    Settings OptionPanel = new Settings();

    DrawPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.addMouseWheelListener(this);
        this.setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {

        setParamF(paramF);
        IFunction function = new SinCos(paramF);
        sc.setsW(getWidth());
        sc.setsH(getHeight());
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics2D gr = bufferedImage.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();
        PixelDrawer pd = new BufferedImagePixelDrawer(bufferedImage);
        LineDrawer ld = new DDALineDrawer(pd);

        /**/

        /**/
        ld.drawLine(sc.r2s(xAxis.getP1()), sc.r2s(xAxis.getP2()));
        ld.drawLine(sc.r2s(yAxis.getP1()), sc.r2s(yAxis.getP2()));
        System.out.println(sc.s2r(new ScreenPoint(getX() / 2, getY() / 2)));
        function.draw(sc, ld);
        drawCells(sc, ld);
        g.drawImage(bufferedImage, 0, 0, null);
    }

    private void drawCells(ScreenConverter sc, LineDrawer ld){
        RealPoint p1= new RealPoint(sc.getrX()- sc.getrW(), sc.getrY() + sc.getrH());
        RealPoint p2= new RealPoint(sc.getrX()- sc.getrW(), sc.getrY() + sc.getrH()+2);
        ld.drawLine(sc.r2s(p1),sc.r2s(p2));
    }

    private RealPoint oldPoint;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        oldPoint = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        oldPoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        double scale = 1 + mouseWheelEvent.getWheelRotation() / 10.;

        sc.setrH(sc.getrH() * scale);
        sc.setrW(sc.getrW() * scale);


//        sc.setrY(sc.getrY() * scale);
//        sc.setrX(sc.getrX() * scale);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        RealPoint newPoint = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
        if (oldPoint != null) {
            double dx = newPoint.getX() - oldPoint.getX();
            double dy = newPoint.getY() - oldPoint.getY();
            sc.setrX(sc.getrX() - dx);
            sc.setrY(sc.getrY() - dy);
            repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void rotator() {
        if (isDown) sc.setrY(sc.getrY() - 1);
        if (isLeft) sc.setrX(sc.getrX() - 1);
        if (isUp) sc.setrY(sc.getrY() + 1);
        if (isRight) sc.setrX(sc.getrX() + 1);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) isLeft = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) isRight = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) isUp = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) isDown = true;
        if (e.getKeyChar() == 'o' && !OptionPanel.isRun) OptionPanel.run();
        rotator();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) isLeft = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) isRight = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) isUp = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN) isDown = false;
    }


}