package pl.coderslab.charity.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountController {

    @GetMapping("/admin/{id}")
    public String prepareAdminAccountPage(@PathVariable Long id, Model model){
        model.addAttribute("userId", id);
        return "admin-account";
    }
}
