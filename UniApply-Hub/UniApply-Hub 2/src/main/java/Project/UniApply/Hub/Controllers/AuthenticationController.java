package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Data.StudentsRepository;
import Project.UniApply.Hub.Data.UniversitiesRepository;
import Project.UniApply.Hub.Models.DTO.LoginFormDTO;
import Project.UniApply.Hub.Models.DTO.RegisterStudentsFormDTO;
import Project.UniApply.Hub.Models.DTO.RegisterUniversitiesFormDTO;
import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {
//test
@Autowired
    StudentsRepository studentsRepository;
@Autowired
    UniversitiesRepository universitiesRepository;

    // The key to store user IDs
    private static final String universitySessionKey = "user";
    private static final String studentSessionKey = "student";

    // Look up user with key
    public Universities getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(universitySessionKey);
        if (userId == null) {
            return null;
        }

        Optional<Universities> user = universitiesRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }
    // Stores key/value pair with session key and user ID

    private static void setUserInSession(HttpSession session, Universities user) {
        session.setAttribute(universitySessionKey, user.getId());
    }

    public Students getStudentFromSession(HttpSession session) {
        Integer studentId = (Integer) session.getAttribute(studentSessionKey);
        if (studentId == null) {
            return null;
        }

        Optional<Students> student = studentsRepository.findById(studentId);

        if (student.isEmpty()) {
            return null;
        }

        return student.get();
    }
    // Stores key/value pair with session key and user ID

    private static void setStudentInSession(HttpSession session, Students student) {
        session.setAttribute(studentSessionKey, student.getId());
    }
//registerClient


    @GetMapping("/studentRegister")
    public String displayRegistrationStudentForm(Model model, HttpSession session) {
        model.addAttribute(new RegisterStudentsFormDTO());
        model.addAttribute("title", "Student Register");

        return "studentRegister";
    }

    @PostMapping("/studentRegister")
    public String processRegistrationStudentForm(@ModelAttribute @Valid RegisterStudentsFormDTO registerStudentsFormDTO,

                                                Errors errors, HttpServletRequest request,
                                                Model model) {

        // Send user back to form if errors are found
        if (errors.hasErrors()) {
            model.addAttribute("title", "Student Register");
            return "studentRegister";
        }
// Look up user in database using email they provided in the form
        Students existingStudent = studentsRepository.findByEmail(registerStudentsFormDTO.getEmail());
        // Send user back to form if email already exists
        if (existingStudent != null) {
            errors.rejectValue("email", "email.alreadyexists", "A user with that email already exists");
            model.addAttribute("title", "Student Register");
            return "studentRegister";
        }
// Send user back to form if passwords didn't match
        String password = registerStudentsFormDTO.getPassword();
        String verifyPassword = registerStudentsFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Client Register");
            return "studentRegister";
        }

        // OTHERWISE, save new email and hashed password in database, start a new session, and redirect to home page
        Students newStudent = new Students(registerStudentsFormDTO.getEmail(), registerStudentsFormDTO.getPassword(), registerStudentsFormDTO.getPhone() ,registerStudentsFormDTO.getFirstName(),
                registerStudentsFormDTO.getLastName(), registerStudentsFormDTO.getCountry());
        studentsRepository.save(newStudent);
        setStudentInSession(request.getSession(), newStudent);

        return "redirect:Students/dashboard";
    }



    //farmer  Part


    @GetMapping("/UniversityRegister")
    public String displayUniversitiesRegistrationForm(Model model,HttpSession session) {
        model.addAttribute(new RegisterUniversitiesFormDTO());
        model.addAttribute("title", "Register");
        model.addAttribute("loggedIn", session.getAttribute("user") != null);

        return "UniversityRegister";
    }

    @PostMapping("/UniversityRegister")
    public String processUniversitiesRegistrationForm(@ModelAttribute @Valid RegisterUniversitiesFormDTO registerUniversitiesFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
        // Send user back to form if errors are found
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "UniversityRegister";
        }
// Look up user in database using email they provided in the form
        Universities existingUniversityEmail = universitiesRepository.findByEmail(registerUniversitiesFormDTO.getEmail());
        Universities existingUniversity= universitiesRepository.findByUniversityName(registerUniversitiesFormDTO.getUniversityName());

        // Send user back to form if email already exists
        if (existingUniversityEmail != null ) {
            errors.rejectValue("email", "email.alreadyexists", "A University with that email already exists");
            model.addAttribute("title", "Register");
            return "UniversityRegister";
        }
        //check  duplicates
        if (existingUniversity != null ) {
            errors.rejectValue("UniversityName", "UniversityName.alreadyexists", "This University Name is already exists");
            model.addAttribute("title", "Register");
            return "UniversityRegister";
        }
// Send user back to form if passwords didn't match
        String password = registerUniversitiesFormDTO.getPassword();
        String verifyPassword = registerUniversitiesFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "UniversityRegister";
        }


        // OTHERWISE, save new email and hashed password in database, start a new session, and redirect to home page

        Universities newUniversity = new Universities(registerUniversitiesFormDTO.getEmail(), registerUniversitiesFormDTO.getPassword(), registerUniversitiesFormDTO.getPhone(),
                registerUniversitiesFormDTO.getUniversityName(), registerUniversitiesFormDTO.getAddress(),registerUniversitiesFormDTO.getDescription()
        ,registerUniversitiesFormDTO.getCity(),registerUniversitiesFormDTO.getZip());

        universitiesRepository.save(newUniversity);

        setUserInSession(request.getSession(), newUniversity);

        return "redirect:Universities/dashboard";
    }
    // Handlers for login form
    @GetMapping("/login")
    public String displayLoginForm(Model model, HttpSession session) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        if(session.getAttribute("user") != null){

            model.addAttribute("loggedIn", session.getAttribute("user") != null);
        }
        else{
            model.addAttribute("loggedIn", session.getAttribute("student") != null);}


        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {
// Send user back to form if errors are found
        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }
        // Look up user in database using email they provided in the form
        Students theStudent = studentsRepository.findByEmail(loginFormDTO.getEmail());
        Universities theUniversity=universitiesRepository.findByEmail((loginFormDTO.getEmail()));
        if (theUniversity == null && theStudent==null) {
            errors.rejectValue("email", "email.invalid", "The given email does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        //if University
        if(theUniversity!=null && theStudent==null){

            String password = loginFormDTO.getPassword();


            if (!theUniversity.isMatchingPassword(password)) {
                errors.rejectValue("password", "password.invalid", "Invalid password");
                model.addAttribute("title", "Log In");
                return "login";
            }

            // OTHERWISE, create a new session for the user and take them to the home page
            setUserInSession(request.getSession(), theUniversity);


            return "redirect:Universities/dashboard";

        }
        //if Student
        else {
            String password = loginFormDTO.getPassword();

            if (!theStudent.isMatchingPassword(password)) {
                errors.rejectValue("password", "password.invalid", "Invalid password");
                model.addAttribute("title", "Log In");
                return "login";
            }

            // OTHERWISE, create a new session for the user and take them to the home page
            setStudentInSession(request.getSession(), theStudent);

// go to the dashboard
            return "redirect:Students/dashboard";

        }
    }
    // Handler for logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:";
    }



}
