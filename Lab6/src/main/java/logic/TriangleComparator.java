package logic;

import model.Triangle;

import java.util.Comparator;

public class TriangleComparator implements Comparator<Triangle> {

    @Override
    public int compare(Triangle t1, Triangle t2) {
        return Double.compare(t1.getArea(), t2.getArea());
    }
}