package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.tables;

import java.util.Map;

public class Table_7_4 {
    private static final Map<Integer, Double> table_7_4 = Map.of(
            2, 0.9,
            3, 0.9,
            4, 0.8,
            5, 0.8,
            6, 0.75,
            10, 0.6,
            20, 0.5
    );

    public static double getFactor(int number) {
        if (number >= 25) {
            return 0.4;
        } else if (table_7_4.containsKey(number)) {
            return table_7_4.get(number);
        }
        return Table.interpolation(table_7_4, number);
    }
}
