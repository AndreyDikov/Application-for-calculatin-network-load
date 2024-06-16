package ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.calculationResult;

import lombok.Getter;
import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.IGenerator;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section.Consumer;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section.Section;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        List<Input> updatedInputsForConsumersWithoutFlats = getUpdatedInputsForConsumersWithoutFlats(
                consumersWithoutFlats,
                regionFactor,
                section.getLimitation());

        if (updatedInputsForConsumersWithoutFlats.isEmpty()) {
            for (Consumer consumer : consumersOnlyFlats) {
                sectionAfterCalculation.inputs
                        .addAll(createInputsForFlats(consumer
                                , regionFactor
                                , sectionAfterCalculation.limitation));
            }
        } else if (updatedInputsForConsumersWithoutFlats.size() == 1) {
            for (Consumer consumer : consumersOnlyFlats) {
                Input notFlatInput = updatedInputsForConsumersWithoutFlats.get(0);
                List<Input> inputs = createInputsForFlats(consumer
                        , regionFactor
                        , sectionAfterCalculation.limitation
                                - notFlatInput.getDesignPower());
                List<Row> rows = notFlatInput.getRows();
                rows.addAll(inputs.get(0).getRows());
                inputs.set(0, new Input(rows));
                sectionAfterCalculation.inputs.addAll(inputs);
            }
        } else {
            for (Consumer consumer : consumersOnlyFlats) {
                List<Input> inputs = createInputsForFlats(consumer
                        , regionFactor
                        , sectionAfterCalculation.limitation
                                - getInputWithMaxDesignPower(updatedInputsForConsumersWithoutFlats).getDesignPower());
                for (int i = 0; i < updatedInputsForConsumersWithoutFlats.size(); i++) {
                    List<Row> rows1 = updatedInputsForConsumersWithoutFlats.get(i).getRows();
                    List<Row> rows2 = inputs.get(i).getRows();
                    rows1.addAll(rows2);
                    inputs.set(i, new Input(rows1));
                }
                sectionAfterCalculation.inputs.addAll(inputs);
            }
        }

        return sectionAfterCalculation;
    }

    private static Input getInputWithMaxDesignPower(List<Input> inputs) {
        Input maxInput = inputs.get(0);
        for (Input input : inputs) {
            if (input.getDesignPower() > maxInput.getDesignPower()) {
                maxInput = input;
            }
        }
        return maxInput;
    }

    private static List<Input> createInputsForFlats(Consumer consumer,
                                                    double regionFactor,
                                                    double limitation) {
        List<Consumer> consumers = createTwoConsumersFromConsumer(consumer);
        List<Input> inputs = getInputs(consumers, regionFactor);

        while (!isLessThanLimitation(inputs, limitation)) {
            List<Consumer> newConsumers = new ArrayList<>();
            for (Consumer el : consumers) {
                newConsumers.addAll(createTwoConsumersFromConsumer(el));
            }
            consumers = newConsumers;
            inputs = getInputs(consumers, regionFactor);
        }
        return inputs;
    }

    private static List<Consumer> createTwoConsumersFromConsumer(Consumer consumer) {
        int number1 = consumer.getNumber() / 2;
        return List.of(
                Consumer.builder()
                        .consumer(consumer.getConsumer())
                        .number(number1)
                        .build(),
                Consumer.builder()
                        .consumer(consumer.getConsumer())
                        .number(consumer.getNumber() - number1)
                        .build());
    }

    private static List<Input> getUpdatedInputsForConsumersWithoutFlats(List<Consumer> consumers,
                                                                        double regionFactor,
                                                                        double limitation) {
        if (consumers.size() <= 1) {
            return getInputs(consumers, regionFactor);
        }

        List<Input> updatedInputs = new ArrayList<>();
        List<Input> inputsForWithoutFlats = getInputs(consumers, regionFactor);
        List<Double> designPowersWithoutFlats = getDesignPowersConsumersWithoutFlats(inputsForWithoutFlats);

        int n = 2;
        do {
            List<List<Double>> minimumAmountDifference = minimumAmountDifference(designPowersWithoutFlats, n);
            for (List<Double> combination : minimumAmountDifference) {
                List<Row> rows = new ArrayList<>();
                for (Double power : combination) {
                    for (Input input : inputsForWithoutFlats) {
                        if (input.getDesignPower() == power) {
                            rows.add(input.getRows().get(0));
                            inputsForWithoutFlats.remove(input);
                            break;
                        }
                    }
                }
                updatedInputs.add(new Input(rows));
            }
            n += 2;
        } while (n <= designPowersWithoutFlats.size()
                && !isLessThanLimitation(updatedInputs, limitation));

        return updatedInputs;
    }

    private static List<Input> getInputs(List<Consumer> consumers, double regionFactor) {
        List<Input> inputs = new ArrayList<>();
        for (Consumer consumer : consumers) {
            inputs.add(new Input(List.of(new Row(consumer, regionFactor))));
        }
        return inputs;
    }

    private static List<Double> getDesignPowersConsumersWithoutFlats(List<Input> inputs) {
        List<Double> designPowers = new ArrayList<>();
        for (Input input : inputs) {
            designPowers.add(input.getDesignPower());
        }
        return designPowers;
    }

    private static boolean isLessThanLimitation(List<Input> inputs, double limitation) {
        for (Input input : inputs) {
            if (input.getDesignPower() < limitation) {
                return true;
            }
        }
        return false;
    }

    public static List<List<Double>> minimumAmountDifference(List<Double> srcList, int n) {
        int maxSize = (srcList.size() / n) + (srcList.size() % n > 0 ? 1 : 0);
        List<Integer> indicesList = new ArrayList<>();
        for (int i = 0; i < srcList.size(); i++) {
            indicesList.add(i);
        }
        Set<Integer> indicesSet = new HashSet<>(indicesList);

        List<List<Integer>> allCombinations = new ArrayList<>();
        for (int i = 1; i < maxSize + 1; i++) {
            IGenerator<List<Integer>> simple1 = Generator.combination(indicesList)
                    .simple(i);

            simple1.stream()
                    .forEach(allCombinations::add);
        }

        List<List<List<Integer>>> fitCombinations = new ArrayList<>();
        for (List<List<Integer>> combination : Generator.combination(allCombinations)
                .simple(n)) {
            List<Integer> combinationList = new ArrayList<>();
            for (List<Integer> elem : combination) {
                combinationList.addAll(elem);
            }
            if (combinationList.size() == indicesSet.size()
                    && new HashSet<>(combinationList).containsAll(indicesSet)) {
                fitCombinations.add(combination);
            }
        }

        List<List<Integer>> resCombination = null;
        double minDiff = Double.MAX_VALUE;
        for (List<List<Integer>> combination : fitCombinations) {
            double minSum = srcList.stream()
                    .mapToDouble(Double::doubleValue)
                    .sum();
            double maxSum = 0;
            for (List<Integer> elem : combination) {
                int elemSum = 0;
                for (Integer indice : elem) {
                    elemSum += srcList.get(indice);
                }
                if (elemSum > maxSum) {
                    maxSum = elemSum;
                }
                if (elemSum < minSum) {
                    minSum = elemSum;
                }
            }
            double diff = maxSum - minSum;
            if (diff >= 0 && (minDiff == Double.MAX_VALUE || diff < minDiff)) {
                minDiff = diff;
                resCombination = combination;
            }
        }

        List<List<Double>> resList = new ArrayList<>();
        for (List<Integer> elem : resCombination) {
            List<Double> elemList = new ArrayList<>();
            for (Integer indice : elem) {
                elemList.add(srcList.get(indice));
            }
            resList.add(elemList);
        }

        return resList;
    }
}
