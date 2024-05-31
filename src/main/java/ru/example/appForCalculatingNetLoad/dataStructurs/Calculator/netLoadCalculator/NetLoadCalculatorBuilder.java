package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator;

import dataStructures.Calculator.netLoadCalculator.ObjectForCalculation.ObjectForCalculation;
import dataStructures.Calculator.netLoadCalculator.Section.Section;

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
