package pl.coderslab.charity.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private CategoryRepository categoryRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        Institution institution = new Institution("Dla dobra", "Szykujemy worki dla potrzebujących");
        institutionRepository.save(institution);
        Category category = new Category("clothes");
        categoryRepository.save(category);
        List<Category> categories = categoryRepository.findAll();
        Donation donation = new Donation(1, categories, institution, "Wrocławska", "Opole", "45-000", LocalDate.now(), LocalDateTime.now(), "Oddane na potrzeby dzieci");
        donationRepository.save(donation);
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("bags", donationRepository.sumAllBags());
        model.addAttribute("supportedInstitutions", institutionRepository.getAllSupportedInstitutions());
        return "index";
    }
}
