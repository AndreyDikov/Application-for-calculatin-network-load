package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.Calculator;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.CalculationResult.CalculationResult;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.ObjectForCalculation.ObjectForCalculation;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section.Section;

import java.util.List;

@Getter
@AllArgsConstructor
public class NetLoadCalculator implements Calculator<CalculationResult> {

    private ObjectForCalculation object;
    private List<Section> sections;

    public static NetLoadCalculatorBuilder builder() {
        return new NetLoadCalculatorBuilder();
    }

    @Override
    public CalculationResult calculate() {
        return new CalculationResult(this);
    }
}
