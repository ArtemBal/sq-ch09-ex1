package com.example.sqch09ex1.controllers;

import com.example.sqch09ex1.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;

    public MainController(LoggedUserManagementService loggedUserManagementService) {
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/main")
    public String home() {
        String username = loggedUserManagementService.getUsername();

        if (username == null) return "redirect:/";
        return "main.html";
    }
}
