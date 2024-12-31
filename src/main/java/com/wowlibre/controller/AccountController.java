package com.wowlibre.controller;

import com.wowlibre.config.*;
import com.wowlibre.dto.*;
import com.wowlibre.service.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    private final AccountService accountService;
    private final Configurations configurations;

    public AccountController(AccountService accountService, Configurations configurations) {
        this.accountService = accountService;
        this.configurations = configurations;
    }

    @PostMapping("/register")
    public String saveStudent(
            @ModelAttribute("register") AccountCreateDto createDto,
            @RequestParam("g-recaptcha-response") String recaptchaResponse,
            HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        accountService.create(createDto, recaptchaResponse, clientIp);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new AccountCreateDto());
        model.addAttribute("recaptchaSiteKey", configurations.getApiKey());
        return "register";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "main";
    }

}
