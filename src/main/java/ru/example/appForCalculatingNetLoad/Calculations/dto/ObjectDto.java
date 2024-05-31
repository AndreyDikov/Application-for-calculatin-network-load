package ru.example.appForCalculatingNetLoad.Calculations.dto;

import lombok.*;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.ObjectForCalculation.ObjectForCalculation;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectDto {
    private String name;
    private String region;
    private String address;
    private String square;

    public double getRegionFactor() {
        return new ObjectForCalculation(region).getCoefficient();
    }
}
