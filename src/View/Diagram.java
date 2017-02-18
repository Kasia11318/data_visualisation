package View;

import Model.*;
import Model.Point;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

class Surface2D extends JPanel {

    private List<Point> points;

    public Surface2D(List<Point> points) {
        this.points = points;
    }

    private void drawAxes(Graphics g) {
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;

        // axes
        g2d.drawLine(10,230,450,230);
        g2d.drawLine(230,450,230,10);

        // arrows
        g2d.drawLine(230,10,235,15);
        g2d.drawLine(230,10,225,15);
        g2d.drawLine(450,230,445,235);
        g2d.drawLine(450,230,445,225);

        // markers
        g2d.drawLine(440, 235, 440, 225);
        g2d.drawLine(335, 235, 335, 225);
        g2d.drawLine(20, 235, 20, 225);
        g2d.drawLine(125, 235, 125, 225);
        g2d.drawLine(225,20,235,20);
        g2d.drawLine(225,125,235,125);
        g2d.drawLine(225,440,235,440);
        g2d.drawLine(225,335,235,335);
    }

    private void drawPoints(Graphics g) {
        g.setColor(Color.BLUE);
        Graphics2D g2d = (Graphics2D) g;

        Float maxX = 0.f;
        Float maxY = 0.f;

        for (Point point : points) {
            if (Math.abs(point.getDimensionals().get(0)) > maxX) {
                maxX = Math.abs(point.getDimensionals().get(0));
            }

            if (Math.abs(point.getDimensionals().get(1)) > maxY) {
                maxY = Math.abs(point.getDimensionals().get(1));
            }
        }

        for (Point point : points) {
            int x = Math.round((point.getDimensionals().get(0) / maxX) * 210);
            int y = Math.round((point.getDimensionals().get(1) / maxY) * 210);
            y *= -1;
            x += 230;
            y += 230;
            g2d.drawLine(x-1,y+1,x+1,y-1);
            g2d.drawLine(x-1,y-1,x+1,y+1);
        }

        g.setColor(Color.BLACK);
        Font f = g.getFont();
        Font f1 = new Font(f.getName(), Font.PLAIN, 9);
        g.setFont(f1);
        g2d.drawString(Float.toString(maxX), 430, 245);
        g2d.drawString(Float.toString(maxX / 2), 325, 245);
        g2d.drawString(Float.toString(-1 * maxX), 10, 245);
        g2d.drawString(Float.toString(-1 * maxX / 2), 115, 245);
        g2d.drawString(Float.toString(maxY), 239, 24);
        g2d.drawString(Float.toString(maxY / 2), 239, 129);
        g2d.drawString(Float.toString(-1 * maxY), 239, 443);
        g2d.drawString(Float.toString(-1 * maxY / 2), 239, 338);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAxes(g);
        drawPoints(g);
    }
}

public class Diagram extends JFrame {

    private CoordinateSystem coordinateSystem;

    public Diagram(CoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        initUI();
    }

    private void initUI() {
        setTitle("Diagram");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void draw() {
        switch (coordinateSystem.getCoordinateSystemType()) {
            case TWO_D:
                add(new Surface2D(coordinateSystem.getCoordinates()));
                break;
            case THREE_D:
                break;
            case PARALLEL_D:
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CoordinateSystem coordinateSystem = new CoordinateSystem();
                coordinateSystem.setCoordinateSystemType(CoordinateSystem.CoordinateSystemType.TWO_D);
                Point point1 = new Point();
                List<Float> dims1 = new ArrayList<>();
                dims1.add(1.0f);
                dims1.add(-4.0f);
                point1.setDimensionals(dims1);
                coordinateSystem.addPoint(point1);
                Point point2 = new Point();
                List<Float> dims2 = new ArrayList<>();
                dims2.add(10.f);
                dims2.add(32.4f);
                point2.setDimensionals(dims2);
                coordinateSystem.addPoint(point2);
                Point point3 = new Point();
                List<Float> dims3 = new ArrayList<>();
                dims3.add(-50.2f);
                dims3.add(10.4f);
                point3.setDimensionals(dims3);
                coordinateSystem.addPoint(point3);
                Point point4 = new Point();
                List<Float> dims4 = new ArrayList<>();
                dims4.add(-10.f);
                dims4.add(-128.4f);
                point4.setDimensionals(dims4);
                coordinateSystem.addPoint(point4);
                Diagram ex = new Diagram(coordinateSystem);
                ex.draw();
                ex.setVisible(true);
            }
        });
    }
}
