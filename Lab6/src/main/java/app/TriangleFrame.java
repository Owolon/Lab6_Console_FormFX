package app;

import logic.FileManager;
import logic.TriangleService;
import model.Triangle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class TriangleFrame extends JFrame {

    private JTable table;
    private JTextArea outputArea;
    private DefaultTableModel model;

    public TriangleFrame() {
        setTitle("Треугольники - Задачи 16 и 17");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private double safeParse(Object value) {
        if (value == null || value.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("Пустое значение в таблице");
        }
        return Double.parseDouble(value.toString().trim());
    }

    private void fillTable(List<Triangle> triangles) {
        model.setRowCount(0); // очищаем таблицу

        for (Triangle t : triangles) {
            model.addRow(new Object[]{
                    t.getA().getX(),
                    t.getA().getY(),
                    t.getB().getX(),
                    t.getB().getY(),
                    t.getC().getX(),
                    t.getC().getY()
            });
        }
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(),
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        String[] columns = {
                "x1", "y1",
                "x2", "y2",
                "x3", "y3"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane tableScroll = new JScrollPane(table);

        JPanel topPanel = new JPanel();

        JButton loadButton = new JButton("Загрузить файл");
        JButton saveButton = new JButton("Сохранить файл");
        JButton addButton = new JButton("Добавить строку");
        JButton task16Button = new JButton("Задание 16");
        JButton task17Button = new JButton("Задание 17");
        JButton deleteButton = new JButton("Удалить строку");


        topPanel.add(loadButton);
        topPanel.add(saveButton);
        topPanel.add(addButton);
        topPanel.add(deleteButton);
        topPanel.add(task16Button);
        topPanel.add(task17Button);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setPreferredSize(new Dimension(1000, 250));

        add(topPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(outputScroll, BorderLayout.SOUTH);

        addButton.addActionListener(e -> model.addRow(new Object[6]));
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this,
                        "Выберите строку для удаления",
                        "Информация",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            model.removeRow(row);
        });
        loadButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(this);

            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }

            try {
                File file = chooser.getSelectedFile();

                List<Triangle> triangles =
                        FileManager.readTriangles(file.getAbsolutePath());

                fillTable(triangles);

                outputArea.setText("Файл успешно загружен");

            } catch (Exception ex) {
                showError(ex);
            }
        });
        saveButton.addActionListener(e -> saveToFile());
        task16Button.addActionListener(e -> executeTask16());
        task17Button.addActionListener(e -> executeTask17());

    }


    private void executeTask17() {
        try {
            List<Triangle> triangles = TriangleService.fromTable(model);
            List<Triangle> sorted = TriangleService.sortByArea(triangles);

            StringBuilder sb = new StringBuilder();
            sb.append("=== ЗАДАНИЕ 17 ===\n\n");

            for (Triangle t : sorted) {
                sb.append(t).append("\n");
            }

            outputArea.setText(sb.toString());

        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void executeTask16() {
        try {
            List<Triangle> triangles = TriangleService.fromTable(model);

            List<Triangle> allQuadrants =
                    TriangleService.findTrianglesInAllQuadrants(triangles);

            List<Triangle> oneQuadrant =
                    TriangleService.findTrianglesInOneQuadrant(triangles);

            StringBuilder sb = new StringBuilder();
            sb.append("=== ЗАДАНИЕ 16 ===\n\n");

            sb.append("Во всех 4 четвертях:\n");
            for (Triangle t : allQuadrants) sb.append(t).append("\n");

            sb.append("\nВ одной четверти:\n");
            for (Triangle t : oneQuadrant) sb.append(t).append("\n");

            outputArea.setText(sb.toString());

        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void saveToFile() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        try {
            File file = chooser.getSelectedFile();
            List<Triangle> triangles = TriangleService.fromTable(model);

            FileManager.writeTriangles(file.getAbsolutePath(), triangles);

            outputArea.setText("Файл сохранён\n");

        } catch (Exception ex) {
            showError(ex);
        }
    }
}