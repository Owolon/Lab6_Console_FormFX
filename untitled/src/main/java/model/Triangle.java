package model;

public class Triangle {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    @Override
    public String toString() {
        return a + " " + b + " " + c +
                " | площадь = " + getArea();
    }

    public double getArea() {
        return Math.abs(
                (a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())) / 2.0
        );
    }

    public boolean isInOneQuadrant() {
        return sameQuadrant(a, b) && sameQuadrant(b, c);
    }

    private boolean sameQuadrant(Point p1, Point p2) {
        return quadrant(p1) == quadrant(p2);
    }

    private int quadrant(Point p) {
        if (p.getX() > 0 && p.getY() > 0) return 1;
        if (p.getX() < 0 && p.getY() > 0) return 2;
        if (p.getX() < 0 && p.getY() < 0) return 3;
        if (p.getX() > 0 && p.getY() < 0) return 4;
        return 0; // на осях
    }

    public boolean isInAllQuadrants() {
        boolean q1 = false, q2 = false, q3 = false, q4 = false;

        for (Point p : new Point[]{a, b, c}) {
            if (p.getX() > 0 && p.getY() > 0) q1 = true;
            if (p.getX() < 0 && p.getY() > 0) q2 = true;
            if (p.getX() < 0 && p.getY() < 0) q3 = true;
            if (p.getX() > 0 && p.getY() < 0) q4 = true;
        }

        return q1 && q2 && q3 && q4;
    }

    private boolean containsPoint(double x, double y) {
        double total = getArea();

        Triangle t1 = new Triangle(new Point(x, y), b, c);
        Triangle t2 = new Triangle(a, new Point(x, y), c);
        Triangle t3 = new Triangle(a, b, new Point(x, y));

        double sum = t1.getArea() + t2.getArea() + t3.getArea();

        return Math.abs(total - sum) < 0.00001;
    }

    private boolean inQuadrant1(Point p) {
        return p.getX() > 0 && p.getY() > 0;
    }

    private boolean inQuadrant2(Point p) {
        return p.getX() < 0 && p.getY() > 0;
    }

    private boolean inQuadrant3(Point p) {
        return p.getX() < 0 && p.getY() < 0;
    }

    private boolean inQuadrant4(Point p) {
        return p.getX() > 0 && p.getY() < 0;
    }

}