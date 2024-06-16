package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Section {
    private String name;
    private double limitation;
    private List<Consumer> consumers;

    public static SectionBuilder builder() {
        return new SectionBuilder();
    }
}
