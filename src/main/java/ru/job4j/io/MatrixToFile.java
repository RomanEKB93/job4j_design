package ru.job4j.io;

import java.io.FileOutputStream;

public class MatrixToFile {
    public static void multiple(int size) {
        try (FileOutputStream out = new FileOutputStream("multTable.txt")) {
            for (int i = 0; i < size; i++) {
                StringBuilder el = new StringBuilder();
                for (int j = 0; j < size; j++) {
                   el.append((i + 1) * (j + 1)).append("\t");
                }
                out.write(el.append(System.lineSeparator())
                        .toString().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiple(10);
    }
}
