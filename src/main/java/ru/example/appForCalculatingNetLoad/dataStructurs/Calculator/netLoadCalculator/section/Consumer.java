package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Consumer {
    private String name;
    private Consumers consumer;
    @Setter private int number;

    public boolean isFlats() {
        return consumer.equals(Consumers.FLATS_WITH_ELECTRIC_STOVE_10_kW)
                || consumer.equals(Consumers.FLATS_WITH_ELECTRIC_STOVE_10_kW_WITH_AIR_CONDITIONERS)
                || consumer.equals(Consumers.FLATS_WITH_ELECTRIC_STOVE_MORE_THAN_10_kW)
                || consumer.equals(Consumers.FLATS_WITH_ELECTRIC_STOVE_MORE_THAN_10_kW_WITH_AIR_CONDITIONERS)
                || consumer.equals(Consumers.FLATS_WITH_ELECTRIC_STOVE_LESS_THAN_10_kW)
                || consumer.equals(Consumers.FLATS_WITH_ELECTRIC_STOVE_LESS_THAN_10_kW_WITH_AIR_CONDITIONERS);
    }
}
