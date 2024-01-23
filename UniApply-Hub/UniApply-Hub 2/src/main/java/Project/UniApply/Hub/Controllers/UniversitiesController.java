package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Data.UniversitiesRepository;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/universities")
public class UniversitiesController {

    @Autowired
    private UniversitiesRepository universitiesRepository;

    @GetMapping("/showAll")
    public String showAll(Model model) {
        Iterable<Universities> allUniversities = universitiesRepository.findAll();
        model.addAttribute("all", allUniversities);
        model.addAttribute("title", "All Universities");

        // Create a list to hold the selected universities
        List<Universities> selectedUniversities = new ArrayList<>();
        model.addAttribute("selectedUniversities", selectedUniversities);

        return "universities/showAll";
    }

    @PostMapping("/apply")
    public String applyToUniversities(@ModelAttribute("selectedUniversities") List<Universities> selectedUniversities) {
        // Process the selected universities (e.g., save them to the student's application)
        // You may want to associate these universities with the current student

        // Redirect to a success page or any other appropriate page
        return "redirect:/Students/dashboard";
    }
}
