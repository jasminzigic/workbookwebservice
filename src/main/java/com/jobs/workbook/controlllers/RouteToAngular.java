package com.jobs.workbook.controlllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteToAngular implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "/";
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
