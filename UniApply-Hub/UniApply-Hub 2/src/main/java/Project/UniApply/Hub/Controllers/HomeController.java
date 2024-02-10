package Project.UniApply.Hub.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping
    public String displayHomePage(Model model, HttpSession session) {
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute("StudentsLoggedIn", session.getAttribute("student") != null);
        return "index";
    }
    @GetMapping("/about")
    public String displayAboutPage(Model model, HttpSession session) {
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute("StudentsLoggedIn", session.getAttribute("student") != null);
        return "/about";
    }
}
