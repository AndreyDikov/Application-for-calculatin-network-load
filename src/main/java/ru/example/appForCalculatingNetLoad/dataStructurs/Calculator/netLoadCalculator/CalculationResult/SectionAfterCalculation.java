package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.CalculationResult;

import lombok.Getter;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section.Section;

import java.util.List;

@Getter
public class SectionAfterCalculation {
    private String name;
    private double limitation;
    private List<Input> inputs;

    public static SectionAfterCalculation calculation(Section section) {
        SectionAfterCalculation sectionAfterCalculation = new SectionAfterCalculation();

        sectionAfterCalculation.name = section.getName();
        sectionAfterCalculation.limitation = section.getLimitation();

        //todo здесь будет основная шиза логика

        return sectionAfterCalculation;
    }
}
