package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.calculationResult;

import lombok.Getter;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.Calculator;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.NetLoadCalculator;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CalculationResult {
    private final List<SectionAfterCalculation> updatedSections;
    private final Double calculatedPowerByInputs;

    public CalculationResult(NetLoadCalculator netLoadCalculator) {
        updatedSections = new ArrayList<>();
        netLoadCalculator
                .getSections()
                .forEach(section ->
                        updatedSections.add(SectionAfterCalculation.calculation(section,
                                        netLoadCalculator.getObject().getCoefficient())));
        calculatedPowerByInputs = Calculator.sumFields(updatedSections, section ->
            Calculator.sumFields(section.getInputs(), Input::getDesignPower));
    }

    public RoundResult round() {
        return new RoundResult(this);
    }
}
