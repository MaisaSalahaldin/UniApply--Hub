package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Data.StudentsRepository;
import Project.UniApply.Hub.Data.UniversitiesRepository;
import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/universities")
public class UniversitiesController {
    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    UniversitiesRepository universitiesRepository;
    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("/showAll")
    public String showAll(Model model) {
        Iterable<Universities> allUniversities = universitiesRepository.findAll();
        model.addAttribute("all", allUniversities);
        model.addAttribute("title", "All Universities");


        List<Universities> selectedUniversities = new ArrayList<>();
        model.addAttribute("selectedUniversities", selectedUniversities);
       // universitiesRepository.save(selectedUniversities);

        return "universities/showAll";
    }

    @PostMapping("/saveSelected")
    public String saveSelectedUniversities(@ModelAttribute("selectedUniversities") List<Integer> selectedUniversityIds) {
        List<Universities> selectedUniversities = StreamSupport.stream(universitiesRepository.findAllById(selectedUniversityIds).spliterator(), false)
                .collect(Collectors.toList());
        universitiesRepository.saveAll(selectedUniversities);

        return "redirect:/universities/showAll";
    }

    @PostMapping("/apply")
    public String applyToUniversities(@ModelAttribute("selectedUniversities") List<Integer> selectedUniversityIds
                                     // HttpServletRequest request
                                      ) {
       // HttpSession session = request.getSession();
        Students currentStudent = studentsRepository.findByEmail(Students.getEmail());

        List<Universities> selectedUniversities = (List<Universities>) universitiesRepository.findAllById(selectedUniversityIds);
        currentStudent.setUniversities(selectedUniversities);
        studentsRepository.save(currentStudent);

//   for (Universities university : selectedUniversities){
//        currentStudent.addUniversity(university);
//       }


        return "redirect:/Students/dashboard";
    }
}
