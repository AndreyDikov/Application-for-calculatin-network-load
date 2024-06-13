package ru.example.appForCalculatingNetLoad.Calculations.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.appForCalculatingNetLoad.Calculations.dto.ObjectDto;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ObjectEntity;
import ru.example.appForCalculatingNetLoad.Calculations.services.CalculatorService;
import ru.example.appForCalculatingNetLoad.Calculations.services.ObjectEntityService;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.ObjectForCalculation.Regions;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final ObjectEntityService objectEntityService;

    @GetMapping
    public String getCalculationPage(Model model) {
        model.addAttribute("allObjects", objectEntityService.getAll());
        return "calculation";
    }

    @RequestMapping("/object-settings")
    public String getObjectSettingsPage(Model model) {
        ObjectEntity objectEntity = new ObjectEntity();
        model.addAttribute("objectEntity", objectEntity);
        List<String> regions = new ArrayList<>();
        for (Regions region : Regions.values()) {
            regions.addAll(region.getNames());
        }
        model.addAttribute("regions", regions);
        return "item";
    }

    @RequestMapping("/create-section")
    public String getAddSectionPage() {
        return "section";
    }

    @PostMapping("/add-object")
    public String addObjectSettings(@AuthenticationPrincipal SecurityUser securityUser,
                                    ObjectEntity objectEntity) {
        objectEntity.setUser(securityUser);
        objectEntityService.saveObjectEntity(objectEntity);
        return "redirect:/calculator";
    }

    @RequestMapping("/create-section/create-consumer")
    public String getAddConsumerPage() {
        return "consumer";
    }
}
