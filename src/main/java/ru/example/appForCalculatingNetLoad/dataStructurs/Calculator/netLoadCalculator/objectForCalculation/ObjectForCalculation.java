package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.objectForCalculation;

import lombok.Getter;

@Getter
public class ObjectForCalculation {
    private double coefficient;

    public ObjectForCalculation(String regionName) {
       for (Regions region : Regions.values()) {
           if (region.getNames().contains(regionName)) {
               coefficient = region.getCoefficient();
               break;
           }
       }
    }
}
