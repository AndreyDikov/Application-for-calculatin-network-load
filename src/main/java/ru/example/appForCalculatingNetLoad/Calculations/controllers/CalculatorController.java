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
import ru.example.appForCalculatingNetLoad.dataStructurs.Calculator.netLoadCalculator.objectForCalculation.Regions;
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
        model.addAttribute("alreadyExist", false);
        model.addAttribute("allObjects",
                objectEntityService.getAllByUser(securityUser));
        model.addAttribute("currentCalculation",
                calculatorService.getCurrentCalculation(securityUser));
        model.addAttribute("currentUserSections",
                sectionService.getByCurrentUserCalculation(securityUser));

        return "/calculation/calculation-form";
    }

    @GetMapping("/{calculationId}")
    public String getCalculationEditForm(@PathVariable("calculationId") Long calculationId,
                                         @AuthenticationPrincipal SecurityUser securityUser,
                                         Model model) {
        CalculationEntity calculationById
                = calculatorService.getCalculationById(calculationId);
        model.addAttribute("alreadyExist", !calculationById.getIsCurrent());
        model.addAttribute("currentCalculation", calculationById);
        model.addAttribute("allObjects",
                objectEntityService.getAllByUser(securityUser));
        model.addAttribute("currentUserSections",
                sectionService.getSectionsByUserAndCalculation(securityUser, calculationById));

        return "/calculation/calculation-form";
    }

    @PostMapping("/calculate/{alreadyExist}")
    public String calculate(@PathVariable(name = "alreadyExist", required = false) Boolean alreadyExist,
                            @AuthenticationPrincipal SecurityUser securityUser,
                            CalculationEntity calculation) {
        calculatorService.performCalculation(securityUser, calculation, alreadyExist);

        if (alreadyExist) {
            return "redirect:/calculator/" + calculation.getId();
        }
        return "redirect:/calculator";
    }

    @GetMapping("/object-settings/{calculationId}")
    public String getObjectSettingsPage(@PathVariable(name = "calculationId", required = false)
                                        String calculationId, Model model) {
        ObjectEntity objectEntity = new ObjectEntity();
        model.addAttribute("objectEntity", objectEntity);
        List<String> regions = new ArrayList<>();
        for (Regions region : Regions.values()) {
            regions.addAll(region.getNames());
        }
        model.addAttribute("regions", regions);
        model.addAttribute("calculationId", calculationId);
        return "item";
    }

    @GetMapping("/create-section/{calculationId}")
    public String getAddSectionPage(@PathVariable(name = "calculationId", required = false) Long calculationId,
                                    @AuthenticationPrincipal SecurityUser securityUser) {
        SectionEntity section = new SectionEntity();
        CalculationEntity currentUserCalculation
                = calculationId != null
                ? calculatorService.getCalculationById(calculationId)
                : calculatorService.getCurrentUserCalculation(securityUser);
        int numberSections = currentUserCalculation.getSections().size();
        section.setName("Секция " + (numberSections + 1));
        section.setCalculation(currentUserCalculation);
        sectionService.saveSection(section);

        return calculationId == null
                ? "redirect:/calculator"
                : "redirect:/calculator/" + calculationId;
    }

    @GetMapping("/{id}/edit")
    public String getEditSectionPage(@PathVariable Long id,
                                     Model model) {
        SectionEntity section = sectionService.getById(id);
        model.addAttribute("section", section);

        return "/section";
    }

    @PostMapping("/create-section/{calculationId}")
    public String createSection(@PathVariable("calculationId") Long calculationId,
                                SectionEntity section) {
        sectionService.saveSection(section);

        return "redirect:/calculator/" + calculationId;
    }

    @PostMapping("/add-object/{calculationId}")
    public String addObjectSettings(@PathVariable("calculationId") Long calculationId,
                                    @AuthenticationPrincipal SecurityUser securityUser,
                                    ObjectEntity objectEntity) {
        objectEntity.setUser(securityUser);
        objectEntityService.saveObjectEntity(objectEntity);
        return "redirect:/calculator/" + calculationId;
    }

    @GetMapping("/{id}/delete/{calculationId}")
    public String deleteSection(@PathVariable Long id,
                                @PathVariable Long calculationId) {
        SectionEntity sectionToDelete = sectionService.getById(id);
        sectionService.deleteSection(sectionToDelete);

        if (calculationId != null) {
            return "redirect:/calculator/" + calculationId;
        }

        return "redirect:/calculator";
    }
}
