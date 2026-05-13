package logic;

import model.Point;
import model.Triangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<Triangle> readTriangles(String fileName) throws IOException {
        List<Triangle> triangles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts.length != 6) {
                    throw new IllegalArgumentException(
                            "Неверный формат строки: " + line
                    );
                }

                double x1 = Double.parseDouble(parts[0]);
                double y1 = Double.parseDouble(parts[1]);
                double x2 = Double.parseDouble(parts[2]);
                double y2 = Double.parseDouble(parts[3]);
                double x3 = Double.parseDouble(parts[4]);
                double y3 = Double.parseDouble(parts[5]);

                Triangle triangle = new Triangle(
                        new Point(x1, y1),
                        new Point(x2, y2),
                        new Point(x3, y3)
                );

                triangles.add(triangle);
            }
        }

        return triangles;
    }

    public static void writeTriangles(String fileName, List<Triangle> triangles) throws IOException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Triangle t : triangles) {
                writer.println(
                        t.getA().getX() + " " + t.getA().getY() + " " +
                                t.getB().getX() + " " + t.getB().getY() + " " +
                                t.getC().getX() + " " + t.getC().getY()
                );
            }
        }
    }
}