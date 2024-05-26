package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Consumers {
    APARTMENT("Квартиры (с эл.плит.)", "шт.", 10, 0.98),
    BUILT_IN_ROOMS("Коммерческие помещения", "м2", 0.2, 0.95);

    private final String name;
    private final String units;
    private final double coefficient;
    private final double cos;
}
