package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.function.ToDoubleFunction;

public interface Calculator<R> {

    R calculate();

    static<T> double sumFields(Collection<T> collection, ToDoubleFunction<T> getFled) {
        return collection.stream()
                .mapToDouble(getFled)
                .sum();
    }

    static double round(double number) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(number));
    }
}
