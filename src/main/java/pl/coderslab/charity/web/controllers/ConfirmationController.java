package pl.coderslab.charity.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/potwierdzenie")
public class ConfirmationController {

    @GetMapping
    public String processConfirmationPage(){
        return "form-confirmation";
    }
}
