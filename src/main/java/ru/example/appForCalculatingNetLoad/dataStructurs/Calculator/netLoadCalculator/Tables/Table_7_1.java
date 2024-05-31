package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Tables;

import java.util.HashMap;
import java.util.Map;

public class Table_7_1 implements Table {
    private static final Map<Integer, Double> table_7_1 = new HashMap<>() {{
        put(1, 10.0);
        put(2, 10.0);
        put(3, 10.0);
        put(4, 10.0);
        put(5, 10.0);
        put(6, 5.1);
        put(9, 3.8);
        put(12, 3.2);
        put(15, 2.8);
        put(18, 2.6);
        put(24, 2.2);
        put(40, 1.95);
        put(60, 1.7);
        put(100, 1.5);
        put(200, 1.36);
        put(400, 1.27);
        put(600, 1.23);
    }};

    public static double getFactor(int number) {
        if (number >= 1000) {
            return 1.19;
        } else if (table_7_1.containsKey(number)) {
            return table_7_1.get(number);
        }
        return Table.interpolation(table_7_1, number);
    }
}
