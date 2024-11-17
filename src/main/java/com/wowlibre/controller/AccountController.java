package com.wowlibre.controller;

import com.wowlibre.dto.*;
import com.wowlibre.service.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public String saveStudent(@ModelAttribute("register") AccountCreateDto createDto) {
        accountService.create(createDto);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new AccountCreateDto());
        return "register";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "main";
    }

}
