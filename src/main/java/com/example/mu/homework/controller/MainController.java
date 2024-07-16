package com.example.mu.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 페이지
 */
@Controller
public class MainController {

    @GetMapping("/main")
    public String main(Model model) {
        return "main";
    }
}
