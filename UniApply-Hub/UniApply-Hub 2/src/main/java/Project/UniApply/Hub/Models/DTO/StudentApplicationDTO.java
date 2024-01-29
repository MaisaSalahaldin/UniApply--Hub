package Project.UniApply.Hub.Models.DTO;

import Project.UniApply.Hub.Models.StudentForm;
import Project.UniApply.Hub.Models.Universities;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import java.util.List;

public class StudentApplicationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private double gpa;
    private String educationLevel;
    private String reference;
    private String coverLetter;
    private boolean usCitizen;

    @ElementCollection
    private List<Integer> selectedUniversityIds;

    //@OneToOne
    private StudentForm studentform;

    public StudentApplicationDTO(String firstName, String lastName, String email, double gpa, String educationLevel, String reference,
                                 String coverLetter, boolean usCitizen, List<Integer> selectedUniversityIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gpa = gpa;
        this.educationLevel = educationLevel;
        this.reference = reference;
        this.coverLetter = coverLetter;
        this.usCitizen = usCitizen;
        this.selectedUniversityIds = selectedUniversityIds;
    }

    public StudentApplicationDTO() {
    }

    public enum EducationLevel {
        HIGH_SCHOOL,
        BACHELOR,
        MASTER,
        DOCTORATE,

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }

    public List<Integer> getSelectedUniversityIds() {
        return selectedUniversityIds;
    }

    public void setSelectedUniversityIds(List<Integer> selectedUniversityIds) {
        this.selectedUniversityIds = selectedUniversityIds;
    }

    public StudentForm getStudentform() {
        return studentform;
    }

    public void setStudentform(StudentForm studentform) {
        this.studentform = studentform;
    }
}
