package ru.example.appForCalculatingNetLoad.Calculations.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @RequestMapping
    public String getHistoryPage() {
        return "calculation-history";
    }
}
