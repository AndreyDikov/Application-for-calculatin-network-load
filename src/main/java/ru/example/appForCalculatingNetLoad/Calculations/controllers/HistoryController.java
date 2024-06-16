package ru.example.appForCalculatingNetLoad.Calculations.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.appForCalculatingNetLoad.Calculations.entities.CalculationEntity;
import ru.example.appForCalculatingNetLoad.Calculations.services.CalculatorService;
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

        return "calculation/calculation-result";
    }
}
