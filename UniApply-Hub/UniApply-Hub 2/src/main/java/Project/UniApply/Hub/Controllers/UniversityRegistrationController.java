package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Data.UniversitiesRepository;
import Project.UniApply.Hub.Models.DTO.RegisterUniversitiesFormDTO;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/universities/UniversityRegister")
public class UniversityRegistrationController {

    @Autowired
    private UniversitiesRepository universitiesRepository;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("title", "University Register");
        model.addAttribute("registerUniversitiesFormDTO", new RegisterUniversitiesFormDTO());
        return "universities/register";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("registerUniversitiesFormDTO") @Valid RegisterUniversitiesFormDTO formDTO,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "universities/register";
        }


        Universities university = new Universities();
        university.setEmail(formDTO.getEmail());
       // university.setPassword(formDTO.getPassword());
        university.setUniversityName(formDTO.getUniversityName());
        university.setAddress(formDTO.getAddress());
        university.setDescription(formDTO.getDescription());
        university.setCity(formDTO.getCity());
        university.setZip(formDTO.getZip());
        university.setPhone(formDTO.getPhone());

        universitiesRepository.save(university);

        redirectAttributes.addFlashAttribute("successMessage", "University registered successfully!");
        return "redirect:/universityRegister";
    }
}

