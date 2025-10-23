package com.unisabana.grupo5.k8s.controller;

import com.unisabana.grupo5.k8s.service.MicroserviceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MicroserviceController {
    @Value("${POD_NAME:default-pod}")
    private String podName;
    private final MicroserviceService microserviceService;

    public MicroserviceController(MicroserviceService microserviceService){
        this.microserviceService = microserviceService;
    }

    @GetMapping("/")
    public String index(Model model) {

        String attachedContent = this.microserviceService.getAttachedText();
        model.addAttribute("podName", podName);
        model.addAttribute("attachedContent", attachedContent);
        return "microservice"; // renderiza templates/index.html
    }
}
