package logic;

import model.Triangle;

import java.util.ArrayList;
import java.util.List;

public class TriangleService {

    public static List<Triangle> findTrianglesInAllQuadrants(List<Triangle> triangles) {
        List<Triangle> result = new ArrayList<>();

        for (Triangle triangle : triangles) {
            if (triangle.isInAllQuadrants()) {
                result.add(triangle);
            }
        }

        return result;
    }

    public static List<Triangle> findTrianglesInOneQuadrant(List<Triangle> triangles) {
        List<Triangle> result = new ArrayList<>();

        for (Triangle triangle : triangles) {
            if (triangle.isInOneQuadrant()) {
                result.add(triangle);
            }
        }

        return result;
    }

    public static List<Triangle> sortByArea(List<Triangle> triangles) {
        List<Triangle> result = new ArrayList<>(triangles);
        result.sort(new TriangleComparator());
        return result;
    }
}