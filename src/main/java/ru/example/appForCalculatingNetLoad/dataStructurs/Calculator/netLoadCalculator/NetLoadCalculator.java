package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.Calculator;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.calculationResult.CalculationResult;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.objectForCalculation.ObjectForCalculation;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section.Section;

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
