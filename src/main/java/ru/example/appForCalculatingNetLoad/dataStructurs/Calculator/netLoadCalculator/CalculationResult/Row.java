package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.CalculationResult;

import lombok.Getter;
import lombok.Setter;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section.Consumer;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section.Consumers;

@Getter
public class Row {
    private final Consumer consumer;
    private final Double installedPower;
    private final double regionFactor;
    private final double demandFactor; //todo узнать как вычисляется коэффициент спроса
    @Setter private double maxMismatchFactor;
    private final double tg;
    private final double temporaryDesignPower;
    @Setter private double designPower;
    @Setter private double reactivePower;
    @Setter private double fullPower;
    @Setter private double maxRatedCurrent;

    public Row(Consumer consumer, double regionFactor) {
        this.consumer = consumer;
        demandFactor = 1;
        tg = Math.tan(Math.acos(consumer.getConsumer().getCos()));
        if (consumer.getConsumer().equals(Consumers.APARTMENT)) {
            installedPower = null;
            this.regionFactor = regionFactor;
            temporaryDesignPower = consumer.getNumber() * this.regionFactor * demandFactor;
        } else {
            installedPower = consumer.getNumber() * consumer.getConsumer().getCoefficient();
            this.regionFactor = 1;
            temporaryDesignPower = installedPower * this.regionFactor * demandFactor;
        }
    }
}
