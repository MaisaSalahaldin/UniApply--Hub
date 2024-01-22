package Project.UniApply.Hub.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Students")
public class StudentFormController {


    @GetMapping("/dashboard")
    public String displayDashboard(){

        return "Students/dashboard";
    }


}
