package app;

import logic.FileManager;
import logic.TriangleService;
import model.Triangle;

import java.util.List;

public class ConsoleApplication {

    public void run(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: java Main <input_file>");
            return;
        }

        try {
            List<Triangle> triangles = FileManager.readTriangles(args[0]);


            System.out.println("Исходные треугольники:");
            printTriangles(triangles);

            System.out.println();
            System.out.println("Задание 16. Треугольники во всех четырех четвертях:");
            List<Triangle> allQuadrants = TriangleService.findTrianglesInAllQuadrants(triangles);
            printTriangles(allQuadrants);

            System.out.println();
            System.out.println("Задание 16. Треугольники строго в одной четверти:");
            List<Triangle> oneQuadrant = TriangleService.findTrianglesInOneQuadrant(triangles);
            printTriangles(oneQuadrant);

            System.out.println();
            System.out.println("Задание 17. Сортировка по площади:");
            List<Triangle> sorted = TriangleService.sortByArea(triangles);
            printTriangles(sorted);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void printTriangles(List<Triangle> triangles) {
        if (triangles.isEmpty()) {
            System.out.println("Нет подходящих треугольников");
            return;
        }

        for (int i = 0; i < triangles.size(); i++) {
            System.out.println((i + 1) + ") " + triangles.get(i));
        }
    }
}