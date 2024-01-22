package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Models.DTO.RegisterStudentsFormDTO;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("Students")
public class StudentFormController {


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
