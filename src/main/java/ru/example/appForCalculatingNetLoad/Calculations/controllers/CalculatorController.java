package ru.example.appForCalculatingNetLoad.Calculations.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.appForCalculatingNetLoad.Calculations.entities.CalculationEntity;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ObjectEntity;
import ru.example.appForCalculatingNetLoad.Calculations.entities.SectionEntity;
import ru.example.appForCalculatingNetLoad.Calculations.services.CalculatorService;
import ru.example.appForCalculatingNetLoad.Calculations.services.ObjectEntityService;
import ru.example.appForCalculatingNetLoad.Calculations.services.SectionService;
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.ObjectForCalculation.Regions;
import ru.example.appForCalculatingNetLoad.security.securityUsers.entities.SecurityUser;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/calculator")
public class CalculatorController {

    private final SectionService sectionService;
    private final CalculatorService calculatorService;
    private final ObjectEntityService objectEntityService;

    @GetMapping
    public String getCalculationPage(@AuthenticationPrincipal SecurityUser securityUser, Model model) {
        model.addAttribute("allObjects", objectEntityService.getAll());
        model.addAttribute("currentCalculation",
                calculatorService.getCurrentCalculation(securityUser));
        model.addAttribute("currentUserSections",
                sectionService.getByCurrentUserCalculation(securityUser));

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

    @GetMapping("/create-section")
    public String getAddSectionPage(@AuthenticationPrincipal SecurityUser securityUser) {
        SectionEntity section = new SectionEntity();
        CalculationEntity currentUserCalculation
                = calculatorService.getCurrentUserCalculation(securityUser);
        int numberSections = currentUserCalculation.getSections().size();
        section.setName("Секция " + (numberSections + 1));
        section.setCalculation(currentUserCalculation);
        sectionService.saveSection(section);

        return "redirect:/calculator";
    }

    @GetMapping("/{id}/edit")
    public String getEditSectionPage(@PathVariable Long id,
                                     Model model) {
        SectionEntity section = sectionService.getById(id);
        model.addAttribute("section", section);

        return "/section";
    }

    @PostMapping("/create-section")
    public String createSection(SectionEntity section) {
        sectionService.saveSection(section);

        return "redirect:/calculator";
    }

    @PostMapping("/add-object")
    public String addObjectSettings(@AuthenticationPrincipal SecurityUser securityUser,
                                    ObjectEntity objectEntity) {
        objectEntity.setUser(securityUser);
        objectEntityService.saveObjectEntity(objectEntity);
        return "redirect:/calculator";
    }

    @GetMapping("/{id}/delete")
    public String deleteSection(@PathVariable Long id) {
        SectionEntity sectionToDelete = sectionService.getById(id);
        sectionService.deleteSection(sectionToDelete);

        return "redirect:/calculator";
    }
}
