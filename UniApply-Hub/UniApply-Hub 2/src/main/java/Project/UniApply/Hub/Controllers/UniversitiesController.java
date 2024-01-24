package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Data.StudentFormRepository;
import Project.UniApply.Hub.Data.StudentsRepository;
import Project.UniApply.Hub.Data.UniversitiesRepository;
import Project.UniApply.Hub.Models.StudentForm;
import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    StudentFormRepository studentFormRepository;


//    @GetMapping("/showAll")
//    public String showAll(Model model) {
//        Iterable<Universities> allUniversities = universitiesRepository.findAll();
//        model.addAttribute("all", allUniversities);
//        model.addAttribute("title", "All Universities");
//
//
//        List<Universities> selectedUniversities = new ArrayList<>();
//        model.addAttribute("selectedUniversities", selectedUniversities);
//       // universitiesRepository.save(selectedUniversities);
//
//        return "universities/showAll";
//    }
//
//    @PostMapping("/saveSelected")
//    public String saveSelectedUniversities(@ModelAttribute("selectedUniversities") List<Integer> selectedUniversityIds) {
//        List<Universities> selectedUniversities = StreamSupport.stream(universitiesRepository.findAllById(selectedUniversityIds).spliterator(), false)
//                .collect(Collectors.toList());
//        universitiesRepository.saveAll(selectedUniversities);
//
//        return "redirect:/universities/showAll";
//    }
//
//    @PostMapping("/apply")
//    public String applyToUniversities(@ModelAttribute("selectedUniversities") List<Integer> selectedUniversityIds
//                                     // HttpServletRequest request
//                                      ) {
//       // HttpSession session = request.getSession();
//        Students currentStudent = studentsRepository.findByEmail(Students.getEmail());
//
//        List<Universities> selectedUniversities = (List<Universities>) universitiesRepository.findAllById(selectedUniversityIds);
//        currentStudent.setUniversities(selectedUniversities);
//        studentsRepository.save(currentStudent);
//
////   for (Universities university : selectedUniversities){
////        currentStudent.addUniversity(university);
////       }
//
//
//        return "redirect:/Students/dashboard";
//    }
//}
// StudentFormController

    @GetMapping("/showUniversities")
    public String displayAllUniversities(Model model, HttpSession session) {
        model.addAttribute("all", universitiesRepository.findAll());
        model.addAttribute("title", "All Universities");
        return "Students/showUniversities";
    }

    @PostMapping("/applyToUniversities")
    public String applyToUniversities(@RequestParam("selectedUniversityIds") List<Integer> selectedUniversityIds,
                                      @ModelAttribute StudentForm studentForm,  HttpServletRequest request,
                                      Model model) {

        HttpSession session = request.getSession(false);
       Students student = authenticationController.getStudentFromSession(session);


        List<Universities> selectedUniversities = new ArrayList<>();

        for (Integer universityId : selectedUniversityIds) {
            Universities university = new Universities();
            university.setId(universityId);
            selectedUniversities.add(university);
        }

        student.setUniversities(selectedUniversities);



       // studentForm.setUniversities(selectedUniversities);
        studentsRepository.save(student);

        return "redirect:/Students/dashboard";
    }
};



