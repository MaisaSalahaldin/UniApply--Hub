package Project.UniApply.Hub.Controllers;

import Project.UniApply.Hub.Data.StudentFormRepository;
import Project.UniApply.Hub.Data.StudentsRepository;
import Project.UniApply.Hub.Data.UniversitiesRepository;
import Project.UniApply.Hub.Models.DTO.SelectedUniversityDTO;
import Project.UniApply.Hub.Models.StudentForm;
import Project.UniApply.Hub.Models.Students;
import Project.UniApply.Hub.Models.Universities;
import org.springframework.beans.factory.annotation.Autowired;
import Project.UniApply.Hub.Models.DTO.RegisterUniversitiesFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        return "redirect:/Students/showUniversities";
    }


    @GetMapping("/dashboard")
    public String displayDashboard(Model model,HttpServletRequest request){
      HttpSession session=request.getSession();

        return "Students/dashboard";
    }
    @GetMapping("/showUniversities")
    public String displayyAllUniversities(Model model, HttpServletRequest request) {
        HttpSession session=request.getSession();
        Students students=authenticationController.getStudentFromSession(session);

        model.addAttribute("student",students);
        model.addAttribute("all",universitiesRepository.findUniversitiesNotApplied(students.getId()));
        model.addAttribute("title", "All Universities");
        return "Students/showUniversities";
    }
@PostMapping("/showUniversities")
public String applyToUniversityprocessing(Model model,HttpServletRequest request
,@ModelAttribute("student") Students student,@RequestParam(value = "uni" , required = false) ArrayList<Integer> uni
, Errors errors ){

    HttpSession session=request.getSession();
    Students students=authenticationController.getStudentFromSession(session);


    final List<Universities> newUniversity =
            (List<Universities>) uni.stream().map(id -> universitiesRepository.findById(id).get()).collect(Collectors.toList());


    students.setUniversities(newUniversity);
    StudentForm studentForm=studentFormRepository.findStudentById(students.getId());
   for(int i=0;i<newUniversity.size();i++){
       studentForm.addUniversity(newUniversity.get(i));

   }
    studentFormRepository.save(studentForm);

    studentsRepository.save(students);



        return "redirect:";
}

    @GetMapping("/profile")
    public String displayStudentProfile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Students student = authenticationController.getStudentFromSession(session);
        Optional<Students> students=studentsRepository.findById(student.getId());
        if(students.isEmpty()){
            return "redirect:";
        }
        model.addAttribute("StudentInformation", students.get());
        return "Students/profile";
    }

@GetMapping("/profile/edit")
    public String UpdateStudentProfile(Model model,HttpServletRequest request){
            HttpSession session=request.getSession();
            Students students=authenticationController.getStudentFromSession(session);
            model.addAttribute("information",studentsRepository.findById(students.getId()));
            model.addAttribute("title","Student Information");
        return "Students/profileEdit";
}
@PostMapping("/profile/edit")
    public String updateStudentProfileProcessing(Model model,HttpServletRequest request
        ,@ModelAttribute @Valid  Students updateData,Errors errors){
        HttpSession session=request.getSession();
        Students student=authenticationController.getStudentFromSession(session);

if(errors.hasErrors()){
    model.addAttribute("information",studentsRepository.findById(student.getId()));
    model.addAttribute("title","Student Information");
    return "Students/profileEdit";
}
        student.setFirstName(updateData.getFirstName());
        student.setLastName(updateData.getLastName());
        student.setCountry(updateData.getCountry());
        student.setEmail(updateData.getEmail());
        student.setPhone(updateData.getPhone());
        studentsRepository.save(student);

        return "redirect:";
}
}
