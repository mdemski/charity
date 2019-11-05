package pl.coderslab.charity.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.repositories.UserRepository;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private UserRepository userRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("bags", donationRepository.sumAllBags());
        List<Institution> institutions = institutionRepository.getAllSupportedInstitutions();
        model.addAttribute("supportedInstitutions", institutions.size());
        return "index";
    }

    @GetMapping("/login")
    public String prepareLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginUser(@Valid User user, BindingResult result){
        if (!(result.hasErrors())){
            User logInUser = userRepository.findByEmail(user.getEmail());
            if(logInUser != null){
                if(logInUser.getPassword().equals(user.getPassword())){
                    return "index";
                } else {
                    return "login";
                }
            } else {
                return "login";
            }
        } else {
            return "login";
        }
    }
}
