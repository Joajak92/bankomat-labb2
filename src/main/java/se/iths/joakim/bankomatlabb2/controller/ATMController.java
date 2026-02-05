package se.iths.joakim.bankomatlabb2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.joakim.bankomatlabb2.service.ATMService;

@Controller
@RequestMapping("/accounts")
public class ATMController {
    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping
    public String getBalance(Model model) {
        model.addAttribute("account", atmService.getBalance());
        return "accounts";
    }
}
