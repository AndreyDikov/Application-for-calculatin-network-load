package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.CalculationResult;

import lombok.Getter;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section.Consumer;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.Section.Section;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SectionAfterCalculation {
    private String name;
    private double limitation;
    private List<Input> inputs;

    public static SectionAfterCalculation calculation(Section section, double regionFactor) {
        SectionAfterCalculation sectionAfterCalculation = new SectionAfterCalculation();

        sectionAfterCalculation.name = section.getName();
        sectionAfterCalculation.limitation = section.getLimitation();
        sectionAfterCalculation.inputs = new ArrayList<>();

        List<Consumer> consumersWithoutFlats = new ArrayList<>();
        List<Consumer> consumersOnlyFlats = new ArrayList<>();
        for (Consumer consumer : section.getConsumers()) {
            if (consumer.isFlats()) {
                consumersOnlyFlats.add(consumer);
            } else {
                consumersWithoutFlats.add(consumer);
            }
        }

        for (Consumer consumer : consumersWithoutFlats) {
            sectionAfterCalculation.inputs
                    .add(new Input(List.of(new Row(consumer, regionFactor))));
        }

        for (Consumer consumer : consumersOnlyFlats) {
            List<Input> inputs = new ArrayList<>(List.of(new Input(List.of(new Row(consumer, regionFactor)))));
            while (!isLessThanLimitation(inputs, sectionAfterCalculation.getLimitation())) {
                List<Input> newInputs = new ArrayList<>();
                for (Input input : inputs) {
                    Consumer getConsumer = input.getRows().get(0).getConsumer();
                    int firstNumberConsumer = getConsumer.getNumber() / 2;
                    int secondNumberConsumer = getConsumer.getNumber() - firstNumberConsumer;
                    Consumer newFirstConsumer = Consumer.builder()
                            .consumer(getConsumer.getConsumer())
                            .number(firstNumberConsumer)
                            .build();
                    Consumer newSecondConsumer = Consumer.builder()
                            .consumer(getConsumer.getConsumer())
                            .number(secondNumberConsumer)
                            .build();
                    newInputs.add(new Input(List.of(new Row(newFirstConsumer, regionFactor))));
                    newInputs.add(new Input(List.of(new Row(newSecondConsumer, regionFactor))));
                }
                inputs = newInputs;
            }
            sectionAfterCalculation.inputs.addAll(inputs);
        }

        return sectionAfterCalculation;
    }

    private static boolean isLessThanLimitation(List<Input> inputs, double limitation) {
        for (Input input : inputs) {
            if (input.getDesignPower() < limitation) {
                return true;
            }
        }
        return false;
    }

    //todo основной алгоритм для разбивки по вводам, пока не работает. Надо доработать
    /**
     public static List<List<Integer>> func(List<Integer> srcList, int n) {
     int maxSize = srcList.size() / n + (srcList.size() % n > 0 ? 1 : 0);
     List<Integer> indicesList = new ArrayList<>();
     for (int i = 0; i < srcList.size(); i++) {
     indicesList.add(i);
     }
     Set<Integer> indicesSet = new HashSet<>(indicesList);

     List<List<Integer>> allCombinations = new ArrayList<>();
     for (int i = 1; i <= maxSize; i++) {
     allCombinations.addAll(generateCombinations(indicesList, i));
     }

     List<List<List<Integer>>> fitCombinations = new ArrayList<>();
     for (List<Integer> combination : combinations(allCombinations, n)) {
     List<Integer> combinationList = new ArrayList<>();
     for (List<Integer> elem : combination) {
     combinationList.addAll(elem);
     }
     if (combinationList.size() == indicesSet.size() && new HashSet<>(combinationList).equals(indicesSet)) {
     fitCombinations.add(combination);
     }
     }

     int minDiff = Integer.MAX_VALUE;
     List<Integer> resCombination = null;
     for (List<Integer> combination : fitCombinations) {
     int minSum = srcList.stream().mapToInt(Integer::intValue).sum();
     int maxSum = 0;
     for (List<Integer> elem : combination) {
     int elemSum = elem.stream().mapToInt(srcList::get).sum();
     maxSum = Math.max(maxSum, elemSum);
     minSum = Math.min(minSum, elemSum);
     }
     int diff = maxSum - minSum;
     if (diff >= 0 && diff < minDiff) {
     minDiff = diff;
     resCombination = combination;
     }
     }

     List<List<Integer>> resList = new ArrayList<>();
     for (Integer elem : resCombination) {
     List<Integer> elemList = new ArrayList<>();
     for (Integer indice : elem) {
     elemList.add(srcList.get(indice));
     }
     resList.add(elemList);
     }

     return resList;
     }

     private static List<List<Integer>> generateCombinations(List<Integer> indicesList, int size) {
     List<List<Integer>> combinations = new ArrayList<>();
     generateCombinationsHelper(indicesList, size, 0, new ArrayList<>(), combinations);
     return combinations;
     }

     private static void generateCombinationsHelper(List<Integer> indicesList, int size, int index, List<Integer> current, List<List<Integer>> combinations) {
     if (size == 0) {
     combinations.add(new ArrayList<>(current));
     return;
     }
     for (int i = index; i < indicesList.size(); i++) {
     current.add(indicesList.get(i));
     generateCombinationsHelper(indicesList, size - 1, i + 1, current, combinations);
     current.remove(current.size() - 1);
     }
     }

     private static <T> List<List<T>> combinations(List<List<T>> lists, int n) {
     List<List<T>> results = new ArrayList<>();
     combinationsHelper(lists, n, 0, new ArrayList<>(), results);
     return results;
     }

     private static <T> void combinationsHelper(List<List<T>> lists, int n, int index, List<T> current, List<List<T>> results) {
     if (n == 0) {
     results.add(new ArrayList<>(current));
     return;
     }
     for (int i = index; i < lists.size(); i++) {
     current.add((T) lists.get(i));
     combinationsHelper(lists, n - 1, i + 1, current, results);
     current.remove(current.size() - 1);
     }
     }
     **/
}
