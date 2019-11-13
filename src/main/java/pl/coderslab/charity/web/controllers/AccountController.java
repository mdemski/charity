package pl.coderslab.charity.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AccountController {

    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public AccountController(UserRepository userRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.userRepository = userRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @GetMapping("/admin/{id}")
    public String prepareAdminAccountPage(@PathVariable Long id, Model model){
        User user = (User) userRepository.getOne(id);
        String userName = user.getFirstName();
        List<User> allUsers = userRepository.findAll();
        List<User> allAdmins = allUsers.stream().filter(user1 -> user1.getRole().equals("admin")).collect(Collectors.toList());
        List<User> normalUsers = allUsers.stream().filter(user1 -> user1.getRole().equals("Normal user")).collect(Collectors.toList());
        model.addAttribute("userName", userName);
        model.addAttribute("institutions", institutionRepository.findAll().size());
        model.addAttribute("admins", allAdmins.size());
        model.addAttribute("normalUsers", normalUsers.size());
        model.addAttribute("donations", donationRepository.findAll().size());
        return "admin-account";
    }

    @GetMapping("/moje-konto/{id}")
    public String prepareUserAccountPage(@PathVariable Long id, Model model){
        User user = (User) userRepository.getOne(id);
        String userName = user.getFirstName();
        model.addAttribute("userName", userName);
        return "my-account";
    }
}
