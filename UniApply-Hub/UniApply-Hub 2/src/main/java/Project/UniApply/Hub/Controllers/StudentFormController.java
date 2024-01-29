package Project.UniApply.Hub.Controllers;
import Project.UniApply.Hub.Data.StudentFormRepository;
import Project.UniApply.Hub.Data.StudentsRepository;
import Project.UniApply.Hub.Data.UniversitiesRepository;
import Project.UniApply.Hub.Models.DTO.RegisterStudentsFormDTO;
import Project.UniApply.Hub.Models.StudentForm;
import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("Students")
public class StudentFormController {
    @Autowired
    StudentFormRepository studentFormRepository;
    @Autowired
    UniversitiesRepository universitiesRepository;
    @Autowired
    AuthenticationController authenticationController;
    @Autowired
    StudentsRepository studentsRepository;
    @GetMapping("/studentForm")
    public String showForm(Model model,HttpServletRequest httpServletRequest) {
        HttpSession session= httpServletRequest.getSession();
        Students students=authenticationController.getStudentFromSession(session);
        model.addAttribute("title", "Student Form");
        model.addAttribute("educationLevels", StudentForm.EducationLevel.values());
        if(studentFormRepository.findStudentById(students.getId())!=null){
            StudentForm studentForm=studentFormRepository.findStudentById(students.getId());
            model.addAttribute("studentForm",studentForm );
            //model.addAttribute("studentFormId",studentForm);
        }
        else{
            model.addAttribute("studentForm", new StudentForm());
        }
        return "Students/studentForm";
    }
    @PostMapping("/studentForm")
    public String submitForm(@ModelAttribute  @Valid StudentForm studentForm,
                             Errors errors, Model model, RedirectAttributes redirectAttributes,HttpServletRequest httpServletRequest) {
        HttpSession session=httpServletRequest.getSession();
        Students student = authenticationController.getStudentFromSession(session);
        if (errors.hasErrors()) {
            model.addAttribute("title", "Student Form");
            model.addAttribute("educationLevels", StudentForm.EducationLevel.values());
            return "Students/studentForm";
        }
        StudentForm studentFormOptional=studentFormRepository.findStudentById(student.getId());
        if(studentFormOptional!=null){
            studentFormOptional.setStudent(student);
            studentFormOptional.setFirstName(studentForm.getFirstName());
            studentFormOptional.setLastName(studentForm.getLastName());
            studentFormOptional.setEmail(studentForm.getEmail());
            studentFormOptional.setGpa(studentForm.getGpa());
            studentFormOptional.setReference(studentForm.getReference());
            studentFormOptional.setCoverLetter(studentForm.getCoverLetter());
            studentFormOptional.setUsCitizen(studentForm.isUsCitizen());
            studentFormRepository.save(studentFormOptional);
        }
        else{
            studentForm.setStudent(student);
            studentFormRepository.save(studentForm);
        }
        redirectAttributes.addFlashAttribute("successMessage", "Form submitted successfully!");
        return "Students/studentForm";
    }
    @GetMapping("/dashboard")
    public String displayDashboard(Model model,HttpServletRequest request){
        HttpSession session=request.getSession();
        return "Students/dashboard";
    }
    @GetMapping("/showUniversities")
    public String displayyAllUniversities(Model model, HttpSession session) {
        model.addAttribute("all",universitiesRepository.findAll());
        model.addAttribute("title", "All Universities");
        return "Students/showUniversities";
    }

//    @RequestMapping(value = "/applyToUniversities" ,method = {RequestMethod.POST, RequestMethod.GET})
//    public String applyToUniversitiesProcessing(@RequestParam("university.id") List<Integer> selectedUniversityIds,
//                                                @ModelAttribute StudentForm studentForm,  HttpServletRequest request,
//                                                Model model) {
//        HttpSession session = request.getSession();
//        Students student = authenticationController.getStudentFromSession(session);
//        model.addAttribute("all", universitiesRepository.findAll());
//        model.addAttribute("title", "All Universities");
//        List<Universities> allUniversity=universitiesRepository.findAll();
//        List<Universities> selectedUniversities = new ArrayList<>();
//        for(int i=0;i<=allUniversity.size();i++){
//            for (int j=0;j<=selectedUniversityIds.size();j++){
//                if(selectedUniversityIds.get(j).equals(allUniversity.get(i).getId())){
//                    selectedUniversities.add(allUniversity.get(i));
//                }
//            }
//        }
//        student.addUniversity((Universities) selectedUniversities);
//        studentsRepository.save(student);
////
////        for (Integer universityId : selectedUniversityIds) {
////            Universities university = new Universities();
////            university.setId(universityId);
////            selectedUniversities.add(university);
////        }
////
////        student.setUniversities(selectedUniversities);
//        // studentForm.setUniversities(selectedUniversities);
//        //studentsRepository.save(student);
//        return "redirect:/Students/dashboard";
//    }
    @GetMapping("/profile")
    public String displayStudentProfile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Students student = authenticationController.getStudentFromSession(session);
        model.addAttribute("StudentInformation", studentsRepository.findById(student.getId()));
        System.out.println("soo "+studentsRepository.findById(student.getId()));
//        model.addAttribute("StudentsLoggedIn", session.getAttribute("student") != null);
        return "Students/profile";
    }
}