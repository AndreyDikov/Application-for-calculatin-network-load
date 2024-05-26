package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.CalculationResult;

import lombok.Getter;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.Calculator;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.NetLoadCalculator;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CalculationResult {
    private final List<SectionAfterCalculation> updatedSections;
    private final double calculatedPowerByInputs;

    public CalculationResult(NetLoadCalculator netLoadCalculator) {
        updatedSections = new ArrayList<>();
        netLoadCalculator
                .getSections()
                .forEach(section -> updatedSections.add(SectionAfterCalculation.calculation(section)));
        calculatedPowerByInputs = Calculator.sumFields(updatedSections, section ->
            Calculator.sumFields(section.getInputs(), Input::getDesignPower));
    }

    @Override
    public String toString() {
        return ""; //todo дописать метод так, чтобы выводил результат в виде красивой таблицы
    }
}
