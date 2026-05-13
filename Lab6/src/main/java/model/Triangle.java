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
        return String.format(
                "[%s %s %s] | S=%.3f",
                a, b, c, getArea()
        );
    }

    public double getArea() {
        return Math.abs(
                (a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())) / 2.0
        );
    }

    public boolean isInOneQuadrant() {
        return sameQuadrant(a, b, c);
    }

    private boolean sameQuadrant(Point p1, Point p2, Point p3) {
        return inQuadrant1(p1) && inQuadrant1(p2) && inQuadrant1(p3)
                || inQuadrant2(p1) && inQuadrant2(p2) && inQuadrant2(p3)
                || inQuadrant3(p1) && inQuadrant3(p2) && inQuadrant3(p3)
                || inQuadrant4(p1) && inQuadrant4(p2) && inQuadrant4(p3);
    }

    public boolean isInAllQuadrants() {
        double minX = Math.min(a.getX(), Math.min(b.getX(), c.getX()));
        double maxX = Math.max(a.getX(), Math.max(b.getX(), c.getX()));
        double minY = Math.min(a.getY(), Math.min(b.getY(), c.getY()));
        double maxY = Math.max(a.getY(), Math.max(b.getY(), c.getY()));

        if (!(minX < 0 && maxX > 0 && minY < 0 && maxY > 0)) {
            return false;
        }

        return containsPoint(0.5, 0.5)
                && containsPoint(-0.5, 0.5)
                && containsPoint(-0.5, -0.5)
                && containsPoint(0.5, -0.5);
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