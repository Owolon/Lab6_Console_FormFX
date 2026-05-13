package logic;

import model.Triangle;
import model.Point;

import javax.swing.table.DefaultTableModel;
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

    public static List<Triangle> fromTable(DefaultTableModel model) {
        List<Triangle> list = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            double x1 = Double.parseDouble(model.getValueAt(i, 0).toString());
            double y1 = Double.parseDouble(model.getValueAt(i, 1).toString());
            double x2 = Double.parseDouble(model.getValueAt(i, 2).toString());
            double y2 = Double.parseDouble(model.getValueAt(i, 3).toString());
            double x3 = Double.parseDouble(model.getValueAt(i, 4).toString());
            double y3 = Double.parseDouble(model.getValueAt(i, 5).toString());

            list.add(new Triangle(
                    new Point(x1, y1),
                    new Point(x2, y2),
                    new Point(x3, y3)
            ));
        }

        return list;
    }
}