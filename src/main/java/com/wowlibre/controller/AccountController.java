package com.wowlibre.controller;

import com.wowlibre.dto.*;
import com.wowlibre.service.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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
        return "register";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "main";
    }

}
