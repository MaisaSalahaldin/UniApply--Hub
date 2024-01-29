
package Project.UniApply.Hub.Controllers;


import Project.UniApply.Hub.Data.StudentFormRepository;
import Project.UniApply.Hub.Data.StudentsRepository;
import Project.UniApply.Hub.Data.UniversitiesRepository;

import Project.UniApply.Hub.Models.DTO.UniversitySelectionDTO;

import Project.UniApply.Hub.Models.StudentForm;
import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@Controller
@RequestMapping("Students")
public class UniversitySelectionController {

    private static final String SELECTED_UNIVERSITIES_SESSION_KEY = "selectedUniversities";

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private UniversitiesRepository universitiesRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private Students students;

    @Autowired
    private StudentForm studentForm;
    @Autowired
    StudentFormRepository studentFormRepository;

    @GetMapping("/showUniversitySelectionPage")
    public String showUniversitySelectionPage(Model model, HttpSession session) {

        Students student = authenticationController.getStudentFromSession(session);

        // Fetch all available universities to display on the page
        List<Universities> allUniversities = universitiesRepository.findAll();
        model.addAttribute("allUniversities", allUniversities);

        // Initialize the UniversitySelectionDTO to store selected university IDs
        UniversitySelectionDTO universitySelectionDTO = new UniversitySelectionDTO();

        // Retrieve selected university IDs from the session (if any)
        List<Integer> selectedUniversitiesIds = (List<Integer>) session.getAttribute(SELECTED_UNIVERSITIES_SESSION_KEY);

        // Set the selected university IDs in the DTO
        universitySelectionDTO.setSelectedUniversitiesIds(selectedUniversitiesIds);
        model.addAttribute("universitySelectionDTO", universitySelectionDTO);

        return "Students/showUniversitySelectionPage";
    }


    @RequestMapping(value = "/applyToUniversities", method = {RequestMethod.GET, RequestMethod.POST})
    public String applyToUniversities(
            @RequestParam(value = "selectedUniversitiesIds", required = false) List<Integer> selectedUniversityIds,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        Students student = authenticationController.getStudentFromSession(session);

        Optional<StudentForm> optionalStudentForm = studentFormRepository.findByStudentId(student.getId());

        if (optionalStudentForm.isPresent()) {
            StudentForm studentFormToApply = optionalStudentForm.get();


            if (selectedUniversityIds != null && !selectedUniversityIds.isEmpty()) {
                List<Universities> selectedUniversities = selectedUniversityIds.stream()
                        .map(universityId -> universitiesRepository.findById(universityId)
                                .orElseThrow(() -> new EntityNotFoundException("University not found with ID: " + universityId)))
                        .collect(Collectors.toList());

                studentForm.setUniversities(selectedUniversities);

                student.setUniversities(selectedUniversities);
                studentsRepository.save(student);
                session.setAttribute(SELECTED_UNIVERSITIES_SESSION_KEY, selectedUniversityIds);
                session.removeAttribute(SELECTED_UNIVERSITIES_SESSION_KEY);
            }

            return "redirect:/Students/applicationSubmitted";
        }



