package ru.example.appForCalculatingNetLoad.Calculations.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ConsumerEntity;
import ru.example.appForCalculatingNetLoad.Calculations.entities.SectionEntity;
import ru.example.appForCalculatingNetLoad.Calculations.services.ConsumerService;
import ru.example.appForCalculatingNetLoad.Calculations.services.SectionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/calculator/consumers")
public class ConsumerController {

    private final SectionService sectionService;
    private final ConsumerService consumerService;

    @GetMapping("/{sectionId}/create")
    public String getAddConsumerPage(@PathVariable Long sectionId, Model model) {
        SectionEntity section = sectionService.getById(sectionId);
        ConsumerEntity consumerEntity = new ConsumerEntity();
        consumerEntity.setSection(section);
        model.addAttribute("consumer", consumerEntity);
        model.addAttribute("sectionId", sectionId);

        return "consumers/create-or-update";
    }

    @PostMapping("/{sectionId}/create")
    public String addConsumer(@PathVariable Long sectionId, ConsumerEntity consumer) {
        consumerService.save(consumer);

        return "redirect:/calculator/" + sectionId + "/edit";
    }

    @GetMapping("/{id}/edit")
    public String getEditConsumerPage(@PathVariable Long id, Model model) {
        model.addAttribute("consumer",
                consumerService.getById(id));
        return "consumers/create-or-update";
    }
}
