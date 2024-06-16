package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.tables;

import java.util.Map;

public interface Table {

    static double interpolation(Map<Integer, Double>table, int number) {
        double factor;
        int minKey = 0;
        int maxKey = 0;
        for (int key : table.keySet()) {
            if (key < number && key > minKey) {
                minKey = key;
            }
            if (key > number) {
                maxKey = key;
            }
        }
        for (int key : table.keySet()) {
            if (key > number && key < maxKey) {
                maxKey = key;
            }
        }
        factor = (double)(number - minKey) * 100 / (maxKey - minKey)
                * (table.get(minKey) - table.get(maxKey))
                / 100 + table.get(maxKey);
        return factor;
    }
}
