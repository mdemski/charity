package pl.coderslab.charity.web.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/rejestracja")
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String prepareRegistrationPage(Model model){
        model.addAttribute("data", new User());
        return "register";
    }

    @PostMapping
    public String processRegisterForm(@Valid User data, BindingResult result, @RequestParam String password2){
        if(result.hasErrors()){
            return "register";
        }
        if (!(data.getPassword().equals(password2))){
            return "register";
        }
        User user = new User();
        user.setEmail(data.getEmail());
        String encodedPassword = passwordEncoder.encode(data.getPassword());
        user.setPassword(encodedPassword);
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setRole(data.getRole());
        userRepository.save(user);
        return "redirect:/login";
    }
}
