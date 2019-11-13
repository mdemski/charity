package pl.coderslab.charity.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repositories.InstitutionRepository;

@Controller
@RequestMapping("/instytucje")
public class InstitutionController {

    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping
    public String institutionManagePage(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        return "institutions";
    }
}
