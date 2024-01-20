package Project.UniApply.Hub.Models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

//@Entity
public class StudentForm {
//    @Id
//    @GeneratedValue
//    private int id;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof StudentForm)) return false;
//        StudentForm that = (StudentForm) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//
//
//
//
//
//    @NotBlank(message = "First name is required")
//    @Size(min = 3, max = 45, message = "First name must be between 3 and 45 characters")
//    private String firstName;
//    @NotBlank(message = "Last name is required")
//    @Size(min = 3, max = 45, message = "Last name must be between 3 and 45 characters")
//    private String lastName;
//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email. Try again.")
//    private String email;
//    @NotBlank(message = "GPA is required")
//    @DecimalMin(value = "0.0", message = "GPA must be equal to or greater than 0.0")
//    @DecimalMax(value = "4.0", message = "GPA must be equal to or less than 4.0")
//    private double gpa;
//    @NotBlank(message = "Education level is required")
//    private String educationLevel;
//    @NotEmpty(message = "References are required")
//    private List<String> references;
//    private String coverLetter;
//    @NotBlank(message = "US citizen information is required")
//    private boolean usCitizen;
//    public StudentForm() {
//    }
//    public StudentForm(String firstName, String lastName, String email, double gpa, String educationLevel,
//                       List<String> references, String coverLetter, boolean usCitizen) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.gpa = gpa;
//        this.educationLevel = educationLevel;
//        this.references = references;
//        this.coverLetter = coverLetter;
//        this.usCitizen = usCitizen;
//    }
//    public String getFirstName() {
//        return firstName;
//    }
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//    public String getLastName() {
//        return lastName;
//    }
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public double getGpa() {
//        return gpa;
//    }
//    public void setGpa(double gpa) {
//        this.gpa = gpa;
//    }
//    public String getEducationLevel() {
//        return educationLevel;
//    }
//    public void setEducationLevel(String educationLevel) {
//        this.educationLevel = educationLevel;
//    }
//    public List<String> getReferences() {
//        return references;
//    }
//    public void setReferences(List<String> references) {
//        this.references = references;
//    }
//    public String getCoverLetter() {
//        return coverLetter;
//    }
//    public void setCoverLetter(String coverLetter) {
//        this.coverLetter = coverLetter;
//    }
//    public boolean isUsCitizen() {
//        return usCitizen;
//    }
//    public void setUsCitizen(boolean usCitizen) {
//        this.usCitizen = usCitizen;
//    }
}