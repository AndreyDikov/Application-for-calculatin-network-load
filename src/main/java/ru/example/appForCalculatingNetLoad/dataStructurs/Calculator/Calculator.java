package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator;

import java.util.Collection;
import java.util.function.ToDoubleFunction;

public interface Calculator<R> {

    R calculate();

    static<T> double sumFields(Collection<T> collection, ToDoubleFunction<T> getFled) {
        return collection.stream()
                .mapToDouble(getFled)
                .sum();
    }
}
