package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.CalculationResult;

import lombok.Getter;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.Calculator;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Input {
    private final List<Row> rows;
    private double cos;
    private double tg;
    private double designPower;
    private double reactivePower;
    private double fullPower;
    private double maxRatedCurrent;

    public Input(List<Row> rows) {
        this.rows = new ArrayList<>();
        setRows(rows);
        addGeneralInputData();
    }

    private void setRows(List<Row> rows) {
        int rowWithMaxTemporaryDesignPower = searchRowWithMaxTemporaryDesignPower(rows);
        for (int i = 0; i < rows.size(); i++) {
            Row row = rows.get(i);
            row.setMaxMismatchFactor(i == rowWithMaxTemporaryDesignPower ? 1 : 0.9);
            row.setDesignPower(row.getTemporaryDesignPower() * row.getMaxMismatchFactor());
            row.setReactivePower(row.getTg() * row.getDesignPower());
            row.setFullPower(row.getDesignPower() / row.getConsumer().getConsumer().getCos());
            row.setMaxRatedCurrent(row.getFullPower() / 0.66);
            this.rows.add(row);
        }
    }

    private void addGeneralInputData() {
        designPower = Calculator.sumFields(rows, Row::getDesignPower);
        reactivePower = Calculator.sumFields(rows, Row::getReactivePower);
        fullPower = Math.sqrt(Math.pow(designPower, 2) + Math.pow(reactivePower, 2));
        cos = designPower / fullPower;
        tg = Math.tan(Math.acos(cos));
        maxRatedCurrent = fullPower / 0.66;
    }

    private static int searchRowWithMaxTemporaryDesignPower(List<Row> rows) {
        int max = 0;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getTemporaryDesignPower() > rows.get(max).getTemporaryDesignPower()) {
                max = i;
            }
        }
        return max;
    }
}
