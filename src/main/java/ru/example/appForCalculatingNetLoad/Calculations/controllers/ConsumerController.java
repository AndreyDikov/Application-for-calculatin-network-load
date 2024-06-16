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

/**
 * Контроллер для работы с потребителями
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/calculator/consumers")
public class ConsumerController {

    private final SectionService sectionService;
    private final ConsumerService consumerService;

    /**
     * Метод для получения страницы добавления потребителя
     *
     * @param sectionId идентификатор секции, к которой относится потребитель
     * @param model     модель
     * @return страница добавления и редактирования потребителя
     */
    @GetMapping("/{sectionId}/create")
    public String getAddConsumerPage(@PathVariable Long sectionId, Model model) {
        SectionEntity section = sectionService.getById(sectionId);
        ConsumerEntity consumerEntity = new ConsumerEntity();
        consumerEntity.setSection(section);
        model.addAttribute("consumer", consumerEntity);
        model.addAttribute("sectionId", sectionId);

        return "consumers/create-or-update";
    }

    /**
     * Метод для создания и редактирования потребителя
     *
     * @param sectionId идентификатор секции, к которой относится потребитель
     * @param consumer  потребитель для сохранения или редактирования
     * @return редирект на страницу редактирования секции
     */
    @PostMapping("/{sectionId}/merge")
    public String mergeConsumer(@PathVariable Long sectionId, ConsumerEntity consumer) {
        consumerService.save(consumer);

        return "redirect:/calculator/" + sectionId + "/edit";
    }

    /**
     * Метод для получения страницы редактирования потребителя
     * @param sectionId идентификатор секции, к которой относится потребитель
     * @param consumerId идентификатор потребителя для редактирования
     * @param model модель
     * @return страницу редактирования и добавления потребителя
     */
    @GetMapping("/{sectionId}/edit/{consumerId}")
    public String getEditConsumerPage(@PathVariable Long sectionId,
                                      @PathVariable Long consumerId,
                                      Model model) {
        model.addAttribute("sectionId", sectionId);
        model.addAttribute("consumer",
                consumerService.getById(consumerId));
        return "consumers/create-or-update";
    }

    @GetMapping("/{id}/delete")
    public String deleteConsumer(@PathVariable Long id) {
        ConsumerEntity consumerToDelete = consumerService.getById(id);
        Long sectionId = consumerToDelete.getSection().getId();
        consumerService.delete(consumerToDelete);

        return "redirect:/calculator/" + sectionId + "/edit";
    }
}
