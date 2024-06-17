package ru.example.appForCalculatingNetLoad.Calculations.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.appForCalculatingNetLoad.Calculations.entities.CalculationEntity;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ConsumerEntity;
import ru.example.appForCalculatingNetLoad.Calculations.entities.SectionEntity;
import ru.example.appForCalculatingNetLoad.Calculations.services.CalculatorService;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.Calculator;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.NetLoadCalculator;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.NetLoadCalculatorBuilder;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.calculationResult.CalculationResult;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section.Consumers;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section.Section;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.section.SectionBuilder;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private final CalculatorService calculatorService;

    @GetMapping("/calculations")
    public String getHistoryPage(@AuthenticationPrincipal SecurityUser securityUser,
                                 Model model) {
        List<CalculationEntity> allCalculationsByUser
                = calculatorService.getALlCalculationsByUser(securityUser);
        for (CalculationEntity calculation : allCalculationsByUser) {
            if (calculation.getIsCurrent()) {
                allCalculationsByUser.remove(calculation);
                break;
            }
        }
        allCalculationsByUser.sort(Comparator.comparing(CalculationEntity::getId));

        model.addAttribute("userCalculations", allCalculationsByUser);

        return "/calculation/calculation-history";
    }

    @GetMapping("/{calculationId}")
    public String getCalculationResult(@PathVariable("calculationId") Long calculationId,
                                       Model model) {
        CalculationEntity calculation
                = calculatorService.getCalculationById(calculationId);

        model.addAttribute("calculation", calculation);

        NetLoadCalculatorBuilder calculatorBuilder = NetLoadCalculator.builder()
                .object(calculation.getObject().getRegion());
        for (SectionEntity section : calculation.getSections()) {
            SectionBuilder sectionBuilder = Section.builder()
                    .name(section.getName())
                    .limitation(section.getLimitValue());
            for (ConsumerEntity consumer : section.getConsumers()) {
                sectionBuilder.addConsumer(consumer.getType()
                        , consumer.getNumber()
                        , consumer.getName());
            }
            calculatorBuilder.addSection(sectionBuilder.build());
        }
        NetLoadCalculator calculator = calculatorBuilder.build();
        CalculationResult result = calculator.calculate();
        model.addAttribute("result", result);
        return "calculation/calculation-result";
    }
}
