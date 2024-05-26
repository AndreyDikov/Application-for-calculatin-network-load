package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionBuilder {

    private String name;
    private double limitation;
    private final List<Consumer> consumers;

    public SectionBuilder() {
        consumers = new ArrayList<>();
    }

    public SectionBuilder name(String name) {
        this.name = name;
        return this;
    }

    public SectionBuilder limitation(double limitation) {
        this.limitation = limitation;
        return this;
    }

    public SectionBuilder addConsumer(Consumers consumer, int number) {
        consumers.add(Consumer.builder()
                .consumer(consumer)
                .number(number)
                .build());
        return this;
    }

    public Section build() {
        return new Section(name, limitation, consumers);
    }
}
