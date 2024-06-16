package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator;

import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.objectForCalculation.ObjectForCalculation;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section.Section;

import java.util.ArrayList;
import java.util.List;

public class NetLoadCalculatorBuilder {
    private ObjectForCalculation object;
    private final List<Section> sections;

    public NetLoadCalculatorBuilder() {
        sections = new ArrayList<>();
    }

    public NetLoadCalculatorBuilder object(String regionName) {
        object = new ObjectForCalculation(regionName);
        return this;
    }

    public NetLoadCalculatorBuilder addSection(Section section) {
        sections.add(section);
        return this;
    }

    public NetLoadCalculator build() {
        return new NetLoadCalculator(object, sections);
    }
}
