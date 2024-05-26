package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Consumer {
    private Consumers consumer;
    private int number;
}
