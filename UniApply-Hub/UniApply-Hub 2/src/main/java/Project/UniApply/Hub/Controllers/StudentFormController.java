package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Data.StudentFormRepository;
import Project.UniApply.Hub.Models.DTO.RegisterStudentsFormDTO;
import Project.UniApply.Hub.Models.StudentForm;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("Students")
public class StudentFormController {

    @Autowired
    StudentFormRepository studentFormRepository;
    @GetMapping("/student-form")
    public String showForm(Model model) {
        model.addAttribute("title", "Student Form");
        model.addAttribute("educationLevels", StudentForm.EducationLevel.values());
        model.addAttribute("studentForm", new StudentForm());
        return "studentForm";
    }

    @PostMapping("/student-form/create")
    public String submitForm(@Valid StudentForm studentForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Student Form");
            model.addAttribute("educationLevels", StudentForm.EducationLevel.values());
            return "studentForm";
        }
        studentFormRepository.save(studentForm);
        return "redirect:/Students/student-form";
    }


    @GetMapping("/dashboard")
    public String displayDashboard(){

        return "Students/dashboard";
    }
    @GetMapping("/showUniversities")
    public String dispalyAllUniversities(Model model, HttpSession session) {
        model.addAttribute("university",new Universities());
        model.addAttribute("title", "All Universities");
        return "Students/showUniversities";
    }


}
