package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Data.ApplicationStatusRepository;
import Project.UniApply.Hub.Data.StudentFormRepository;
import Project.UniApply.Hub.Data.StudentsRepository;
import Project.UniApply.Hub.Data.UniversitiesRepository;
import Project.UniApply.Hub.Models.ApplicationStatus;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/Universities")
public class UniversitiesController {
    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    UniversitiesRepository universitiesRepository;
    @Autowired
    StudentsRepository studentsRepository;
    @Autowired
    StudentFormRepository studentFormRepository;
    @Autowired
    ApplicationStatusRepository applicationStatusRepository;

    @GetMapping("/dashboard")
    public String displayDashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute("StudentsLoggedIn", session.getAttribute("student") != null);

        Universities universities = authenticationController.getUserFromSession(session);

        return "Universities/dashboard";
    }

    @GetMapping("/showForms")
    public String displayForms(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute("StudentsLoggedIn", session.getAttribute("student") != null);

        Universities universities = authenticationController.getUserFromSession(session);
        List<ApplicationStatus> formsOfUniversity = applicationStatusRepository.findFormsByUniversityId(universities.getId());
        //List<ApplicationStatus> applicationStatuses=applicationStatusRepository.findByStudentForm(formsOfUniversity);
        model.addAttribute("nameOfUniversity", universities.getUniversityName());
        model.addAttribute("formsOfUniversity", formsOfUniversity);
        //model.addAttribute("status",applicationStatuses);

        return "Universities/showForms";
    }



    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("formIds") List<Integer> formIds,Model model,
                               @RequestParam("statuses") List<String> statuses,HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute("StudentsLoggedIn", session.getAttribute("student") != null);

        Universities universities = authenticationController.getUserFromSession(session);

        if (formIds.size() != statuses.size()) {
            // Handle error: formIds and statuses lists must have the same size
            return "redirect:/Universities/showForms";
        }
        // Iterate through the lists of formIds and statuses
        for (int i = 0; i < formIds.size(); i++) {
            int formId = formIds.get(i);
            String status = statuses.get(i);
            // Retrieve the StudentForm based on the formId
            Optional<StudentForm> optionalStudentForm = studentFormRepository.findById(formId);

            if (optionalStudentForm.isPresent()) {
                StudentForm studentForm = optionalStudentForm.get();
                // Update the ApplicationStatus directly based on the selected status

                // Check if an ApplicationStatus already exists for the StudentForm and University
                Optional<ApplicationStatus> existingStatus = applicationStatusRepository
                        .findByStudentFormAndUniversities(studentForm, universities);
                ApplicationStatus applicationStatus;
                if (existingStatus.isPresent()) {
                    // If an ApplicationStatus exists, update it
                    applicationStatus = existingStatus.get();
                    applicationStatus.setApplicationStatusType(ApplicationStatus.ApplicationStatusType.valueOf(status));
                } else {
                    // If no ApplicationStatus exists, create a new one with PENDING status
                    applicationStatus = new ApplicationStatus();
                    applicationStatus.setStudentForm(studentForm);
                    applicationStatus.setUniversities(universities);
                    applicationStatus.setApplicationStatusType(ApplicationStatus.ApplicationStatusType.PENDING);
                }
                // Save the ApplicationStatus
                applicationStatusRepository.save(applicationStatus);
            }
        }
        // Redirect to the showForms page
        return "redirect:/Universities/showForms";
    }

}
